package matador.spilleplade.felter;

import gui_fields.GUI_Street;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;

public class IndkomstSkat extends Felt {

    /*Herunder en konstruktør af IndkomstSkat med navn, beskrivelse og underbeskrivelse.*/
    public IndkomstSkat(boolean b) {
        super(
                Oversaetter.t("spilleplade.felt.indkomstskat.feltnavn"),
                Oversaetter.t("spilleplade.felt.indkomstskat.beskrivelse"),
                Oversaetter.t("spilleplade.felt.indkomstskat.underBeskrivelse"),
                Color.BLACK,
                Color.WHITE
        );
    }

    /*{@inheritDoc}*/
    @Override
    protected GUI_Street opretGUIFelt()
    {
        return new GUI_Street();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Street getGUIFelt()
    {
        return (GUI_Street) this.guiField;
    }

    @Override
    public void koerHandling(Spil spil) {

    }
}