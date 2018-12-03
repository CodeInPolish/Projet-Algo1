package jeu;


/**
 * @author Lecharlier Loïc
 * 		   Krasowski Marcin
 *
 * Classe représentant un joueur du jeu des guerriers
 */

public class Joueur {
	
	private Guerrier[] guerriers ; // tableau contenant les guerriers du joueur
	private int numJoueur ; // numéro du joueur
	private String nom; //nom du joueur
	
	/**
	 * Crée un joueur du jeu des guerriers 
	 * @param nom : le nom du joueur
	 * @param nbGuerriers : le nombre de guerriers du joueur
	 * @param ptsVie : le nombre de points de vie de départ des guerriers du joueur
	 * @param numJoueur : numéro du joueur
	 */
	public Joueur(String nom, int nbGuerriers, int ptsVie, int numJoueur) {
		this.nom = nom;
		guerriers = new Guerrier[nbGuerriers];
		for(int i=0;i<nbGuerriers;i++) {
			guerriers[i] = new Guerrier(numJoueur,ptsVie,i+1);
		}
		this.numJoueur = numJoueur;
	}
	
	/**
	 * renvoie le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * renvoie le numéro du joueur
	 * @return le numéro du joueur
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
	
	
	/**
	 * Renvoie le guerrier numéro i du joueur
	 * @param numGuerrier : le numéro du joueur
	 * @return le guerrier numéro i du joueur
	 */
	public Guerrier getGuerrier(int numGuerrier) {
		if(numGuerrier>=0 && numGuerrier<guerriers.length) {
			return guerriers[numGuerrier];
		}
		return null;
	}
	
	/** 
	 * Détermine le nombre de guerrier encore en vie du joueur
	 * @return le nombre de guerrier encore en vie du joueur
	 */
	public int nombreDeGuerriersEnVie() {
		int count=0;
		for(int i=0;i<guerriers.length;i++) {
			if(guerriers[i].getPtsVie()>0) {
				count++;
			}
		}
		return count;
	}

}
