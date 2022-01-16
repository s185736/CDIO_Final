package matador.spilleplade.genstand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerningTest {
    Terning terning = new Terning();

    @Test
    public void terningKast(){
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
    void getFaceValue() {
    }

    @Test
    void getAntalSider() {
    }

}