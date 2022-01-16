package matador;

import org.junit.Test;

import static org.junit.Assert.*;

public class LagerStashTest {
    @Test
    public void addMaengde() throws Exception {
        LagerStash lagerStash;
        lagerStash = new LagerStash();
        lagerStash.tilfoejBeloeb(100);
        assertEquals(100,
                lagerStash.getMaengdeBeloeb());
    }

    @Test
    public void getMængdeBeloeb() throws Exception {
        LagerStash lagerStash1;
        lagerStash1 = new LagerStash();
        LagerStash lagerStash2;
        lagerStash2 = new LagerStash(100);
        assertEquals(0, lagerStash1.getMaengdeBeloeb());
        assertEquals(100, lagerStash2.getMaengdeBeloeb());
    }
}