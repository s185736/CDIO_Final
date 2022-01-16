package matador.spilleplade;

import matador.spilleplade.felter.Chancekort;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ChancekortTest {

    private Chancekort felt;

    @Before
    public void setUp() throws Exception {
        this.felt = new Chancekort();
    }

    @Test
    public void getGUIFelt() throws Exception {
        assertEquals(true, this.felt.getGUIFelt() != null);
    }

    @Test
    public void setNavn() throws Exception {
        this.felt.setFeltNavn("Opdatering af Navn.");
        assertEquals(true, this.felt.getFeltNavn().equals("Opdatering af Navn."));
    }


    @Test
    public void setBeskrivelse() throws Exception
    {
        this.felt.setBeskrivelse("Opdatering af Beskrivelsen.");
        assertEquals(true, this.felt.getBeskrivelse().equals("Opdatering af Beskrivelsen."));
    }


    @Test
    public void setUnderBeskrivelse() throws Exception
    {
        this.felt.setUnderBeskrivelse("Opdatering af Underbeskrivelsen.");
        assertEquals(true, this.felt.getUnderBeskrivelse().equals("Opdatering af Underbeskrivelsen."));
    }

}