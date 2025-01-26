package toutesVersions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import élémentsDuJeu.*;


public abstract class MaitreDuJeuProcheAwale extends MaitreDuJeu {
	private static final int NB_TROUS = 12;
	private static final int NB_GRAINES_DEPART = 4;
	private static final Predicate<Integer> estSud = valeur -> valeur < (NB_TROUS / 2);
	
	private List<Joueur> joueurs;
	private Joueur joueurJouantLeTour;
	private Joueur joueurNeJouantPas;
	private Joueur joueurVainqueur;
	private Plateau plateau;
	
	public abstract règlesCommunes getRèglesCommunes();
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public List<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}  
	


	public Joueur getJoueurJouantLeTour() {
		return joueurJouantLeTour;
	}
	
	public void setJoueurJouantLeTour(Joueur joueurJouantLeTour) {
		this.joueurJouantLeTour = joueurJouantLeTour;
	}


	public Joueur getJoueurNeJouantPas() {
		return joueurNeJouantPas;
	}
	
	public void setJoueurNeJouantPas(Joueur joueurNeJouantPas) {
		this.joueurNeJouantPas = joueurNeJouantPas;
	}


	public Joueur getJoueurVainqueur() {
		return joueurVainqueur;
	}
	
	public void setJoueurVainqueur(Joueur joueurVainqueur) {
		this.joueurVainqueur = joueurVainqueur;
	}


	public MaitreDuJeuProcheAwale() {
		super();
		plateau = new Plateau();
		joueurs = new ArrayList<>();
		initialiser();
	}
	
	private void initialiser() {
		initialiserPlateau();
		initialiserJoueurs();
	}
	
	public void initialiserPlateau() {
		getPlateau().setTrou(new ArrayList<>(NB_TROUS));// Une liste de n trous est prévue
		Trou trou;// Variable trou de type Trou
		
		for (int i = 0; i < NB_TROUS; i++) {
			trou = new Trou(NB_GRAINES_DEPART); // Creation de n trous// n graines pour chaque trou
			if (estSud.test(i)) 
				trou.setJoueurEnum(JoueurEnum.SUD);// Attribution des trous à SUD
			else 
				trou.setJoueurEnum(JoueurEnum.NORD);// Attribution des trous à NORD
			getPlateau().getTrou().add(trou);// Plateau composé de 12 trous contenant 4 graines chacun
			//trouVide = Predicate.isEqual(plateau.getTrou().get(i).trouVide());// NEW 04/01/22 Mais marche pas encore
		}
		
	}


	public void initialiserJoueurs() {
		getJoueurs().add(new Joueur(JoueurEnum.SUD, 0, false));// Ajout de SUD aux joueurs, score à 0 inchangé
		getJoueurs().add(new Joueur(JoueurEnum.NORD, 0, false));// Ajout de NORD aux joueurs, score à 0 inchangé
		Collections.shuffle(getJoueurs());// Mélange des joueurs
		setJoueurJouantLeTour(getJoueurs().stream().findFirst().get());// On ne choisit pas qui commence
		setJoueurNeJouantPas(getJoueurs().stream().skip(1).findFirst().get());// On choisit le joueur restant
		Comparator<Joueur> joueurComparator = Comparator.comparing(Joueur::getJoueurEnum,
				Comparator.comparing(JoueurEnum::getOrdre));// Comparaison 0 (NORD) et 1 (SUD) pour ordonner notre liste
															// de joueurs
		setJoueurs(getJoueurs().stream().sorted(joueurComparator).collect(Collectors.toList()));// Ordonnancement
																								// logique des joueurs
	}
	
	public void changementDeJoueur(Joueur j) {
		getJoueurNeJouantPas().setGainExiste(false);
		setJoueurJouantLeTour(getJoueurNeJouantPas());
		setJoueurNeJouantPas(j);
	}
	
	@Override
	public void aGagné() {
		if (getJoueurJouantLeTour().getScore() > getJoueurNeJouantPas().getScore())
			setJoueurVainqueur(getJoueurJouantLeTour());
		else 
			setJoueurVainqueur(getJoueurNeJouantPas());
	}
	
}
