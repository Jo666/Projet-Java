package élémentsDuJeu;


public class Trou {
	private Integer nombreDeGraines;
	private JoueurEnum joueurEnum;
	
	public Trou(int nbrGraines) {
		this.nombreDeGraines = nbrGraines;
	}




	public Integer getNombreDeGraines() {
		return nombreDeGraines;
	}


	public void setNombreDeGraines(Integer nombreDeGraines) {
		this.nombreDeGraines = nombreDeGraines;
	}


	public JoueurEnum getJoueurEnum() {
		return joueurEnum;
	}


	public void setJoueurEnum(JoueurEnum joueurEnum) {
		this.joueurEnum = joueurEnum;
	}


}
