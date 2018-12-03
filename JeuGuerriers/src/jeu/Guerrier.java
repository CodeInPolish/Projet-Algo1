package jeu;

/**
 * @author Lecharlier Loïc
 * 
 * Classe représentant un pion du jeu des guerriers
 *
 */

public class Guerrier {
	private int nombreDeTours ; // nombre de tour déjà effectués par le pion
	private int ptsVie; // nombre de point de vie du guerrier
	private int numJoueur; //numéro du joueur à qui appartient le guerrier
	private int numGuerrier; // numéro du guerrier
	
	/**
	 * Crée un pion numéro numero du joueur dont le numéro est numJoueur et qui a ptsVie point(s) de vie
	 * @param numJoueur : numéro du joueur auquel appartient le pion
	 * @param ptsVie : nombre de points de vie de départ du guerrier
	 * @param numGuerrier : numéro du pion
	 */
	public Guerrier(int numJoueur, int ptsVie, int numGuerrier) {
		this.numJoueur = numJoueur;
		this.numGuerrier = numGuerrier;
		this.nombreDeTours = 0 ;
		this.ptsVie = ptsVie ;
	}
	
	/**
	 * renvoie le numéro du pion
	 * @return le numéro du pion
	 */
	public int getNumGuerrier() {
		return numGuerrier;
	}

	/**
	 * renvoie le numéro du joueur auquel appartient le pion
	 * @return le numéro du joueur auquel appartient le pion
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
	
	/**
	 * renvoie le nombre de tour déjà effectué par le pion
	 * @return le nombre de tour déjà effectué par le pion
	 */
	public int getNombreDeTours() {
		return this.nombreDeTours ;
	}

	
	/**
	 * renvoie le nombre de points de vie du guerrier
	 * @return le nombre de points de vie du guerrier
	 */
	public int getPtsVie() {
		return ptsVie ;
	}
	
	/**
	 * Mets le nombre de points de vie du guerrier à ptsVie ;
	 * @param ptsVie : nombre de points de vie de départ du guerrier
	 */
	public void setPtsVie(int ptsVie) {
		this.ptsVie = ptsVie;
	}
	
	/**
	 * augmente de 1 le nombre de tour
	 */
	
	public void ajouterUnTour() {
		this.nombreDeTours++ ;
	}

}
