package jeu;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Lecharlier Loic
 * 		   Krasowski Marcin
 * 
 *         Classe d'ex�cution du jeu
 *
 */

public class JeuGuerrier {

	private static Scanner scanner = new Scanner(System.in);
	private static GrilleJeu grille; // gestion des donn�es du jeu
	private static PlateauDeJeu plateau; // panneau graphique du jeu
	private static De de = new DeTests();

	public static void main(String[] args) {

		System.out.println("Bienvenue au jeu des guerriers !");

		// configuration du jeu
		// A ne pas modifier
		/*
		System.out.print("Entrez le nombre de cases : ");
		int nbrCases = UtilitairesJeux.lireEntierPositif("Le nombre de cases doit �tre pair");
		System.out.print("Entrez le nombre de tours : ");
		int nbrTours = UtilitairesJeux.lireEntierPositif("Le nombre de tours est de minimum 1");
		System.out.print("Entrez le nombre de joueurs : ");
		int nbreJoueurs = UtilitairesJeux.lireEntierPositif("Le nombre de joueurs est de minimum 2");
		System.out.print("Entrez le nombre de guerriers par joueurs : ");
		int nbreJetons = UtilitairesJeux.lireEntierPositif("Le nombre de guerriers est de minimum 1");
		System.out.print("Entrez le nombre de points de vie des guerriers : ");
		int ptsVie = UtilitairesJeux.lireEntierPositif("Le nombre de points de vie est de minimum 1");

		String[] nomJoueurs = new String[nbreJoueurs];
		System.out.println("Entrez les noms des joueurs selon l'ordre du jeu : ");
		for (int numJoueur = 1; numJoueur <= nbreJoueurs; numJoueur++) {
			System.out.print("Entrez le nom du joueur " + numJoueur + " : ");
			nomJoueurs[numJoueur - 1] = UtilitairesJeux.lireStringNonVide("Le nom doit contenir au moins une lettre");
		}
		*/
		int nbrCases=30,nbrTours=2,nbreJoueurs=2,nbreJetons=5,ptsVie=5;
		String[] nomJoueurs = new String[] {"muu","jee"};
		grille = new GrilleJeu(nbreJoueurs, nbrCases, nbreJetons, nbrTours, ptsVie, nomJoueurs);
		plateau = new PlateauDeJeu(nbrCases,nbreJoueurs, nbreJetons, grille);
		plateau.afficherGuerriers(grille.classerGuerriers());
		
		int ordre=1;
		while(grille.checkWin()==null) {
			int jetDe = de.lancer();
			plateau.afficherResultatDe(jetDe);
			plateau.afficherInformation2("Tour du joueur: "+grille.donnerJoueur(ordre).getNom());
			
			int choix = plateau.jouer();
			
			plateau.afficherInformation("Case "+choix+" a été appuyé");
			
			checkGuerrierJoueur(ordre, choix, jetDe);
			
			plateau.actualiser(grille);
			plateau.afficherGuerriers(grille.classerGuerriers());
			ordre++;
			if(ordre>nbreJoueurs) {
				ordre=1;
			}
		}
		
		//afficher message win
		plateau.actualiser(grille);
		plateau.afficherGagnant(grille.checkWin());
		
	}
	
	private static void checkGuerrierJoueur(int joueur, int caseSelect, int jetDe) {
		Joueur player = grille.donnerJoueur(joueur);
		
		if(grille.estUnPionDuJoueur(caseSelect, player)) {
			grille.bougerPion(caseSelect, caseSelect+jetDe);
		}
	}

}
