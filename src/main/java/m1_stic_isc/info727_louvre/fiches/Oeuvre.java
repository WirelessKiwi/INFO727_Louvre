package m1_stic_isc.info727_louvre.fiches;


import java.util.Vector;

public class Oeuvre {
	private static int lastId = 0;
	private String _nom;
	private String _date;
	private int _id;
	private String _description;
	private Departement _departement;
	private Photo _photoFiche;
	private Vector<Auteur> _listeAuteurs = new Vector<Auteur>();

	public Oeuvre (String nom, String date, String description, Departement dep, Vector<Auteur> auteurs){
		this._nom = nom;
		this._date = date;
		this._description = description;
		this._departement = dep;
		this._id = Oeuvre.lastId;
		
		
		
		//l'oeuvre s'ajoute dans les oeuvres de l'auteur et du d�partement
		if(auteurs != null){
		this._listeAuteurs = auteurs;
			for (int i=0;i<auteurs.size();i++)
			{
				auteurs.elementAt(i).addOeuvre(this);
			}
		}
		// si l'auteur est inconnu,
		else
		{
			this._listeAuteurs.add(Auteur.auteurInconnu);
			Auteur.auteurInconnu.addOeuvre(this);
		}
		dep.addOeuvre(this);
		Oeuvre.lastId +=1;
	}
	
	//Constructeur d'oeuvre avec auteur inconnu
	public Oeuvre (String nom, String date, String description, Departement dep)
	{

		this._nom = nom;
		this._date = date;
		this._description = description;
		this._departement = dep;
		this._id = Oeuvre.lastId;
		
		this._listeAuteurs.add(Auteur.auteurInconnu);
		Auteur.auteurInconnu.addOeuvre(this);
	
		dep.addOeuvre(this);
		Oeuvre.lastId +=1;
	}
	
	/**
	 * construit la fiche textuelle de l'oeuvre
	 * @return les informations de l'oeuvre
	 */
	public String construireFiche() {
		String fiche = new String();
		
		fiche += "\nNom de l'oeuvre: ";
		fiche += this.getNom()+"\n";
		
		fiche +="Auteur(s): \n";
		for(int i = 0; i < _listeAuteurs.size(); i++){
			fiche+= "\t- "+_listeAuteurs.get(i).getNom();
			fiche += " "+_listeAuteurs.get(i).getPrenom()+"\n";
		}
		
		fiche += "Description : \n";
		fiche += "\t"+this.getDescription();
		
		
		return fiche;
	}
	
	/**
	 * Teste si le nom de l'oeuvre contient le nom
	 * pass� en param�tre
	 * 
	 * @param nom : nom � tester
	 * @return : true si oui, non sinon
	 */
	public boolean recherche(String nom){
		nom = nom.toLowerCase();
		if(getNom().toLowerCase().contains(nom)){
			return true;
		}
		else{
			return false;
		}
	}

	/*=========================
	 	Getters & Setters
	 ==========================*/
	public void setNom(String aNom) {
		this._nom = aNom;
	}

	public String getNom() {
		return this._nom;
	}

	public void setDate(String aDate) {
		this._date = aDate;
	}

	public String getDate() {
		return this._date;
	}

	public void setId(int aId) {
		this._id = aId;
	}

	public int getId() {
		return this._id;
	}

	public void setDescription(String aDescription) {
		this._description = aDescription;
	}

	public String getDescription() {
		return this._description;
	}

	public Departement getDepartement() {
		return _departement;
	}

	public void setDepartement(Departement _departement) {
		this._departement = _departement;
	}

	public Photo getPhotoFiche() {
		return _photoFiche;
	}

	public void setPhotoFiche(Photo _photoFiche) {
		this._photoFiche = _photoFiche;
	}

	public Vector<Auteur> getListeAuteurs() {
		return _listeAuteurs;
	}

	public void setListeAuteurs(Vector<Auteur> _listeAuteurs) {
		this._listeAuteurs = _listeAuteurs;
	}
}