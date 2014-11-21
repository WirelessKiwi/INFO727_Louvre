package louvre.fiches;


import java.util.Vector;

public class Departement {
	private String _nom;
	private Vector<Oeuvre> _listeOeuvres = new Vector<Oeuvre>();
	
	public Departement(String nom){
		this._nom = nom;
	}
	
	/**
	 * Teste si le department correspond au nom
	 * passé en paramètre
	 * 
	 * @param departement
	 * @return true si le nom du department contient la chaine
	 * 			false sinon
	 */
	public boolean recherche(String departement) {
		departement = departement.toLowerCase();
		if(this._nom.toLowerCase().contains(departement)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getNom() {
		return _nom;
	}
	
	public void setNom(String _nom) {
		this._nom = _nom;
	}
	
	public Vector<Oeuvre> getOeuvres() {
		return _listeOeuvres;
	}
	
	public void setListeOeuvres(Vector<Oeuvre> _listeOeuvres) {
		this._listeOeuvres = _listeOeuvres;
	}
	
	public void addOeuvre(Oeuvre oeuvre)
	{
			this._listeOeuvres.add(oeuvre);
	}

	
}