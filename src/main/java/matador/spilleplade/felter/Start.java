package matador.spilleplade.felter;

import gui_fields.GUI_Start;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;


public class Start extends Felt {

    /*Herunder en konstruktør af Start med navn, beskrivelse og underbeskrivelse.*/
    public Start() {
        super(
                Oversaetter.t("spilleplade.felt.start.feltnavn"),
                Oversaetter.t("spilleplade.felt.start.beskrivelse"),
                Oversaetter.t("spilleplade.felt.start.underBeskrivelse"),
                Color.GREEN,
                Color.BLACK
        );
    }


    /*{@inheritDoc}*/
    @Override
    protected GUI_Start opretGUIFelt()
    {
        return new GUI_Start();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Start getGUIFelt()
    {
        return (GUI_Start) this.guiField;
    }

    @Override
    public void koerHandling(Spil spil) {

    }
}