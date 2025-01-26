package appli;


import java.util.Scanner;


import awale.MaîtreDuJeuAwale;
import wali.MaitreDuJeuWali;


public class AppliMain {
	private static MaîtreDuJeuAwale awale;
	private static MaitreDuJeuWali wali;
	
		@SuppressWarnings("resource")
	    public static void main(String[] args) {
	        int variante;
	        System.out.println("A quelle variante voulez-vous jouer?\n[1] Awalé \n[2] Wali");
	        Scanner choix = new Scanner(System.in);
	        do {
		    if(choix.hasNextInt()) {
		    	variante = choix.nextInt();
		        switch (variante) {
		        case 1:
		        	System.out.println("Awale");
		            awale = new MaîtreDuJeuAwale();
		            awale.jouer();
		            break;
		        case 2:
		        	System.out.println("Wali");
		        	wali = new MaitreDuJeuWali();
			        wali.jouer();	            
			        break;
		        default:
		        	System.out.println("Saisissez une valeur entre 1 et 2");
		            break;
		        }
	       }
		   else {
			   choix.next();
			   System.out.println("Saisissez une valeur numérique");
		   }   
	    }while(true);
}
}
