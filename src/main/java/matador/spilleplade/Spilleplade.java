package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spiller.Spiller;
import matador.spilleplade.felter.*;
import matador.spilleplade.felter.Ejendom;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;


public class Spilleplade {

    /*Felter på spillepladen.*/
    private Felt[] felter;
    private HashMap<Spiller, Felt> spillerFeltMap = new HashMap<>();

    /*Konstruktør af Spilleplade.*/
    public Spilleplade()
    {
        //this.felter = this.opretFelter();
        this.felter = Felter.getFelter();
    }

    /*Få felterne af dette spileplade.*/
    public Felt[] getFelter()
    {
        return this.felter;
    }

    /*Få et specifikt felt med et navn på spillepladen.*/
    public Felt getFeltVedNavn(String feltNavn) {
        for (Felt felt : this.felter) {
            if (!felt.getFeltNavn().equals(feltNavn)) {
                continue;
            }
            return felt;
        }
        return null;
    }

    /*Få husfelter i en array med farvetype.*/
    public Ejendom[] getFeltvedTypeFarve(Color ...types) {
        LinkedList<Ejendom> felter = new LinkedList<>();
        Ejendom[] ejendomFelt = this.getHusFelt();
        for (int i = 0, husFeltLength = ejendomFelt.length; i < husFeltLength; i++) {
            Ejendom feltH = ejendomFelt[i];
            for (int j = 0; j < types.length; j++) {
                Color spillerBrik = types[j];
                if (feltH.getFarveType() != spillerBrik) {
                    continue;
                }
                felter.add(feltH);
            }
        }
        return felter.toArray(new Ejendom[] {});
    }

    /*Få husfelter i en array.*/
    public Ejendom[] getHusFelt() {
        LinkedList<Ejendom> felter = new LinkedList<>();
        Felt[] felts1 = this.felter;
        for (int i = 0, felts1Length = felts1.length; i < felts1Length; i++) {
            Felt felt = felts1[i];
            if (!(felt instanceof Ejendom)) {
                continue;
            }
            felter.add((Ejendom) felt);
        }
        return felter.toArray(new Ejendom[] {});
    }

    /*Få husfelter i en array som er ejet af en spiller.*/
    public Ejendom[] getFelterEjetAfSpiller(Spiller spiller) {
        LinkedList<Ejendom> felter = new LinkedList<>();
        Ejendom[] ejendomFelt = this.getHusFelt();
        for (int i = 0, husFeltLength = ejendomFelt.length; i < husFeltLength; i++) {
            Ejendom felt = ejendomFelt[i];
            if (!felt.erEjetAfSpiller(spiller)) {
                continue;
            }
            felter.add(felt);
        }
        return felter.toArray(new Ejendom[] {});
    }

    /*Få alle felterne der bruges i GUI_field.*/
    public GUI_Field[] getGUIFelt() {
        GUI_Field[] felter = new GUI_Field[this.felter.length];
        int i = this.felter.length - 1;
        while (i >= 0) {
            felter[i] = this.felter[i].getGUIFelt();
            i--;
        }
        return felter;
    }

    /*Tilføjer en spiller til map mellem spiller og field.*/
    public void addPlayer(Spiller spiller) {
        Felt startFelt = this.felter[0];
        this.spillerFeltMap.put(spiller, startFelt);
        startFelt.setBil(spiller);
    }

    /*Rykker spillere fra position til den næste position.*/
    public void rykSpiller(Spiller spiller, int feltAtRykke) {
        Felt nuvaerendeFelt = this.spillerFeltMap.get(spiller);
        int i = 0;
        while (i < this.felter.length) {
            if (nuvaerendeFelt != this.felter[i]) {
                i++;
            } else {
                int nasteFeltPosition = i + feltAtRykke;
                nasteFeltPosition = nasteFeltPosition % this.felter.length;
                Felt nextFelt = this.felter[nasteFeltPosition];
                this.rykSpiller(spiller, nextFelt);
                break;
            }
        }
    }

    /*Spillere der skal rykkes fra placering til den næste..*/
    public void rykSpiller(Spiller spiller, Felt feltAtRykke) {
        Felt nuvaerendeFelt = this.spillerFeltMap.get(spiller);
        this.spillerFeltMap.replace(spiller, feltAtRykke);
        nuvaerendeFelt.fjernBil(spiller);
        feltAtRykke.setBil(spiller);
    }

    /*Få felt som en spiller er placeret på.*/
    public Felt getSpillerFelt(Spiller spiller)
    {
        return this.spillerFeltMap.get(spiller);
    }

    /*Får information hvis den næste placering er før den nuværende felt.*/
    public boolean erFeltFoer(Felt nyFelt, Felt nuvaerendeFelt) {
        if (nyFelt != nuvaerendeFelt) {
            for (Felt felt : this.felter) {
                if (felt == nyFelt) {
                    return true;
                } else {
                    if (felt != nuvaerendeFelt) {
                        continue;
                    }
                    return false;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}

