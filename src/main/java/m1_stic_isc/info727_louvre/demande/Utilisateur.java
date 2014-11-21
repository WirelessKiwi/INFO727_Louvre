package m1_stic_isc.info727_louvre.demande;

/**
 * Repr�sente un utilisateur quelconque (historien ou photographe)
 * Permet de regrouper tous les utilisateurs dans une m�me liste
 * pour g�rer la connexion
 * 
 * @author Quentin
 *
 */
public class Utilisateur {
	private String identifiant;
	private String motDePasse;
	
	/**
	 * Cr�e un nouvel utilisateur ayant pour identifiant <login>
	 * et pour mot de passe <mdp>
	 * 
	 * @param login : identifiant de l'utilisateur
	 * @param mdp : mot de passe de l'utilisateur
	 */
	public Utilisateur (String login, String mdp){
		this.identifiant = login;
		this.motDePasse = mdp;
	}

	/**
	 * Compare le couple login/mdp avec le couple
	 * login/mdp de l'utilisateur
	 * 
	 * @param aLogin : le login � comparer
	 * @param aPassword : le mot de passe � comprarer
	 * 
	 * @return true si les couples correspondent
	 */
	public boolean connexion(String aLogin, String aPassword) {
		if(aLogin.equals(this.identifiant) && aPassword.equals(this.motDePasse)){
			return true;
		}
		return false;
	}
	
	/**
	 * Permet de savoir si un utilisateur est un photographe
	 * Red�finie dans Photographe
	 * @return true si l'utilisateur est un photographe
	 * 			false sinon
	 */
	public boolean isPhotographe(){
		return false;
	}
	
	/**
	 * Permet de savoir siun utiisateur est un historien
	 * Red�finie dans Historien
	 * @return true si l'utilisateur est un historien
	 * 			false sinon
	 */
	public boolean isHistorien(){
		return false;
	}

	/*=============================
	 * 		Getters & Setters
	=============================*/
	public void setIdentifiant(String aIdentifiant) {
		this.identifiant = aIdentifiant;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setMotDePasse(String aMotDePasse) {
		this.motDePasse = aMotDePasse;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}
}