package louvre.fiches;

import java.util.Vector;

public class Auteur {
	private String _nom;
	private String _prenom;
	private Vector<Oeuvre> _listeOeuvres = new Vector<Oeuvre>();
	public static Auteur auteurInconnu = new Auteur("Inconnu", "Inconnu");
	
	public Auteur (String nom, String prenom){
		this._nom = nom;
		this._prenom = prenom;
	}
	
	/**
	 * Teste si le nom et et le prenom de l'auteur
	 * contiennent le nom et prénom passés en apramètre
	 * 
	 * @param nom : nom à tester
	 * @param prenom : prenom à tester
	 * 
	 * @return true si oui, false sinon
	 */
	public boolean recherche(String nom, String prenom){
		nom = nom.toLowerCase();
		prenom = prenom.toLowerCase();
		
		if(getNom().toLowerCase().contains(nom) 
				&& getPrenom().toLowerCase().contains(prenom)){
			return true;
		}
		else{
			return false;
		}
	}

	public void setNom(String aNom) {
		this._nom = aNom;
	}

	public String getNom() {
		return this._nom;
	}

	public void setPrenom(String aPrenom) {
		this._prenom = aPrenom;
	}

	public String getPrenom() {
		return this._prenom;
	}

	public Vector<Oeuvre> getListeOeuvres() {
		return _listeOeuvres;
	}

	public void setListeOeuvres(Vector<Oeuvre> _listeOeuvres) {
		this._listeOeuvres = _listeOeuvres;
	}

	public void addOeuvre(Oeuvre oeuvre) {
		this._listeOeuvres.add(oeuvre);
		
	}
}