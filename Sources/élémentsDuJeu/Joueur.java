package élémentsDuJeu;


public class Joueur {
	private JoueurEnum joueurEnum;
	private Integer score;
	private Boolean gainExiste;


	/**
	 * Donne au Joueur un nom énuméré identifiable, un score initial et la
	 * possibilité d'un gain.
	 * 
	 * @param joueurEnum : <i>choisira parmi un ensemble de noms de joueurs énumérés et
	 *                   identifiés.</i>
	 * @param score      : <i>valeur d'un score pour ce Joueur nommé.</i>
	 * @param pasDeGain  : <i>possiblité qu'un Joueur puisse obtenir un gain. Logiquement
	 *                   faux avant de jouer. Possiblement vrai à chaque fois qu'il
	 *                   jouera./</i>
	 */
	public Joueur(JoueurEnum joueurEnum, Integer score, Boolean pasDeGain) {
		this.joueurEnum = joueurEnum;
		this.score = score;
		this.gainExiste = pasDeGain;
	}


	/**
	 * Renseigne sur le nom d'un Joueur.
	 * 
	 * @return <b>joueurEnum</b> <i>identifie le Joueur par un nom de joueur énuméré [Nord,
	 *         Sud,...].</i>
	 */
	
	public JoueurEnum getJoueurEnum() {
		return joueurEnum;
	}


	/**
	 * Renseigne sur le score d'un joueur nommé. Peut servir à déterminer le vainqueur
	 * d'une partie si un joueur a un Score plus élevé qu'un autre Joueur.
	 * 
	 * @return <b>score</b> : <i>identifie la valeur du Score pour le Joueur nommé.</i>
	 */
	public Integer getScore() {
		return score;
	}


	/**
	 * Modifie le score d'un Joueur nommé. Peut changer selon les événénements,
	 * lorsque le Joueur joue et remporte un Gain.
	 * 
	 * @param score : <i>valeur d'un score pour ce Joueur nommé.</i>
	 */
	public void setScore(Integer score) {
		this.score = score;
	}


	/**
	 * Indique si le Joueur vient d'obtenir un Gain. Permet de ne pas afficher cette
	 * information si le Gain n'existe pas après avoir joué.
	 * 
	 * @return <b>gainExiste</b> : <code>true</code> si le Joueur remporte un Gain. Toujours <code>false</code> avant
	 *         de jouer.
	 */
	public Boolean getGainExiste() {
		return gainExiste;
	}


	/**
	 * Change la possibilité d'un GAin sur 
	 * @param gainExiste
	 */
	public void setGainExiste(Boolean gainExiste) {
		this.gainExiste = gainExiste;
	}


}
