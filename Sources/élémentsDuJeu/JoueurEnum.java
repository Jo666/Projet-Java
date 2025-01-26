package élémentsDuJeu;


public enum JoueurEnum {
	NORD(0), SUD(1); // EST(2), OUEST(3), LUCIEN(4)...


	private Integer ordre;


	JoueurEnum(Integer ordre) {
		this.ordre = ordre;
	}


	/**
	 * Renseigne sur l'ordre permettant d'identifier un joueur parmi d'autres ou de
	 * le comparer avec un autre. Cet ordre sera toujours connu. Il a été
	 * attribué pour chaque joueur énuméré de la classe JoueurEnum
	 * 
	 * @return <b>ordre</b> : <i>l'ordre attribué à un joueur énuméré. [Nord = 0, Sud = 1]</i>
	 * 
	 */
	
	public Integer getOrdre() {
		return ordre;
	}
	
}
