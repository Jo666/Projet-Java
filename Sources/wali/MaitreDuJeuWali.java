package wali;


import toutesVersions.*;




public class MaitreDuJeuWali extends MaitreDuJeuProcheAwale{
	
	private static final String IMPOSSIBLE ="Vous ne pouvez pas semer ce trou, r\u00E9essayez.";
	private static final String DE_1_A_12 = "Rentrez une valeur comprise entre 1 et 12";


	private RèglesWali règlesWali;
	@SuppressWarnings("unused")
	private Affichage affichage;
	
	public void setRèglesWali(RèglesWali règlesWali) {
		this.règlesWali = règlesWali;
	}
	
	public règlesCommunes getRèglesCommunes() {
		return règlesWali;
	}
	
	public MaitreDuJeuWali() {
		super();
		RèglesWali règlesWali = new RèglesWali(getPlateau(), getJoueurs());
		setRèglesWali(règlesWali);
	}
	
	public void jouer() { 
		do {
		affichage = new Affichage(this);
		saisirUnTrou();
		règlesWali.semer(getJoueurJouantLeTour(),getTrouSaisi()-1);
		if(règlesWali.peutRécolter(getJoueurJouantLeTour(), règlesWali.getIndexTrouArrivé()))
			règlesWali.récolter(getJoueurJouantLeTour(), règlesWali.getIndexTrouArrivé());
		changementDeJoueur(getJoueurJouantLeTour());
			}while (!règlesWali.finDePartie());
		
		changementDeJoueur(getJoueurJouantLeTour());
		aGagné();
		affichage = new Affichage(this);
	}
	
	@Override
	public boolean saisieValide() {
		if (getTrouSaisi() >= 1 && getTrouSaisi() <= 12) {
			if (règlesWali.peutViderCeTrou(getJoueurJouantLeTour(), getTrouSaisi() - 1)){// si le joueur peut semer,
				return true;
			}
			System.out.println(IMPOSSIBLE);
			return false;
		}
		System.out.println(DE_1_A_12);
		return false;
	}
}
