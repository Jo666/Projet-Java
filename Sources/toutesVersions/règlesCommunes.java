package toutesVersions;


import élémentsDuJeu.*;


public abstract class règlesCommunes implements règlesDuJeu {
	private static final int NB_TROUS = 12; // TODO
	private Plateau plateau;
	private int gainSurUnTour;
	private int indexTrouArrivé;
	
	//CONSTRUCTEUR
	public règlesCommunes(Plateau plateau) {
		this.plateau = plateau;
	}
	//GETTER ET SETTER
	public int getGainSurUnTour() {
		return gainSurUnTour;
	}
	
	public void setGainSurUnTour(int gainSurUnTour) {
		this.gainSurUnTour = gainSurUnTour;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}


	public int getIndexTrouArrivé() {
		return indexTrouArrivé;
	}


	@Override
	public void viderUnTrou(int indexTrou) { // pour semer et récolter
		plateau.getTrou().get(indexTrou).setNombreDeGraines(0);
	}


	@Override
	public void semer(Joueur j, int indexTrou) { // pour jouer un tour de jeu (Maître du jeu)
		int i = 0; // on prévoit une récolte
		indexTrouArrivé = 0;
		gainSurUnTour = 0;// réinitialisation du gain sur le tour en cours, changera si récolte


		if (nombreDeGrainesDansTrou(indexTrou) > NB_TROUS - (indexTrou + 1)) { // CAS 1, on déborde : 
																					// graines > (12-(index+1))
			if (nombreDeGrainesDansTrou(indexTrou) - (NB_TROUS - 1) > NB_TROUS - 1 - indexTrou) {// CAS 1.1, on
																									// déborde deux fois
				for (i = indexTrou; i < NB_TROUS; ++i) // on sème jusqu'au dernier trou (index #11)...
					ajouterUneGraine(i); // ... et on continuera sur le trou suivant (index #0)...
				for (i = 0; i < NB_TROUS; ++i) // on sème jusqu'au dernier trou (index #11)...
					ajouterUneGraine(i); // ... et on continuera sur le trou suivant (index #0)...
				for (i = 0; i < (nombreDeGrainesDansTrou(indexTrou) - 2) - (NB_TROUS - indexTrou) - (NB_TROUS -2); ++i) {
					ajouterUneGraine(i);
					indexTrouArrivé = i; // on prévoit une récolte
				}
			} else {// CAS 1.2, on déborde une fois
				for (i = indexTrou; i < NB_TROUS; ++i) // on sème jusqu'au dernier trou (index #11)...
					ajouterUneGraine(i); // ... et on continuera sur le trou suivant (index #0)...
				for (i = 0; i < nombreDeGrainesDansTrou(indexTrou) - (NB_TROUS - indexTrou); ++i) {
					ajouterUneGraine(i);
					indexTrouArrivé = i; // on prévoit une récolte
				}
			}


		} else // CAS 2, on ne déborde pas : graines < (12-(index+1))
			for (i = indexTrou; i < indexTrou + nombreDeGrainesDansTrou(indexTrou); ++i) {
				ajouterUneGraine(i);
				indexTrouArrivé = i; // on prévoit une récolte
			}
		viderUnTrou(indexTrou);
	}
	
	public void ajouterUneGraine(int indexTrou) { // pour semer
		plateau.getTrou().get(indexTrou).setNombreDeGraines(plateau.getTrou().get(indexTrou).getNombreDeGraines() + 1);
	}
	
	public int nombreDeGrainesDansTrou(int indexTrou) { 
		return getPlateau().getTrou().get(indexTrou).getNombreDeGraines();
	}
	
	public boolean trouVide(int indexTrou) { // pour pouvoir semer
		return getPlateau().getTrou().get(indexTrou).getNombreDeGraines() == 0;
	}
	
	public void gérerScore(Joueur j, int indexTrou) { // pour récolter
		j.setGainExiste(true); // pour l'affichage conditionnel d'un gain sur un tour
		setGainSurUnTour(getGainSurUnTour() + nombreDeGrainesDansTrou(indexTrou));// valeur du gain pour le tour
		j.setScore(j.getScore() + nombreDeGrainesDansTrou(indexTrou)); // valeur du score pour la partie
	}
	
}
