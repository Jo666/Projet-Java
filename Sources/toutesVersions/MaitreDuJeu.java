package toutesVersions;


import java.util.Scanner;


public abstract class MaitreDuJeu implements MaitreDuJeuInterface{
	private static final String NUMERIQUE ="Rentrez une valeur numérique";
	private int trouSaisi;
	
	public int getTrouSaisi() {
		return trouSaisi;
	}
	
	@Override
	public int saisirUnTrou() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			if (sc.hasNextInt()) { // si valeur numérique,
				trouSaisi = sc.nextInt(); 
				if(saisieValide())
					return trouSaisi - 1;
			}
			else {
				sc.next();
				System.out.println(NUMERIQUE);	
			}
		}while(true);
	}




}
