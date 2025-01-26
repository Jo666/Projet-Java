package awale;


import java.util.List;


import toutesVersions.RèglesCommunesProcheAwale;
import élémentsDuJeu.*;


public class RèglesAwale extends RèglesCommunesProcheAwale{
	
	//==============================
	// Attributs
	//==============================
	
	private static final int NB_TROUS = 12; // TODO
	private List<Joueur> joueurs;
	
	
	//==============================
	// Constructeurs					
	//==============================
	
	public RèglesAwale(Plateau plateau, List<Joueur> joueurs) {
		super(plateau);
		this.joueurs = joueurs;
	}
	


	public boolean peutViderCeTrou(Joueur j, int indexTrou) { // pour saisir un trou à vider pour le semer (Maître du Jeu)
		return (joueurs.indexOf(j) == getPlateau().getTrou().get(indexTrou).getJoueurEnum().getOrdre()
				&& !(trouVide(indexTrou)));
	}
	
	//METHODES POUR RECOLTER
	public boolean peutRécolter(Joueur j, int indexTrou) { // pour récolter
		return campAdverse(j, indexTrou) && grainesRécoltables(indexTrou)
				&& !risqueFamineNord() && !risqueFamineSud();
	}
	
	//METHODE POUR NE PAS AFFAMER
	
	private boolean risqueFamineSud() {// pour récolter
		int i, cpt;
		for (i = 0, cpt = 0;  i < 6; ++i)  // TODO Améliorer	MOCHE	
			if (trouVide(i))
				++cpt;
		if (cpt == 5)
				return true;
		else return false;
	}
	
	private boolean risqueFamineNord() {// pour récolter
		int i, cpt;
		for (i = 6, cpt = 0; i < 12; ++i)  // TODO Améliorer	MOCHE	
			if (trouVide(i))
				++cpt;
		if (cpt == 5)
			return true;
		else return false;
	}


	private boolean nordPeutNourrirSud() { // pour la fin de partie
		for (int i = NB_TROUS - 1; i >= (NB_TROUS / 2) /*- 1*/; --i)  // on teste NORD, index 11 jusqu'index 6
			if (nombreDeGrainesDansTrou(i) >= NB_TROUS - i /*&& famineSud(p)*/)// graines NORD insuffisantes pour atteindre SUD affamé
				return true;
		return false;
	}
		
	private boolean sudPeutNourrirNord() { // pour la fin de partie
		for (int j = (NB_TROUS / 2) - 1; j >= 0; --j)  // on teste SUD, index 5 jusqu'index 0
			if (nombreDeGrainesDansTrou(j) >= (NB_TROUS / 2) - j /*&& famineNord(p)*/) // graines SUD insuffisantes pour atteindre NORD affamé
				return true; // CAS très particulier
		return false; // CAS général
	}
	
	public boolean peutNourrir(int indexTrou) {
		if(indexTrou < NB_TROUS && indexTrou >= (NB_TROUS / 2))
			if (nombreDeGrainesDansTrou(indexTrou) < NB_TROUS - indexTrou)
				return false;
		if(indexTrou >= 0 && indexTrou < (NB_TROUS / 2))
			if (nombreDeGrainesDansTrou(indexTrou) < (NB_TROUS /2) - indexTrou)
				return false;
		
		return true;
	}
	
	
	//METHODES POUR LA FIN DE PARTIE
	public boolean famineNord() {// pour famine
		for (int i = NB_TROUS / 2; i <= NB_TROUS -1; i++) 
			if (!trouVide(i)) 
				return false;
		return true;
	}
	
	public boolean famineSud() {// pour famine
		for (int i = 0; i < NB_TROUS / 2; i++)
			if (!trouVide(i))
				return false;
		return true;
	}
	
	public boolean famine() { // TODO sert plus à rien pour la fin de partie
		return famineSud() || famineNord();
	}
	
	public boolean finDePartie() {
		return famineSud() && !nordPeutNourrirSud() || famineNord() && !sudPeutNourrirNord();
	}


}
