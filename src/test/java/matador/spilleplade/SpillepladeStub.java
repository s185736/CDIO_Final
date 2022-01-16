package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spilleplade.felter.*;

import java.awt.Color;

public class SpillepladeStub extends Spilleplade {

    private Felt[] felter;
    {
        felter = new Felt[]{
                new Start(),
                new Ejendom("Test house", 1, Color.WHITE),
                new Chancekort(),
                new Faengsel(false),
                new Faengsel(true),
                new Parkering(),
        };
    }

    @Override
    public Felt[] getFelter()
    {
        return this.felter;
    }

    @Override
    public Felt getFeltVedNavn(String feltNavn) {
        Felt[] felts = this.felter;
        for (int i = 0; i < felts.length; i++) {
            Felt felt = felts[i];
            if (!felt.getFeltNavn().equals(feltNavn)) {
                continue;
            }
            return felt;
        }
        return null;
    }


    @Override
    public GUI_Field[] getGUIFelt() {
        GUI_Field[] felter = new GUI_Field[this.felter.length];
        for (int i = this.felter.length - 1; i >= 0; i--) {
            felter[i] = this.felter[i].getGUIFelt();
        }
        return felter;
    }
}