package jeu;


/**
 * @author Lecharlier Lo�c
 * 		   Krasowski Marcin
 *
 * Classe repr�sentant un joueur du jeu des guerriers
 */

public class Joueur {
	
	private Guerrier[] guerriers ; // tableau contenant les guerriers du joueur
	private int numJoueur ; // num�ro du joueur
	private String nom; //nom du joueur
	
	/**
	 * Cr�e un joueur du jeu des guerriers 
	 * @param nom : le nom du joueur
	 * @param nbGuerriers : le nombre de guerriers du joueur
	 * @param ptsVie : le nombre de points de vie de d�part des guerriers du joueur
	 * @param numGuerrier : num�ro du joueur
	 */
	public Joueur(String nom, int nbGuerriers, int ptsVie, int numGuerrier) {
		this.nom = nom;
		guerriers = new Guerrier[nbGuerriers];
		for(int i=0;i<nbGuerriers;i++) {
			guerriers[i] = new Guerrier(numGuerrier,ptsVie,i+1);
		}
		this.numJoueur = numGuerrier;
	}
	
	/**
	 * renvoie le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * renvoie le num�ro du joueur
	 * @return le num�ro du joueur
	 */
	public int getNumJoueur() {
		return numJoueur ;
	}
	
	
	/**
	 * Renvoie le guerrier num�ro i du joueur
	 * @param numGuerrier : le num�ro du joueur
	 * @return le guerrier num�ro i du joueur
	 */
	public Guerrier getGuerrier(int numGuerrier) {
		//TODO
		return null ;
	}
	
	/** 
	 * D�termine le nombre de guerrier encore en vie du joueur
	 * @return le nombre de guerrier encore en vie du joueur
	 */
	public int nombreDeGuerriersEnVie() {
		//TODO
		return 0 ;
	}

}
