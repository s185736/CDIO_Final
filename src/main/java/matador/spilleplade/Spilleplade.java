package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spiller.Spiller;
import matador.Oversaetter;
import matador.spilleplade.felter.*;
import matador.spilleplade.genstand.Hus;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Spilleplade {

    /*Felter på spillepladen.*/
    private Felt[] felts;
    private HashMap<Spiller, Felt> playerFieldMap = new HashMap<>();

    /*Konstruktør af Spilleplade.*/
    public Spilleplade()
    {
        this.felts = this.opretFelter();
    }

    /*Få felterne af dette spileplade.*/
    public Felt[] getFelter()
    {
        return this.felts;
    }

    /*Få et specifikt felt med et navn på spillepladen.*/
    public Felt getFeltVedNavn(String feltNavn) {
        for (Felt felt : this.felts) {
            if (!felt.getFeltNavn().equals(feltNavn)) {
                continue;
            }
            return felt;
        }
        return null;
    }

    /*Få husfelter i en array med farvetype.*/
    public Hus[] getFeltvedTypeFarve(Color ...types) {
        ArrayList<Hus> felter = new ArrayList<>();
        Hus[] husFelt = this.getHusFelt();
        for (int i = 0, husFeltLength = husFelt.length; i < husFeltLength; i++) {
            Hus feltH = husFelt[i];
            for (int j = 0; j < types.length; j++) {
                Color type = types[j];
                if (feltH.getFarveType() != type) {
                    continue;
                }
                felter.add(feltH);
            }
        }
        return felter.toArray(new Hus[] {});
    }

    /*Få husfelter i en array.*/
    public Hus[] getHusFelt() {
        ArrayList<Hus> felter = new ArrayList<>();
        Felt[] felts1 = this.felts;
        for (int i = 0, felts1Length = felts1.length; i < felts1Length; i++) {
            Felt felt = felts1[i];
            if (!(felt instanceof Hus)) {
                continue;
            }
            felter.add((Hus) felt);
        }
        return felter.toArray(new Hus[] {});
    }

    /*Få husfelter i en array som er ejet af en spiller.*/
    public Hus[] getFieldsOwnedByPlayer(Spiller spiller) {
        ArrayList<Hus> felter = new ArrayList<>();
        Hus[] husFelt = this.getHusFelt();
        for (int i = 0, husFeltLength = husFelt.length; i < husFeltLength; i++) {
            Hus field = husFelt[i];
            if (!field.erEjetAfSpiller(spiller)) {
                continue;
            }
            felter.add(field);
        }
        return felter.toArray(new Hus[] {});
    }

    /*Få alle felterne der bruges i GUI_field.*/
    public GUI_Field[] getGUIFelt() {
        GUI_Field[] felter = new GUI_Field[this.felts.length];
        int i = this.felts.length - 1;
        while (i >= 0) {
            felter[i] = this.felts[i].getGUIFelt();
            i--;
        }
        return felter;
    }

    /*Tilføjer en spiller til map mellem spiller og field.*/
    public void addPlayer(Spiller spiller) {
        Felt startFelt = this.felts[0];
        this.playerFieldMap.put(spiller, startFelt);
        startFelt.setBil(spiller);
    }

    /*Rykker spillere fra position til den næste position.*/
    public void movePlayer(Spiller spiller, int feltAtRykke) {
        Felt nuvaerendeFelt = this.playerFieldMap.get(spiller);
        int i = 0;
        while (i < this.felts.length) {
            if (nuvaerendeFelt != this.felts[i]) {
                i++;
            } else {
                int nextFieldPos = i + feltAtRykke;
                nextFieldPos = nextFieldPos % this.felts.length;
                Felt nextFelt = this.felts[nextFieldPos];
                this.movePlayer(spiller, nextFelt);
                break;
            }
        }
    }

    /*Spillere der skal rykkes fra placering til den næste..*/
    public void movePlayer(Spiller spiller, Felt feltAtRykke) {
        Felt nuvaerendeFelt = this.playerFieldMap.get(spiller);
        this.playerFieldMap.replace(spiller, feltAtRykke);
        nuvaerendeFelt.fjernBil(spiller);
        feltAtRykke.setBil(spiller);
    }

    /*Få felt som en spiller er placeret på.*/
    public Felt getPlayerField(Spiller spiller)
    {
        return this.playerFieldMap.get(spiller);
    }

    /*Får information hvis den næste placering er før den nuværende felt.*/
    public boolean isFieldBefore(Felt nyFelt, Felt nuvaerendeFelt) {
        if (nyFelt != nuvaerendeFelt) {
            for (Felt felt : this.felts) {
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

    /*Alle felter for dette spilleplade.*/
    private Felt[] opretFelter() {
        return new Felt[] {
                /*Herunder skal felterne tilføjes.
                * Det er kronologisk rækkefølge.
                * HUSK BLOT AT FØLGE SAMME STRUKTUR
                * ÆNDRING AF FELTER FOREGÅR BÅDE I Spillerplade.java samt dansk.txt*/
                new Start().setFeltNavn("Start") .setUnderBeskrivelse("") .setBaggrundsFarve(Color.GREEN),
                new Hus(Oversaetter.t("spilleplade.felt.burgerbaren.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.pizzeriaet.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),

                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.slikbutikken.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.iskiosken.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),

                new Faengsel(true).setUnderBeskrivelse("På besøg"),
                new Hus(Oversaetter.t("spilleplade.felt.museet.beskrivelse"), 2, Color.WHITE).setUnderBeskrivelse("M2"),
                new Hus(Oversaetter.t("spilleplade.felt.biblioteket.beskrivelse"), 2, Color.WHITE).setUnderBeskrivelse("M2"),

                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.skaterparken.beskrivelse"), 2, Color.WHITE).setUnderBeskrivelse("M3"),
                new Hus(Oversaetter.t("spilleplade.felt.swimmingpoolen.beskrivelse"), 2, Color.WHITE).setUnderBeskrivelse("M3"),

                new Parkering().setFeltNavn("Parkering").setUnderBeskrivelse("P"),
                new Hus(Oversaetter.t("spilleplade.felt.spillehallen.beskrivelse"), 3, Color.WHITE).setUnderBeskrivelse("M3"),
                new Hus(Oversaetter.t("spilleplade.felt.biografen.beskrivelse"), 3, Color.WHITE).setUnderBeskrivelse("M3"),

                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.legetøjsbutikken.beskrivelse"), 3, Color.WHITE).setUnderBeskrivelse("M3"),
                new Hus(Oversaetter.t("spilleplade.felt.dyrehandlen.beskrivelse"), 3, Color.WHITE).setUnderBeskrivelse("M3"),

                new Faengsel(false).setUnderBeskrivelse("Fængsel"),
                new Hus(Oversaetter.t("spilleplade.felt.bowlinghallen.beskrivelse"), 4, Color.WHITE).setUnderBeskrivelse("M4"),
                new Hus(Oversaetter.t("spilleplade.felt.zoo.beskrivelse"), 4, Color.WHITE).setUnderBeskrivelse("M4"),

                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.vandlandet.beskrivelse"), 5, Color.WHITE).setUnderBeskrivelse("M5"),
                new Hus(Oversaetter.t("spilleplade.felt.strandpromenaden.beskrivelse"), 5, Color.WHITE).setUnderBeskrivelse("M5"),
        };
    }
}