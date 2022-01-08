package matador.spilleplade.felter;

import gui_fields.GUI_Street;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;

public class Parkering extends Felt {

    /*Herunder en konstruktør af Parkering med navn, beskrivelse og underbeskrivelse.*/
    public Parkering() {
        super(
                Oversaetter.t("spilleplade.felt.parking.feltnavn"),
                Oversaetter.t("spilleplade.felt.parking.beskrivelse"),
                Oversaetter.t("spilleplade.felt.parking.underBeskrivelse"),
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