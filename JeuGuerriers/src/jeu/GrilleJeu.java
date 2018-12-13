package jeu;

/**
 * @author Kambayi Dimitri 
 * 		   Krasowski Marcin
 * 		   
 * 
 * Classe repr�sentant l'�tat du jeu des guerriers
 * 
 */

public class GrilleJeu {

	private Joueur[] tableJoueurs; //tableau des joueurs
	private Guerrier[] cases; //cases du jeu
	private int nombreDeTours; //nombre de tours � faire pour gagner
	
	/**
	 * initialise et construit la table des joueurs
	 * initialise et construit la table repr�sentant les cases du jeu
	 * initialise le nombre de tour � faire pour gagner
	 * 
	 * @param nombreJoueurs : le nombre de joueurs participants
	 * @param nombreDeCases : le nombre de cases du plateau de jeu
	 * @param nombreDeGuerriersParJoueur : le nombre de guerriers par joueurs
	 * @param nombreDeTours : le nombre de tours � effectuer pour gagner
	 * @param ptsVieDeDepart : le nombre de points de vie de d�part des guerriers
	 * @param nomDesJoueurs : tableau contenant le nom des joueurs
	 * 
	 */
	
	public GrilleJeu(int nombreJoueurs, int nombreDeCases, int nombreDeGuerriersParJoueur, int nombreDeTours,int ptsVieDeDepart,String[] nomDesJoueurs) {
		tableJoueurs = new Joueur[nombreJoueurs];
		for(int i=0;i<nombreJoueurs;i++) {
			tableJoueurs[i] = new Joueur(nomDesJoueurs[i],nombreDeGuerriersParJoueur,ptsVieDeDepart,i+1);
		}
		cases = new Guerrier[nombreDeCases];
		int index=0;
		for(int i=0;i<nombreDeGuerriersParJoueur;i++) {
			for(int j=0;j<nombreJoueurs;j++) {
				cases[index] = tableJoueurs[j].getGuerrier(i+1);
				index++;
			}
		}
		this.nombreDeTours = nombreDeTours;
	} 	

	/**
	 * renvoie le joueur dont le numero est passe en parametre
	 * 
	 * @param numJoueur : le numero d'ordre du joueur dans le jeu
	 * @return le joueur
	 * @throws IllegalArgumentException : numero invalide
	 */
	public Joueur donnerJoueur(int numJoueur) {
		if(numJoueur > tableJoueurs.length || numJoueur < 0) {
			throw new IllegalArgumentException();
		}
		
		return tableJoueurs[numJoueur-1];
	}

	/**
	 * Renvoie le guerrier se trouvant � la case dont le num�ro est numCase
	 * @param numCase : le num�ro de la case
	 * @return le guerrier se trouvant � la case dont le num�ro est numCase s'il y en a un
	 *         null sinon
	 */
	public Guerrier donnerPion(int numCase) {
		if(numCase>0 && numCase<cases.length) {
			return cases[numCase-1];
		}
		
		return null;
	}

	
	/**
	 * Bouge le guerrier se trouvant � la case num�ro caseDepart et le met � la case num�ro caseArriv�e
	 * 
	 * @param caseDepart : num�ro de la case o� se trouve le guerrier � bouger
	 * @param caseArrivee : num�ro de la case o� il faut mettre le guerrier
	 *
	 */
	
	public void bougerPion(int caseDepart, int caseArrivee) {
		cases[caseArrivee-1] = cases[caseDepart-1];
		cases[caseDepart-1]=null;
	}
	
	/*
	 * push un pion � la case num�ro 'location'
	 * 
	 * @param elem : l'�l�ment � placer 
	 * @param location : num�ro de la case
	 */
	public void pushPion(Guerrier elem, int location) {
		cases[location-1] = elem;
	}
	
	/**
	 * D�termine si le guerrier se trouvant sur la case num�ro numCase appartient au joueur joueur
	 * 
	 * @param numCase : num�ro de la case
	 * @param joueur : le joueur
	 * @return true  si le guerrier se trouvant � la case num�ro numCase appartient au joueur Joueur
	 *         false sinon
	 */
	public boolean estUnPionDuJoueur(int numCase, Joueur joueur) {
		Guerrier pion = donnerPion(numCase);
		
		if(pion==null) {
			return false;
		}
		
		if(pion.getNumJoueur()==joueur.getNumJoueur()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * supprime le guerrier se trouvant � la case num�ro numCase (vide la case)
	 * @param numCase : num�ro de la case
	 */
	
	public void supprimerPion(int numCase) {
		cases[numCase-1] = null;
	}
	
	/**
	 * Classe les guerriers encore un jeu d'abord selon le nombre tour d�j� effectu� (du plus grand au plus petit) et ensuite (si dans le m�me tour) par num�ro de case occup�e (du plus grand au plus petit)  
	 * @return un tableau de pion repr�sentant le classement des pions selon les crit�res ci-dessus
	 */
	public Guerrier[] classerGuerriers() {
		int guerriersEnVie = 0;
		for(int i=0;i<tableJoueurs.length;i++) {
			guerriersEnVie += tableJoueurs[i].nombreDeGuerriersEnVie();
		}
		Guerrier[] buffer = new Guerrier[guerriersEnVie];
		int add_no=0;
		for(int i=0;i<cases.length;i++) {
			Guerrier elem = donnerPion(i);
			if(elem!=null && elem.getPtsVie()>0) {
				int j=0;
				while(j<add_no) {
					if(buffer[j].getNombreDeTours()<=elem.getNombreDeTours()) {
						break;
					}
					j++;
				}
				for(int mov=add_no;mov>j;mov--) {
					buffer[mov] = buffer[mov-1];
				}
				buffer[j] = elem;
				add_no++;
			}
		}
		return buffer;
	}
	
	/*
	 * algorithme de recherche de case libre � partir de la case start(compris)
	 * 
	 * @param start : case de d�part de la recherche
	 */
	
	public int donnerCaseVide(int start) {
		for(int i=start;i<cases.length;i++) {
			if(cases[i]==null) {
				return i+1;
			}
		}
		
		for(int i=0;i<start;i++) {
			if(cases[i]==null) {
				return i+1;
			}
		}
		
		return -1;
	}

}
