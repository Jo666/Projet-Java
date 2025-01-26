package toutesVersions;


public class Affichage {


	private final int NB_TROUS = 12; // TODO getMaîtreDuJeu().getPlateau().getTrou().size();
	private static final String CROCHET_OUVRANT = "[";
	private static final String CROCHET_FERMANT = "]";
	private static final String ESPACE = " ";
	private static final String NORD = "NORD";
	private static final String SUD = "SUD";
	private static final String RAMASSE = "ramassé";
	private static final String BILLE_S = "bille(s).";
	private static final String BILLES = "billes.";
	private static final String JOUER = "de jouer.";
	private static final String GAGNE = "gagne la partie de Wali.";
	private static String A = "a";
	private MaitreDuJeuProcheAwale maitreDuJeu;


	/**
	 * L'Affichage sera informé par le Maître du Jeu de l'état de la partie.
	 * 
	 * @param maitreDuJeu
	 */
	
	
	
	//==============================
	// Méthodes publiques
	//==============================
		
	public Affichage(MaitreDuJeuProcheAwale maitreDuJeu) {
		this.maitreDuJeu = maitreDuJeu;
		afficher(maitreDuJeu);
	}




	public MaitreDuJeuProcheAwale getMaitreDuJeu() {
		return maitreDuJeu;
	}




	/**
	 * Affiche toutes les informations nécessaires au bon déroulement d'une partie,
	 * avec dans l'ordre: un plateau, le score des joueurs, le jeu en action.
	 * 
	 * @param maîtreDuJeu
	 */
	public void afficher(MaitreDuJeuProcheAwale maitreDuJeu) {
		afficherPlateau();
		afficherScores();
		afficherSession();
	}


	/**
	 * Renouvellement de l'Affichage Console, à chaque fois que le Maître du Jeu
	 * donne la main à un joueur qui ne jouait pas (Méthode optionnel et surtout
	 * améliorable)
	 */
	public void clear() {
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
	}


	
	/** A partir d'ici c'est privé */
	
	private void afficherPlateau() {
		System.out.println(buildIndexNord());
		System.out.println(buildValeursNord());
		System.out.println(buildValeursSud());
		System.out.println(buildIndexSud());
	}


	private void afficherScores() {
		this.maitreDuJeu.getJoueurs().forEach(joueur -> System.out
				.println(joueur.getJoueurEnum().name() + ESPACE + A + ESPACE + joueur.getScore() + ESPACE + BILLE_S));
	}


	private void afficherSession() {
		if (maitreDuJeu.getJoueurVainqueur() == null)
			System.out.println(buildInfosJeu());
		else
			System.out.println(buildFinDuJeu());
	}


	private StringBuffer buildInfosJeu() {
		StringBuffer infosJeu;
		infosJeu = new StringBuffer("");
		if (maitreDuJeu.getJoueurNeJouantPas().getGainExiste() == true)
			infosJeu.append("\n" + maitreDuJeu.getJoueurNeJouantPas().getJoueurEnum() + ESPACE + A + ESPACE + RAMASSE
					+ ESPACE + maitreDuJeu.getRèglesCommunes().getGainSurUnTour() + ESPACE + BILLES);
		infosJeu.append("\n" + A.toUpperCase() + ESPACE + this.maitreDuJeu.getJoueurJouantLeTour().getJoueurEnum()
				+ ESPACE + JOUER + ESPACE);
		return infosJeu;
	}


	private StringBuffer buildFinDuJeu() {
		StringBuffer finDuJeu;
		finDuJeu = new StringBuffer("\n" + maitreDuJeu.getJoueurVainqueur().getJoueurEnum().name() + ESPACE + GAGNE);
		return finDuJeu;
	}


	private StringBuffer buildIndexSud() {// Trous (1->6), ligne SUD
		StringBuffer indexSud;
		indexSud = new StringBuffer(SUD + ESPACE + ESPACE + ESPACE + ESPACE + ESPACE);
		for (int i = 0; i < (NB_TROUS / 2); i++) {
			indexSud.append(i + 1).append(ESPACE).append(ESPACE).append(ESPACE).append(ESPACE);
		}
		indexSud.append("\n");
		return indexSud;
	}


	private StringBuffer buildValeursSud() {
		StringBuffer valeursSud;
		valeursSud = new StringBuffer(ESPACE + ESPACE + ESPACE + ESPACE + ESPACE + ESPACE);
		for (int i = 0; i < (NB_TROUS / 2); i++) {
			if (this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines() >= 10) // TODO = Factoriser le code
				valeursSud.append(CROCHET_OUVRANT)
						.append(this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines())
						.append(CROCHET_FERMANT).append(ESPACE);
			else
				valeursSud.append(CROCHET_OUVRANT).append(ESPACE)
						.append(this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines())
						.append(CROCHET_FERMANT).append(ESPACE);
		}
		return valeursSud;
	}


	private StringBuffer buildValeursNord() {// Trous (7->12), ligne NORD
		StringBuffer valeursNord;
		valeursNord = new StringBuffer(ESPACE + ESPACE + ESPACE + ESPACE + ESPACE + ESPACE);
		for (int i = this.maitreDuJeu.getPlateau().getTrou().size() - 1; i >= ((NB_TROUS / 2)); i--) {
			if (this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines() >= 10)
				valeursNord.append(CROCHET_OUVRANT)
						.append(this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines())
						.append(CROCHET_FERMANT).append(ESPACE);
			else
				valeursNord.append(CROCHET_OUVRANT).append(ESPACE)
						.append(this.maitreDuJeu.getPlateau().getTrou().get(i).getNombreDeGraines())
						.append(CROCHET_FERMANT).append(ESPACE);
		}
		return valeursNord;
	}


	private StringBuffer buildIndexNord() {
		StringBuffer indexNord;
		indexNord = new StringBuffer(NORD + ESPACE + ESPACE + ESPACE);
		for (int i = this.maitreDuJeu.getPlateau().getTrou().size() - 1; i >= ((NB_TROUS / 2)); i--) {
			if (i >= 10)
				indexNord.append(i + 1).append(ESPACE).append(ESPACE).append(ESPACE);
			if (i < 10)
				indexNord.append(i + 1).append(ESPACE).append(ESPACE).append(ESPACE).append(ESPACE);
		}
		return indexNord;
	}
}
