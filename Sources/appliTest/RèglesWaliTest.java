package appliTest;


import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;


import org.junit.jupiter.api.Test;


import wali.RèglesWali;
import élémentsDuJeu.Plateau;
import élémentsDuJeu.Trou;


class RèglesWaliTest {


	RèglesWali r = new RèglesWali(new Plateau(), new ArrayList<>());
	
	@Test
	void testPlateau() {
		Plateau p = new Plateau();
		int NB_TROUS = 12;
		int NB_GRAINES_DEPART = 4;
		assertTrue(p.getTrou().isEmpty());
		p.setTrou(new ArrayList<>(NB_TROUS));
		Trou t = null;
		for (int i = 0; i < NB_TROUS; i++) {
			t = new Trou(NB_GRAINES_DEPART); 
			p.getTrou().add(t);
		}
		
		assertTrue(p.getTrou().contains(t));
		assertTrue(p.getTrou().get(0).getNombreDeGraines() == 4);
		assertTrue(p.getTrou().size() == 12);
		assertFalse(p.getTrou().stream().findAny().get().getNombreDeGraines() != 4);
		assertEquals(p.getTrou().get(0).getNombreDeGraines(), p.getTrou().get(11).getNombreDeGraines());
		
		p.getTrou().get(5).setNombreDeGraines(null);
		assertTrue(p.getTrou().get(5).getNombreDeGraines() == null);
		
		p.getTrou().get(5).setNombreDeGraines(50);
		assertNotEquals(p.getTrou().get(0).getNombreDeGraines(), p.getTrou().get(5).getNombreDeGraines());
	}	
	
	
}
