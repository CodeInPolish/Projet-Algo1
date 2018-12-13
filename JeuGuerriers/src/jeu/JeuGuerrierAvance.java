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

public class JeuGuerrierAvance {

	private static Scanner scanner = new Scanner(System.in);
	private static GrilleJeu grille; // gestion des donn�es du jeu
	private static PlateauDeJeu plateau; // panneau graphique du jeu
	private static De de = new DeJeu();

	public static void main(String[] args) {

		System.out.println("Bienvenue au jeu des guerriers !");

		// configuration du jeu
		// A ne pas modifier
		
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
		
		while(nbreJetons>nbrCases){
			System.out.print("Le nombre de guerriers doit etre inf�rieur � "+nbrCases+".");
			nbreJetons = UtilitairesJeux.lireEntierPositif("Le nombre de guerriers est de minimum 1");
		}

		String[] nomJoueurs = new String[nbreJoueurs];
		System.out.println("Entrez les noms des joueurs selon l'ordre du jeu : ");
		for (int numJoueur = 1; numJoueur <= nbreJoueurs; numJoueur++) {
			System.out.print("Entrez le nom du joueur " + numJoueur + " : ");
			nomJoueurs[numJoueur - 1] = UtilitairesJeux.lireStringNonVide("Le nom doit contenir au moins une lettre");
		}
		
		grille = new GrilleJeu(nbreJoueurs, nbrCases, nbreJetons, nbrTours, ptsVie, nomJoueurs);
		plateau = new PlateauDeJeu(nbrCases,nbreJoueurs, nbreJetons, grille);
		plateau.afficherGuerriers(grille.classerGuerriers());
		
		int playerIndex=0;
		int[] playersInGame = new int[nbreJoueurs];
		for(int i=0;i<nbreJoueurs;i++) {
			playersInGame[i] = i+1;
		}
		
		Joueur winner = checkWin(nbrTours, nbreJoueurs, grille.classerGuerriers());
		
		while(winner==null) {
			
			if(playersInGame[playerIndex]>0) {
				plateau.afficherJoueur(grille.donnerJoueur(playersInGame[playerIndex]));
				int jetDe = de.lancer();
				plateau.afficherResultatDe(jetDe);
				int choix = plateau.jouer();			
				int outcome = checkGuerrierJoueur(playersInGame[playerIndex], choix, jetDe, nbrCases);
				
				switch(outcome) {
					case 5: {playerIndex--;}break;
					default: break;
				}
				
			}
			
			plateau.actualiser(grille);
			Guerrier[] classementGuerrier = grille.classerGuerriers();
			plateau.afficherGuerriers(classementGuerrier);
			winner = checkWin(nbrTours, nbreJoueurs, classementGuerrier);
			playersInGame = eliminatePlayers(nbreJoueurs);
			
			do{
				playerIndex++;
				if(playerIndex>nbreJoueurs-1) {
					playerIndex=0;
				}
			}while(playersInGame[playerIndex]<0);
		}
		
		plateau.afficherGagnant(winner);
		plateau.afficherFinDuJeu();
		
	}
	
	private static int checkGuerrierJoueur(int joueur, int caseSelect, int jetDe, int nbrCases) {
		int returnVal=0;
		Joueur player = grille.donnerJoueur(joueur);
		
		if(grille.estUnPionDuJoueur(caseSelect, player)) {
			returnVal = generalLogic(caseSelect, jetDe, nbrCases);
			
		}
		else {
			plateau.afficherInformation("Tour perdu!");
		}
		
		return returnVal;
	}
	
	private static int generalLogic(int caseSelect, int jetDe, int nbrCases) {
		int returnVal=0;
		int caseArrivee=caseSelect+jetDe;
		boolean crossedFinish=false;
		if(caseArrivee>nbrCases) {
			caseArrivee = caseArrivee-nbrCases;
			crossedFinish=true;
		}
		Guerrier pionDeplace = grille.donnerPion(caseSelect);
		Guerrier pionArrivee = grille.donnerPion(caseArrivee);
		if(pionArrivee==null) {
			if(crossedFinish) {
				grille.donnerPion(caseSelect).ajouterUnTour();
			}
			grille.bougerPion(caseSelect, caseArrivee);
			returnVal=1;
		}
		else{
			returnVal=fight(pionDeplace, pionArrivee, caseSelect, caseArrivee, crossedFinish);
		}
		
		return returnVal;
	}
	
	private static int fight(Guerrier pionDeplace, Guerrier pionArrivee, int caseDepart, int caseArrivee, boolean crossedFinish) {
		int returnVal=0;
		int degatsPion1 = de.lancer();
		int degatsPion2 = de.lancer();
		
		pionArrivee.setPtsVie(pionArrivee.getPtsVie()-degatsPion1);
		pionDeplace.setPtsVie(pionDeplace.getPtsVie()-degatsPion2);
		
		if(pionDeplace.getPtsVie() <= 0 && pionArrivee.getPtsVie() <= 0) {
			grille.supprimerPion(caseDepart);
			grille.supprimerPion(caseArrivee);
			plateau.afficherInformation2("Les deux guerriers sont morts!");
			returnVal=2;
		}
		else if(pionDeplace.getPtsVie() <= 0) {
			grille.supprimerPion(caseDepart);
			plateau.afficherInformation2("Votre guerrier est mort!");
			returnVal=3;
		}
		else if(pionArrivee.getPtsVie() <= 0) {					
			if(crossedFinish) {
				pionDeplace.ajouterUnTour();
			}
			
			grille.pushPion(pionDeplace,caseArrivee);
			grille.supprimerPion(caseDepart);
			plateau.afficherInformation2("Le guerrier attaqué est mort!");
			returnVal=4;
		}
		else if(degatsPion1>degatsPion2) {
			plateau.afficherInformation2("Votre guerrier a gagné la bataille!");
			returnVal=5;
			grille.supprimerPion(caseDepart);
			int arrivee=grille.donnerCaseVide(caseArrivee);
			
			if(crossedFinish) {
				pionDeplace.ajouterUnTour();
			}
			
			grille.pushPion(pionDeplace,arrivee);
			if(arrivee<caseArrivee) {
				pionDeplace.ajouterUnTour();
			}
		}
		else {
			plateau.afficherInformation2("Le guerrier a infligé: "+degatsPion1+" et a subi: "+degatsPion2);
			returnVal=6;
		}
		return returnVal;
	}
	
	private static Joueur checkWin(int nbTours, int nbJoueurs, Guerrier[] tableauClasse) {
		int joueursPionsEnVie = 0;
		int joueurEnVie = 0;
		for(int i=1;i<nbJoueurs+1;i++) {
			if(grille.donnerJoueur(i).nombreDeGuerriersEnVie()>0) {
				joueursPionsEnVie++;
				joueurEnVie=i;
			}
		}
		if(joueursPionsEnVie==1) {
			return grille.donnerJoueur(joueurEnVie);
		}
		//si tableau trié, le meilleur pion sera en première position
		if(tableauClasse[0].getNombreDeTours()==nbTours) {
			return grille.donnerJoueur(tableauClasse[0].getNumJoueur());
		}
		return null;
	}
	
	private static int[] eliminatePlayers(int nbJoueurs) {
		int[] buffer = new int[nbJoueurs];
		for(int i=1;i<nbJoueurs+1;i++) {
			if(grille.donnerJoueur(i).nombreDeGuerriersEnVie()>0) {
				buffer[i-1]=i;
			}else {
				buffer[i-1]=-1;
			}
		}
		return buffer;
	}

}
