package matador.spilleplade.genstand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerningTest {

    private Terning terning;

    @Before
    public void setUp() throws Exception {
        this.terning = new Terning();
    }

    @After
    public void rivNed() throws Exception {
    }

    @Test
    public void kast() throws Exception {
      //Der bliver lavet en test på 1000 kast
        int antalKast = 1000;
        for(int i = 0; i < antalKast; i++) {
            //Gemmer resultatet fra metoden kast()
            terning.kast();
            int resultat = terning.getFaceValue();
            //assertTrue vil give fejlmelding hvis resultatet fra metoden kast() ikke er et tal fra 1 til 6.
            assertTrue(1 <= resultat && resultat <= 6);
        }
    }

    @Test
    public void getAntalSider() throws Exception {
        assertEquals(6, this.terning.getAntalSider());
    }

}