package toutesVersions;


import élémentsDuJeu.*;
public interface règlesDuJeu {
	void viderUnTrou(int indexTrou);
	boolean peutViderCeTrou(Joueur j, int indexTrou);
	void semer (Joueur j, int index);
	void récolter(Joueur j, int indexTrou);
	boolean grainesRécoltables(int indexTrou);
	void gérerScore(Joueur j, int indexTrou);
}
