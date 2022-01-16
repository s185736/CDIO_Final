package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spilleplade.felter.Ejendom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import matador.spilleplade.felter.Felt;

import static org.junit.Assert.*;


public class SpillepladeTest {

    private Spilleplade spilleplade;

    @Before
    public void setUp() throws Exception {
        this.spilleplade = new SpillepladeStub();
    }

    @Test
    public void getFelter() throws Exception {
        assertEquals(6, this.spilleplade.getFelter().length);
    }

    @Test
    public void getFeltVedNavn() throws Exception {
        Felt felt = this.spilleplade.getFeltVedNavn("Test house");
        assertEquals(true, felt != null && felt instanceof Ejendom);
    }

    @Test
    public void getGUIFelt() throws Exception {
        GUI_Field[] guiFields = this.spilleplade.getGUIFelt();
        assertEquals(this.spilleplade.getFelter().length, guiFields.length);
    }
}