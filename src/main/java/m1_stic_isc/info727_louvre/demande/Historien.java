package m1_stic_isc.info727_louvre.demande;

import java.util.Vector;

import m1_stic_isc.info727_louvre.fiches.Oeuvre;

/**
 * 
 * @author Turbopastek
 *
 */
public class Historien extends Utilisateur {

	private Vector<Demande> listeDemandes = new Vector<Demande>();
	
	public Historien(String login, String mdp) {
		super(login, mdp);
	}
	
	/**
	 * Cr�e une nouvelle demande et ajoute cette demande
	 * � la liste de demandes du systeme
	 * 
	 * @param nom : nom de la demande
	 * @param description : description de la demande
	 * @param oeuvre : oeuvre li�e � la demande
	 */
	public void creerDemande(String nom, String description, Oeuvre oeuvre, Systeme sys) {
			Demande demande = new Demande(nom, description, oeuvre, this);
			addDemande(demande);
			sys.addDemande(demande);
	}
	
	/**
	 * Ajoute une demande � la liste des demandes cr�es
	 * par l'historien
	 * 
	 * @param demande : demande � ajouter
	 */
	private void addDemande(Demande demande) {
		listeDemandes.add(demande);
	}
	
	/**
	 * Override Utilisateur.isHistorien()
	 */
	public boolean isHistorien() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public Vector<Demande> getDemandes() {
		return (Vector<Demande>) listeDemandes.clone();
	}
}