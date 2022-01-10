package matador.spilleplade.felter;

import gui_fields.GUI_Street;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;

public class Rederi extends Felt {

    /*Herunder en konstruktør af Rederi med navn, beskrivelse og underbeskrivelse.*/
    /*Der skal stadig tilføjes de andre linjer...
    * scandlines2, 3 samt molslinien*/
    public Rederi(boolean b) {
        super(
                Oversaetter.t("spilleplade.felt.scandlines1.feltnavn"),
                Oversaetter.t("spilleplade.felt.scandlines1.beskrivelse"),
                Oversaetter.t("spilleplade.felt.scandlines1.underBeskrivelse"),
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