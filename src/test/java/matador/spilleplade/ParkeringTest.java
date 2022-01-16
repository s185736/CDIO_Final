package matador.spilleplade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import matador.spilleplade.felter.Parkering;

public class ParkeringTest {

    private Parkering felt;

    @Before
    public void setUp() throws Exception {
        this.felt = new Parkering();
    }

    @Test
    public void getGUIFelt() throws Exception {
        Assert.assertEquals(true, this.felt.getGUIFelt() != null);
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
        this.felt.setUnderBeskrivelse("Opdatering af UnderBeskrivelsen.");
        Assert.assertEquals(true, this.felt.getUnderBeskrivelse().equals("Opdatering af UnderBeskrivelsen."));
    }
}