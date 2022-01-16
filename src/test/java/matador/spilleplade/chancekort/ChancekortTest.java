package matador.spilleplade.chancekort;

import matador.spilleplade.felter.Chancekort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChancekortTest {

    private Chancekort felt;

    @Before
    public void setUp() throws Exception {
        this.felt = new Chancekort();
    }

    @Test
    public void getGUIFelt() throws Exception {
        Assert.assertTrue(this.felt.getGUIFelt() != null);
    }

    @Test
    public void setNavn() throws Exception {
        this.felt.setFeltNavn("Opdatering af Navn.");
        Assert.assertTrue(this.felt.getFeltNavn().equals("Opdatering af Navn."));
    }


    @Test
    public void setBeskrivelse() throws Exception
    {
        this.felt.setBeskrivelse("Opdatering af Beskrivelsen.");
        Assert.assertTrue(this.felt.getBeskrivelse().equals("Opdatering af Beskrivelsen."));
    }


    @Test
    public void setUnderBeskrivelse() throws Exception
    {
        this.felt.setUnderBeskrivelse("Opdatering af Underbeskrivelsen.");
        Assert.assertTrue(this.felt.getUnderBeskrivelse().equals("Opdatering af Underbeskrivelsen."));
    }

}