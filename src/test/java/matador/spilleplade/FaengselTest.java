package matador.spilleplade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import matador.spilleplade.felter.Faengsel;

import static org.junit.Assert.*;


public class FaengselTest {

    private Faengsel felt;


    @Before
    public void setUp() throws Exception {
        this.felt = new Faengsel(true);
    }

    @Test
    public void getGUIFelt() throws Exception {
        assertTrue(this.felt.getGUIFelt() != null);
    }

    @Test
    public void setNavn() throws Exception {
        this.felt.setFeltNavn("Opdatering af Navn.");
        assertEquals(true, this.felt.getFeltNavn().equals("Opdatering af Navn."));
    }

    @Test
    public void setBeskrivelse() throws Exception {
        this.felt.setBeskrivelse("Opdatering af Beskrivelsen.");
        assertEquals(true, this.felt.getBeskrivelse().equals("Opdatering af Beskrivelsen."));
    }

    @Test
    public void setUnderBeskrivelse() throws Exception {
        this.felt.setUnderBeskrivelse("Opdatering af Underbeskrivelsen.");
        assertEquals(true, this.felt.getUnderBeskrivelse().equals("Opdatering af Underbeskrivelsen."));
    }

    @Test
    public void erBesoeg() throws Exception {
        assertEquals(true, this.felt.isErPaaBesoeg());
        assertEquals(false, (new Faengsel(false)).isErPaaBesoeg());
    }
}