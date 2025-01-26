package awale;


import toutesVersions.*;




public class MaîtreDuJeuAwale extends MaitreDuJeuProcheAwale{


	private static final String IMPOSSIBLE ="Vous ne pouvez pas semer ce trou.";
	private static final String DE_1_A_12 = "Rentrez une valeur comprise entre 1 et 12";
	private static final String NOURRIS ="Nourris ton prochain !";
	
	private RèglesAwale règlesAwale;
	@SuppressWarnings("unused")
	private Affichage affichage;


	public MaîtreDuJeuAwale() {
		super();
		RèglesAwale règlesAwale = new RèglesAwale(getPlateau(), getJoueurs());
		setRèglesAwale(règlesAwale);
	}
	
	@Override
	public règlesCommunes getRèglesCommunes() {
		return règlesAwale;
	}


	public void setRèglesAwale(RèglesAwale règlesAwale) {
		this.règlesAwale = règlesAwale;
	}
	
	
	@Override
	public boolean saisieValide() {
		if (getTrouSaisi() >= 1 && getTrouSaisi() <= 12) {
			if (règlesAwale.famine()) {
				if(règlesAwale.peutNourrir(getTrouSaisi() - 1))
					return true;
				else {
					System.out.println(NOURRIS);
					return false;
				}
			}
			else if (règlesAwale.peutViderCeTrou(getJoueurJouantLeTour(), getTrouSaisi() - 1)){// si le joueur peut semer,
				return true;
			}
			System.out.println(IMPOSSIBLE);
			return false;
		}
		System.out.println(DE_1_A_12);
		return false;
	}


	public void jouer() { 
		do {
		affichage = new Affichage(this);
		saisirUnTrou();
		règlesAwale.semer(getJoueurJouantLeTour(),getTrouSaisi()-1);
		if(règlesAwale.peutRécolter(getJoueurJouantLeTour(), règlesAwale.getIndexTrouArrivé()))
			règlesAwale.récolter(getJoueurJouantLeTour(), règlesAwale.getIndexTrouArrivé());
		changementDeJoueur(getJoueurJouantLeTour());
			}while (!règlesAwale.finDePartie());
		
		changementDeJoueur(getJoueurJouantLeTour());
		aGagné();
		affichage = new Affichage(this);
	}
	
	
	
	




}
