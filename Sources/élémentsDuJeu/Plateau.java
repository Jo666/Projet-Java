package élémentsDuJeu;


import java.util.ArrayList;
import java.util.List;


public class Plateau {
	private List<Trou> trou;


	public Plateau() {
		trou = new ArrayList<>();
	}


	public List<Trou> getTrou() {
		return trou;
	}
	
	public void setTrou(List<Trou> trou) {
		this.trou = trou;
	}


}
