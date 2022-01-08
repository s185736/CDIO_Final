package matador.spilleplade.felter;

import gui_fields.GUI_Chance;
import matador.spil.Spil;
import matador.Oversaetter;

import java.awt.Color;

public class Chancekort extends Felt {

    /*Konstrutør: Feltskifte med navn, beskrivelse osv..*/
    public Chancekort() {
        super(
                Oversaetter.t("spilleplade.felt.chance.feltnavn"),
                Oversaetter.t("spilleplade.felt.chance.beskrivelse"),
                Oversaetter.t("spilleplade.felt.chance.underBeskrivelse"),
                Color.BLACK,
                Color.WHITE
        );
    }

    /*{@inheritDoc}*/
    @Override
    protected GUI_Chance opretGUIFelt()
    {
        return new GUI_Chance();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Chance getGUIFelt()
    {
        return (GUI_Chance) this.guiField;
    }

    @Override
    public void koerHandling(Spil spil)
    {
        spil.getChanceDeck().draw().play(spil);
    }

}