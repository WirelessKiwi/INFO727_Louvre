package m1_stic_isc.info727_louvre.demande;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import m1_stic_isc.info727_louvre.fiches.Oeuvre;

/**
 * Point d'entr�e pour le syst�me de gestion de demandes
 * de travaux photos
 * 
 * G�re :
 * 	- la liste des demandes
 *  - la liste des utilisateurs (Historiens et Photographes)
 *  - l'utilisateur courant
 * A une r�f�rence vers le syst�me g�rant les fiches pour pouvoir acc�der aux oeuvres
 * 
 * @author Quentin
 *
 */
public class Systeme {
	
	private Utilisateur curUser;
	private Vector<Demande> listeDemandes = new Vector<Demande>();
	private Vector<Utilisateur> listeUtilisateurs = new Vector<Utilisateur>();
	private m1_stic_isc.info727_louvre.fiches.Systeme musee;
	
	public Systeme(m1_stic_isc.info727_louvre.fiches.Systeme musee)
	{
		this.curUser = null;
		this.musee = musee;
		// cr�e des utilisateurs de test
		this.listeUtilisateurs.add(new Historien("toto", "toto"));
		this.listeUtilisateurs.add(new Photographe("titi", "titi"));
	}

	/**
	 * Affiche un message demandant � l'utilisateur les informations
	 * permettants de cr�er une demande
	 * On cr�er ensuite cette demande
	 */
	public void afficherPageCreation()
	{		
		String nom = readLine("Nom de la demande :");
		String description = readLine("Description : ");
		String oeuvre = readLine("Oeuvre : ");
		
		this.creerDemande(nom, description, Integer.parseInt(oeuvre));
	}
	
	/**
	 * readLine fait maison pour palier au bug de System.console()
	 * qui renvoit null
	 * 
	 * @param message : message � afficher
	 * @return une ligne (String)
	 */
	private String readLine(String message)
	{
		if (System.console() != null) {
	        return System.console().readLine(message);
	    }
		else{
		    System.out.print(message);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    try {
				return reader.readLine();
			} catch (IOException e) {
				System.err.println("Erreur IO");
				return null;
			}
		}
	}

	/**
	 * Ajoute une nouvelle demande
	 * V�rifie qu'un utilisateur est connect� et qu'il peut
	 * cr�er une demande (i.e. c'est un historien)
	 * 
	 * @param nom : nom de la demande
	 * @param description : description de la demande
	 * @param oeuvre : index de l'oeuvre associ�e � la demande
	 */
	public void creerDemande(String nom, String description, int oeuvreIndex)
	{
		if(curUser != null){
			if(curUser.isHistorien()){
				// on r�cup�re l'oeuvre correspondant � l'index
				Oeuvre oeuvre = musee.getOeuvreByIndex(oeuvreIndex);
				// on v�rifie que la r�cup�ration a fonctionn�
				if(oeuvre != null){
					((Historien) curUser).creerDemande(nom, description, oeuvre, this);
				}
				else{
					System.out.println("L'oeuvre n'existe pas");
				}
			}
			else{
				System.out.println("Vous n'avez pas le droit de cr�er une demande");
			}
		}
		else{
			System.out.println("Vous n'�tes pas connect�");
		}
	}
	
	/**
	 * Ajoute une demande � la liste des demandes
	 * 
	 * @param demande : la demande � ajouter
	*/
	public void addDemande(Demande demande)
	{
		listeDemandes.add(demande);		
	}
	
	/**
	 * Recherche toutes les demandes en attente du photographe connect�
	 */
	public void consulterDemandesEnAttente()
	{
		Demande curDemande;
		Vector<Demande> resultats = new Vector<Demande>();
		if(curUser.isPhotographe())
		{
			for(int i=0;i<listeDemandes.size();i++){
				curDemande = listeDemandes.get(i);
				if(curDemande.estEtat(EtatDemande.EN_ATTENTE)){
					resultats.add(curDemande);
				}
			}
			if(!resultats.isEmpty()){
				afficherDemandes(resultats);
			}
			else{
				System.out.println("Aucune demandes en attente");
			}
		}
		else{
			System.out.println("Vous ne pouvez pas consulter les demandes en attente");
		}
		
	}

	/**
	 * Affiche une liste de demandes
	 * Utilis� par consulterDemandes et consulteresDemandes
	 * @param demandes : le tableau de demandes � afficher
	 */
	public void afficherDemandes(Vector<Demande> demandes) 
	{
		Demande curDemande;
		String listeDemande ="Liste des demandes :\n";
		for(int i=0;i<demandes.size();i++){
			curDemande = demandes.get(i);
			listeDemande += "\tOeuvre concern�e: "+curDemande.getOeuvre().getNom()+"\n";
			listeDemande += "\tNom de la demande: "+curDemande.getNom()+"\n";
			listeDemande += "\tDescription: "+curDemande.getDescription()+"\n\n";		
		}
		System.out.println(listeDemande);
	}

	public void prendreEnCharge(Demande aDemande)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Cherche un utilisateur correspondant au couple
	 * login/mdp entr� par l'utilisateur
	 * Si un utlisateur correspond, il devient l'utilisateur connect�
	 * 
	 * @param aLogin : login entr� par l'utilisateur
	 * @param aPassword : motde passe entr� par l'utilisateur
	 */
	public void connexion(String aLogin, String aPassword)
	{
		int i = 0;
		int size = listeUtilisateurs.size();
		boolean connexion = false;
		Utilisateur u;
		
		while(i < size && !connexion) {
			// obtenir �l�ment courant
			u = listeUtilisateurs.get(i);
			// traitement de l'�l�ment
			if(u.connexion(aLogin, aPassword)) {
				connexion = true;
				curUser = u;
			}
			// �l�ment suivant
			i++;
		}
	}

	/**
	 * Cherche et affiche toutes les demandes
	 * de l'utilisateur courant
	 * 
	 * Utilise afficherDemandes() pour afficher le r�sultat
	 */
	public void consulterMesDemandes()
	{
		// on v�rifie qu'un utilisateur est connect�
		if(curUser != null){
			// on v�rifie qu'il sagit d'un historien
			if(curUser.isHistorien()){
				afficherDemandes(((Historien) curUser).getDemandes());
			}
			else{
				System.out.println("Acc�s interdit");
			}
		}
		else{
			System.out.println("Vous n'�tes pas connect�");
		}
	}
	
	/**
	 * Renvoie l'utilisateur connect�
	 * 
	 * @return : l'utilisateur courant
	 */
	public Utilisateur getCurUser()
	{
		return this.curUser;
	}

	
	
	
	public static void main(String[] arg)
	{
		m1_stic_isc.info727_louvre.fiches.Systeme musee = new m1_stic_isc.info727_louvre.fiches.Systeme();
		Systeme sys = new Systeme(musee);
		
		sys.consulterMesDemandes();
		// connexion en tant qu'historien
		sys.connexion("toto", "toto");
		sys.afficherPageCreation();
		
		sys.connexion("titi", "titi");
		sys.consulterDemandesEnAttente();
	}
}