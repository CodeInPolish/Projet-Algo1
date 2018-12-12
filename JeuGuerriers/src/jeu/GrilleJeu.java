package jeu;

/**
 * @author Lecharlier Lo�c
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
		tableJoueurs = new Joueur[nombreJoueurs+1];
		for(int i=1;i<nombreJoueurs+1;i++) {
			tableJoueurs[i] = new Joueur(nomDesJoueurs[i-1],nombreDeGuerriersParJoueur,ptsVieDeDepart,i);
		}
		cases = new Guerrier[nombreDeCases+1];
		int index=1;
		for(int i=1;i<nombreDeGuerriersParJoueur+1;i++) {
			for(int j=1;j<nombreJoueurs+1;j++) {
				cases[index] = tableJoueurs[j].getGuerrier(i);
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
		
		return tableJoueurs[numJoueur];
	}

	/**
	 * Renvoie le guerrier se trouvant � la case dont le num�ro est numCase
	 * @param numCase : le num�ro de la case
	 * @return le guerrier se trouvant � la case dont le num�ro est numCase s'il y en a un
	 *         null sinon
	 */
	public Guerrier donnerPion(int numCase) {
		if(numCase>0 && numCase<cases.length) {
			return cases[numCase];
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
		int finish=0;
		boolean crossedFinish=false;
		Guerrier pionDeplace = donnerPion(caseDepart);
		if(pionDeplace!=null) {
			if(caseArrivee>cases.length-1) {
				crossedFinish=true;
				finish = caseArrivee-cases.length+1;
			}
			else {
				finish = caseArrivee;
			}
			
			Guerrier pionArrivee = donnerPion(finish);
			
			if(pionArrivee != null) {
				bougerPionRegles(pionDeplace,pionArrivee,caseDepart,finish,crossedFinish);
			}
			else {
				if(crossedFinish) {
					pionDeplace.ajouterUnTour();
				}
				
				pushPion(pionDeplace,finish);
				supprimerPion(caseDepart);
			}
		}
	}
	
	private void bougerPionRegles(Guerrier pionDeplace, Guerrier pionArrivee, int caseDepart, int finish, boolean crossedFinish) {
		De jet = new DeTests();
		int degatsPion1 = jet.lancer();
		int degatsPion2 = jet.lancer();
		
		pionArrivee.setPtsVie(pionArrivee.getPtsVie()-degatsPion1);
		pionDeplace.setPtsVie(pionDeplace.getPtsVie()-degatsPion2);
		
		if(pionDeplace.getPtsVie() <= 0 && pionArrivee.getPtsVie() <= 0) {
			supprimerPion(caseDepart);
			supprimerPion(finish);
		}
		else if(pionDeplace.getPtsVie() <= 0) {
			supprimerPion(caseDepart);
		}
		else if(pionArrivee.getPtsVie() <= 0) {					
			if(crossedFinish) {
				pionDeplace.ajouterUnTour();
			}
			
			pushPion(pionDeplace,finish);
			supprimerPion(caseDepart);
		}
		else if(degatsPion1>degatsPion2) {
			supprimerPion(caseDepart);
			int arrivee=donnerCaseVide(finish);
			
			if(arrivee>0) {
				pushPion(pionDeplace,arrivee);
				if(arrivee<finish) {
					pionDeplace.ajouterUnTour();
				}
			}	
		}
	}
	
	private void pushPion(Guerrier elem, int location) {
		cases[location] = elem;
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
		cases[numCase] = null;
	}
	
	/**
	 * Classe les guerriers encore un jeu d'abord selon le nombre tour d�j� effectu� (du plus grand au plus petit) et ensuite (si dans le m�me tour) par num�ro de case occup�e (du plus grand au plus petit)  
	 * @return un tableau de pion repr�sentant le classement des pions selon les crit�res ci-dessus
	 */
	public Guerrier[] classerGuerriers() {
		int guerriersEnVie = 0;
		for(int i=1;i<tableJoueurs.length;i++) {
			guerriersEnVie += tableJoueurs[i].nombreDeGuerriersEnVie();
		}
		Guerrier[] buffer = new Guerrier[guerriersEnVie];
		int add_no=0;
		for(int i=1;i<cases.length;i++) {
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
	
	public Joueur checkWin() {
		Guerrier[] guerriersEnVie = classerGuerriers();
		int joueursPionsEnVie = 0;
		int joueurEnVie = 0;
		for(int i=1;i<tableJoueurs.length;i++) {
			if(tableJoueurs[i].nombreDeGuerriersEnVie()>0) {
				joueursPionsEnVie++;
				joueurEnVie=i;
			}
		}
		if(joueursPionsEnVie==1) {
			return donnerJoueur(joueurEnVie);
		}
		//si tableau trié, le meilleur pion sera en première position
		if(guerriersEnVie[1].getNombreDeTours()==nombreDeTours) {
			return donnerJoueur(guerriersEnVie[1].getNumJoueur());
		}
		return null;
	}
	
	private int donnerCaseVide(int start) {
		for(int i=start;i<cases.length;i++) {
			if(cases[i]==null) {
				return i;
			}
		}
		
		for(int i=1;i<start;i++) {
			if(cases[i]==null) {
				return i;
			}
		}
		
		return -1;
	}

}
