package matador;

import matador.spilleplade.genstand.Terning;
import org.junit.Assert;
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
        this.terning.kast();
        assertTrue("Dice faceValue til: lav", this.terning.getFaceValue() >= 1);
        assertTrue("Dice faceValue til: høj", this.terning.getFaceValue() <= 6);
    }

    @Test
    public void getAntalSider() throws Exception {
        assertEquals(6, this.terning.getAntalSider());
    }

}