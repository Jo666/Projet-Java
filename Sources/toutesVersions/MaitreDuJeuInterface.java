package toutesVersions;


import élémentsDuJeu.Joueur;


public interface MaitreDuJeuInterface {
	void jouer();
	int saisirUnTrou();
	boolean saisieValide();
	void changementDeJoueur(Joueur j);
	void aGagné();
	void initialiserPlateau();
	void initialiserJoueurs();
}
