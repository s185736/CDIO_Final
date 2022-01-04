package test;

import matadorJuniorSpil.genstand.Terning;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class brugerTest {

    @Test
    public void terningKast(){
        // Opretter et eksempel af Terning klassen
        Terning terning = new Terning();
        // Der bliver lavet en test på 1000 kast
        int antalKast = 1000;
        for(int i = 0; i < antalKast; i++) {
            // Gemmer resultatet fra metoden kast()
            int resultat = terning.kast();
            // assertTrue vil give fejlmelding hvis resultatet fra metoden kast() ikke er et tal fra 1 til 6.
            assertTrue(1 <= resultat && resultat <= 6);
        }
    }

}
