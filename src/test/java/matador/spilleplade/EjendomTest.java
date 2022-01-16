package matador.spilleplade;

import matador.spilleplade.felter.Ejendom;
import matador.spiller.Spiller;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.Assert.*;


public class EjendomTest {

    private Ejendom field;


    @Before
    public void setUp() throws Exception
    {
        this.field = new Ejendom("Test Navn", 10, Color.WHITE);
    }


    @Test
    public void getGUIFelt() throws Exception
    {
        assertEquals(true, this.field.getGUIFelt() != null);
    }


    @Test
    public void setNavn() throws Exception
    {
        this.field.setFeltNavn("Opdatering af Navn");
        assertEquals(true, this.field.getFeltNavn().equals("Opdatering af Navn"));
    }


    @Test
    public void setBeskrivelse() throws Exception
    {
        this.field.setBeskrivelse("Opdatering af Beskrivelse");
        assertEquals(true, this.field.getBeskrivelse().equals("Opdatering af Beskrivelse"));
    }


    @Test
    public void setUnderBeskrivelse() throws Exception
    {
        this.field.setUnderBeskrivelse("Opdatering af UnderBeskrivelse");
        assertEquals(true, this.field.getUnderBeskrivelse().equals("Opdatering af UnderBeskrivelse"));
    }


    @Test
    public void getLeje() throws Exception
    {
        assertEquals(10, this.field.getLeje());
    }


    @Test
    public void erEjet() throws Exception
    {
        assertEquals(false, this.field.erEjet());
    }


    @Test
    public void erEjetAfSpiller() throws Exception
    {
        Spiller spiller = new Spiller("Test Player");

        assertFalse(this.field.erEjetAfSpiller(spiller));

        this.field.setEjer(spiller);

        assertTrue(this.field.erEjetAfSpiller(spiller));
    }


    @Test
    public void setEjer() throws Exception
    {
        assertFalse(this.field.erEjet());

        this.field.setEjer(new Spiller("Test Player"));

        assertTrue(this.field.erEjet());
    }

}