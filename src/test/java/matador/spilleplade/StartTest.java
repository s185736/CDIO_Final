package matador.spilleplade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import matador.spilleplade.felter.Start;

public class StartTest {

    private Start startFelt;

    @Before
    public void setUp() throws Exception {
        this.startFelt = new Start();
    }

    @Test
    public void getGUIFelt() throws Exception {
        Assert.assertTrue(this.startFelt.getGUIFelt() != null);
    }

    @Test
    public void setNavn() throws Exception {
        this.startFelt.setFeltNavn("Opdatering af Navn.");
        Assert.assertTrue(this.startFelt.getFeltNavn().equals("Opdatering af Navn."));
    }


    @Test
    public void setBeskrivelse() throws Exception {
        this.startFelt.setBeskrivelse("Opdatering af Beskrivelsen.");
        Assert.assertEquals(true, this.startFelt.getBeskrivelse().equals("Opdatering af Beskrivelsen."));
    }

    @Test
    public void setUnderBeskrivelse() throws Exception {
        this.startFelt.setUnderBeskrivelse("Opdatering af Underbeskrivelsen.");
        Assert.assertTrue(this.startFelt.getUnderBeskrivelse().equals("Opdatering af Underbeskrivelsen."));
    }
}