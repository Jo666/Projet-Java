package wali;


import java.util.List;


import toutesVersions.RèglesCommunesProcheAwale;
import élémentsDuJeu.Joueur;
import élémentsDuJeu.Plateau;


public class RèglesWali extends RèglesCommunesProcheAwale{
	private static final int NB_TROUS = 12; // TODO


	public RèglesWali(Plateau plateau, List<Joueur> joueurs) {
		super(plateau);
	}


	@Override
	public boolean peutViderCeTrou(Joueur j, int indexTrou) {
		return !(trouVide(indexTrou));
	}
	
	
	//METHODES POUR LA FIN DE PARTIE
	public boolean finDePartie() {
		int cpt = 0, indexTrouNonVide = 0;
		for(int i = 0; i < NB_TROUS; i++) {
			if(trouVide(i))
				cpt++;
			else
				indexTrouNonVide = i;
		}
		if((cpt == NB_TROUS - 1 && nombreDeGrainesDansTrou(indexTrouNonVide) == 1 ||cpt == NB_TROUS))
			return true;
		return false; 
	}
}
