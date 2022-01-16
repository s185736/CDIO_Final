package matador.spiller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpillerTest {
    private Spiller spiller;

    @Before
    public void setUp() throws Exception {
        this.spiller = new Spiller("Test Navn");
    }

    @Test
    public void getNavn() throws Exception {
        assertTrue(this.spiller.getNavn().equals("Test Navn"));
    }

    @Test
    public void getBalance() throws Exception {
        assertEquals(0, this.spiller.getBalance());
    }

    @Test
    public void tilfoejBalance() throws Exception {
        assertEquals(0, this.spiller.getBalance());
        this.spiller.tilfoejBalance(100);
        assertEquals(100, this.spiller.getBalance());
    }

    @Test
    public void getSpillerBrik() throws Exception {
        assertEquals(Spiller.SpillerBrik.BIL1, this.spiller.getType());
        this.spiller.setType(Spiller.SpillerBrik.BIL6);
        assertEquals(Spiller.SpillerBrik.BIL6, this.spiller.getType());
    }

}