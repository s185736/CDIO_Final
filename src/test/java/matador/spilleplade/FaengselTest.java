package matador.spilleplade;

import matador.spilleplade.felter.Faengsel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FaengselTest {

    private Faengsel felt;


    @Before
    public void setUp() throws Exception {
        this.felt = new Faengsel(true);
    }

    @Test
    public void getGUIFelt() throws Exception {
        Assert.assertTrue(this.felt.getGUIFelt() != null);
    }

    @Test
    public void setNavn() throws Exception {
        this.felt.setFeltNavn("Opdatering af Navn.");
        Assert.assertEquals(true, this.felt.getFeltNavn().equals("Opdatering af Navn."));
    }

    @Test
    public void setBeskrivelse() throws Exception {
        this.felt.setBeskrivelse("Opdatering af Beskrivelsen.");
        Assert.assertEquals(true, this.felt.getBeskrivelse().equals("Opdatering af Beskrivelsen."));
    }

    @Test
    public void setUnderBeskrivelse() throws Exception {
        this.felt.setUnderBeskrivelse("Opdatering af Underbeskrivelsen.");
        Assert.assertEquals(true, this.felt.getUnderBeskrivelse().equals("Opdatering af Underbeskrivelsen."));
    }

    @Test
    public void erBesoeg() throws Exception {
        Assert.assertEquals(true, this.felt.isErPaaBesoeg());
        Assert.assertEquals(false, (new Faengsel(false)).isErPaaBesoeg());
    }
}