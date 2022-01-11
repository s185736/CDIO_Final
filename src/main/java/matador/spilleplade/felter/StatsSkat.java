package matador.spilleplade.felter;

import gui_fields.GUI_Street;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;

public class StatsSkat extends Felt {

    /*Herunder en konstruktør af StatsSkat med navn, beskrivelse og underbeskrivelse.*/
    public StatsSkat(boolean b) {
        super(
                Oversaetter.t("spilleplade.felt.statskat.feltnavn"),
                Oversaetter.t("spilleplade.felt.statskat.beskrivelse"),
                Oversaetter.t("spilleplade.felt.statskat.underBeskrivelse"),
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
        return (GUI_Street) this.gui_felt;
    }

    @Override
    public void koerHandling(Spil spil) {

    }
}