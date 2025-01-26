package toutesVersions;


import élémentsDuJeu.Joueur;
import élémentsDuJeu.Plateau;


public abstract class RèglesCommunesProcheAwale extends règlesCommunes {
	private static final int NB_TROUS = 12;
	
	public boolean campAdverse(Joueur j, int indexTrou) { // pour pouvoir récolter
		return j.getJoueurEnum().getOrdre() != getPlateau().getTrou().get(indexTrou).getJoueurEnum().getOrdre();
	}


	public RèglesCommunesProcheAwale(Plateau plateau) {
		super(plateau);
	}
	
	public void récolter(Joueur j, int indexTrou) {
		gérerScore(j, indexTrou);
		viderUnTrou(indexTrou);
		
		if (peutRécolterAbondamment(j, indexTrou))
			récolteAbondante(j, indexTrou);	
	}
	
	public boolean peutRécolter(Joueur j, int indexTrou) { // pour récolter
		return campAdverse(j, indexTrou) && grainesRécoltables(indexTrou);
	}
	
	public void récolteAbondante(Joueur j, int indexTrou) {
		// Récolte Multiple par index décroissants, sens horaire, TODO commenter plus
		if (indexTrou > 0 && peutRécolter(j, indexTrou - 1)) 
			for (int indexMoins = indexTrou - 1; peutRécolterAbondammentSensHoraire(j, indexMoins); indexMoins--) {
				gérerScore(j, indexMoins);
				viderUnTrou(indexMoins);
			}
		// Récolte Multiple par index croissants, sens anti-horaire, sens dans lequel on joue...TODO
		if (indexTrou < NB_TROUS - 1 && peutRécolter(j, indexTrou + 1)) // par index croissants...
			for (int indexPlus = indexTrou + 1; peutRécolterAbondammentSensAntiHoraire(j, indexPlus); indexPlus++) {
				gérerScore(j, indexPlus);
				viderUnTrou(indexPlus);	
			}
		
	}
	
	public boolean peutRécolterAbondamment(Joueur j, int indexTrou) { // pour récolter
		return (indexTrou != NB_TROUS - 1 && peutRécolter(j, indexTrou + 1)
				|| indexTrou != 0 && peutRécolter(j, indexTrou - 1));
	}
	
	
	public boolean peutRécolterAbondammentSensHoraire(Joueur j, int indexTrou) {// indexTrou = indexMoins
		return (indexTrou >= 0 // CAS SUD : tantQue limite SudOuest pas atteinte
				&& !trouVide(indexTrou) // ET on s'arrête si trouVide
				&& peutRécolter(j, indexTrou) // ET tantQue le joueur peut
				&& indexTrou <= (NB_TROUS / 2) - 1 // ET tantQue CAS SUD (défensif)
				|| indexTrou > (NB_TROUS / 2) - 1 // CAS NORD : tantQue limite NordEst pas atteinte
						&& !trouVide(indexTrou) // ET on s'arrête si trouVide
						&& peutRécolter(j, indexTrou) // ET tantQue le joueur peut
						&& indexTrou > (NB_TROUS / 2) - 1); // ET tantQue CAS NORD (défensif)
	}


	public boolean peutRécolterAbondammentSensAntiHoraire(Joueur j, int indexTrou) {// indexTrou = indexMoins
		return (indexTrou < NB_TROUS // CAS NORD : tantQue la limite NordOuest n'est pas atteinte
				&& !trouVide(indexTrou) // ET on s'arrête si trouVide
				&& peutRécolter(j, indexTrou) // ET tant Que le joueur peut
				&& indexTrou > (NB_TROUS / 2) - 1 // ET tantQue CAS NORD (défensif)
				|| indexTrou <= (NB_TROUS / 2) - 1 // CAS SUD : tantQue limite SudEst pas atteinte
						&& !trouVide(indexTrou) // ET on s'arrête si trouVide
						&& peutRécolter(j, indexTrou) // ET tantQue le joueur peut
						&& indexTrou <= (NB_TROUS / 2) - 1); // ET tantQue CAS SUD (défensif)


	}
	
	public boolean grainesRécoltables(int indexTrou) { // pour pouvoir récolter simplement et abondamment
		return getPlateau().getTrou().get(indexTrou).getNombreDeGraines() == 2
				|| getPlateau().getTrou().get(indexTrou).getNombreDeGraines() == 3;
	}
}
