package jeu;

/**
 * @author Lecharlier Loïc
 * 
 * Classe représentant l'état du jeu des guerriers
 * 
 */

public class GrilleJeu {

	private Joueur[] tableJoueurs; //tableau des joueurs
	private Guerrier[] cases; //cases du jeu
	private int nombreDeTours; //nombre de tours à faire pour gagner
	
	/**
	 * initialise et construit la table des joueurs
	 * initialise et construit la table représentant les cases du jeu
	 * initialise le nombre de tour à faire pour gagner
	 * 
	 * @param nombreJoueurs : le nombre de joueurs participants
	 * @param nombreDeCases : le nombre de cases du plateau de jeu
	 * @param nombreDeGuerriersParJoueur : le nombre de guerriers par joueurs
	 * @param nombreDeTours : le nombre de tours à effectuer pour gagner
	 * @param ptsVieDeDepart : le nombre de points de vie de départ des guerriers
	 * @param nomDesJoueurs : tableau contenant le nom des joueurs
	 * 
	 */
	
	public GrilleJeu(int nombreJoueurs, int nombreDeCases, int nombreDeGuerriersParJoueur, int nombreDeTours,int ptsVieDeDepart,String[] nomDesJoueurs) {
		//TODO
	} 	

	/**
	 * renvoie le joueur dont le numero est passe en parametre
	 * 
	 * @param numJoueur : le numero d'ordre du joueur dans le jeu
	 * @return le joueur
	 * @throws IllegalArgumentException : numero invalide
	 */
	public Joueur donnerJoueur(int numJoueur) {
		// TODO
		return null ;
	}

	/**
	 * Renvoie le guerrier se trouvant à la case dont le numéro est numCase
	 * @param numCase : le numéro de la case
	 * @return le guerrier se trouvant à la case dont le numéro est numCase s'il y en a un
	 *         null sinon
	 */
	public Guerrier donnerPion(int numCase) {
		return null ;
	}

	
	/**
	 * Bouge le guerrier se trouvant à la case numéro caseDepart et le met à la case numéro caseArrivée
	 * 
	 * @param caseDepart : numéro de la case où se trouve le guerrier à bouger
	 * @param caseArrivee : numéro de la case où il faut mettre le guerrier
	 *
	 */
	
	public void bougerPion(int caseDepart, int caseArrivee) {
		//TODO
	}
	
	/**
	 * Détermine si le guerrier se trouvant sur la case numéro numCase appartient au joueur joueur
	 * 
	 * @param numCase : numéro de la case
	 * @param joueur : le joueur
	 * @return true  si le guerrier se trouvant à la case numéro numCase appartient au joueur Joueur
	 *         false sinon
	 */
	public boolean estUnPionDuJoueur(int numCase, Joueur joueur) {
		//TODO
		return false ;
	}
	
	/**
	 * supprime le guerrier se trouvant à la case numéro numCase (vide la case)
	 * @param numCase : numéro de la case
	 */
	
	public void supprimerPion(int numCase) {
		//TODO
	}
	
	/**
	 * Classe les guerriers encore un jeu d'abord selon le nombre tour déjà effectué (du plus grand au plus petit) et ensuite (si dans le même tour) par numéro de case occupée (du plus grand au plus petit)  
	 * @return un tableau de pion représentant le classement des pions selon les critères ci-dessus
	 */
	public Guerrier[] classerGuerriers() {
		//TODO
		return null ;
	}

}
