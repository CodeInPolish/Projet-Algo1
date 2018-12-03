package jeu;

/**
 * @author Lecharlier Lo�c
 * 
 * Classe repr�sentant un pion du jeu des guerriers
 *
 */

public class Guerrier {
	private int nombreDeTours ; // nombre de tour d�j� effectu�s par le pion
	private int ptsVie; // nombre de point de vie du guerrier
	private int numJoueur; //num�ro du joueur � qui appartient le guerrier
	private int numGuerrier; // num�ro du guerrier
	
	/**
	 * Cr�e un pion num�ro numero du joueur dont le num�ro est numJoueur et qui a ptsVie point(s) de vie
	 * @param numJoueur : num�ro du joueur auquel appartient le pion
	 * @param ptsVie : nombre de points de vie de d�part du guerrier
	 * @param numGuerrier : num�ro du pion
	 */
	public Guerrier(int numJoueur, int ptsVie, int numGuerrier) {
		this.numJoueur = numJoueur;
		this.numGuerrier = numGuerrier;
		this.nombreDeTours = 0 ;
		this.ptsVie = ptsVie ;
	}
	
	/**
	 * renvoie le num�ro du pion
	 * @return le num�ro du pion
	 */
	public int getNumGuerrier() {
		return numGuerrier;
	}

	/**
	 * renvoie le num�ro du joueur auquel appartient le pion
	 * @return le num�ro du joueur auquel appartient le pion
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
	
	/**
	 * renvoie le nombre de tour d�j� effectu� par le pion
	 * @return le nombre de tour d�j� effectu� par le pion
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
	 * Mets le nombre de points de vie du guerrier � ptsVie ;
	 * @param ptsVie : nombre de points de vie de d�part du guerrier
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
