package matador.spilleplade.felter;

import gui_fields.GUI_Refuge;
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
                Color.white,
                Color.orange
        );
    }

    /*{@inheritDoc}*/
    @Override
    protected GUI_Refuge opretGUIFelt()
    {
        return new GUI_Refuge();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Refuge getGUIFelt()
    {
        return (GUI_Refuge) this.gui_felt;
    }

    @Override
    public void koerHandling(Spil spil) {

    }
}