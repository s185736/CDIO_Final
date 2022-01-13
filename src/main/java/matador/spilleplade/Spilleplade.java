package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spiller.Spiller;
import matador.Oversaetter;
import matador.spilleplade.felter.*;
import matador.spilleplade.felter.Ejendom;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;


public class Spilleplade {

    /*Felter på spillepladen.*/
    private Felt[] felter;
    private HashMap<Spiller, Felt> playerFieldMap = new HashMap<>();

    /*Konstruktør af Spilleplade.*/
    public Spilleplade()
    {
        this.felter = Felter.getFelter2();
        //this.felter = this.opretFelter();
        //this.felter = Felter.getFelter2();
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
                Color type = types[j];
                if (feltH.getFarveType() != type) {
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
    public Ejendom[] getFieldsOwnedByPlayer(Spiller spiller) {
        LinkedList<Ejendom> felter = new LinkedList<>();
        Ejendom[] ejendomFelt = this.getHusFelt();
        for (int i = 0, husFeltLength = ejendomFelt.length; i < husFeltLength; i++) {
            Ejendom field = ejendomFelt[i];
            if (!field.erEjetAfSpiller(spiller)) {
                continue;
            }
            felter.add(field);
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
        this.playerFieldMap.put(spiller, startFelt);
        startFelt.setBil(spiller);
    }

    /*Rykker spillere fra position til den næste position.*/
    public void movePlayer(Spiller spiller, int feltAtRykke) {
        Felt nuvaerendeFelt = this.playerFieldMap.get(spiller);
        int i = 0;
        while (i < this.felter.length) {
            if (nuvaerendeFelt != this.felter[i]) {
                i++;
            } else {
                int nextFieldPos = i + feltAtRykke;
                nextFieldPos = nextFieldPos % this.felter.length;
                Felt nextFelt = this.felter[nextFieldPos];
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

    /*Alle felter for dette spilleplade.*/
/*
    private Felt[] opretFelter() {
        Felt[] nyFelt = new Felt[40];
        nyFelt[0] = new Start().setFeltNavn("Start").setUnderBeskrivelse("").setBaggrundsFarve(Color.GREEN);
        nyFelt[1] = new Ejendom(Oversaetter.t("spilleplade.felt.roedovrevej.beskrivelse"), 1200, Color.BLUE).setUnderBeskrivelse("kr. 1.200");
        nyFelt[2] = new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?");
        nyFelt[3] = new Ejendom(Oversaetter.t("spilleplade.felt.hvidovrevej.beskrivelse"), 1200, Color.blue).setUnderBeskrivelse("kr. 1.200");
        nyFelt[4] = new IndkomstSkat(true).setUnderBeskrivelse("IndkomstSkat");
        nyFelt[5] = new Rederi(true).setUnderBeskrivelse("Scandlines");
        nyFelt[6] = new Ejendom(Oversaetter.t("spilleplade.felt.roskildevej.beskrivelse"), 2000, Color.ORANGE).setUnderBeskrivelse("kr. 2.000");
        nyFelt[7] = new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?");
        nyFelt[8] = new Ejendom(Oversaetter.t("spilleplade.felt.valbylanggade.beskrivelse"), 2000, Color.ORANGE).setUnderBeskrivelse("kr. 2.000");
        nyFelt[9] = new Ejendom(Oversaetter.t("spilleplade.felt.allegade.beskrivelse"), 2400, Color.ORANGE).setUnderBeskrivelse("kr. 2.400");
        nyFelt[10] = new Faengsel(true).setUnderBeskrivelse("På besøg");
        nyFelt[11] = new Ejendom(Oversaetter.t("spilleplade.felt.frederiksbergalle.beskrivelse"), 2800, Color.yellow).setUnderBeskrivelse("kr. 2.800");
        nyFelt[12] = new Ejendom(Oversaetter.t("spilleplade.felt.tuborgsquash.beskrivelse"), 3000, Color.red).setUnderBeskrivelse("kr. 3.000");
        nyFelt[13] = new Ejendom(Oversaetter.t("spilleplade.felt.bulowsvej.beskrivelse"), 2800, Color.yellow).setUnderBeskrivelse("kr. 2.800");
        nyFelt[14] = new Ejendom(Oversaetter.t("spilleplade.felt.glkongevej.beskrivelse"), 3200, Color.yellow).setUnderBeskrivelse("kr. 3.200");
        nyFelt[15] = new Rederi(true).setUnderBeskrivelse("Scandlines");
        nyFelt[16] = new Ejendom(Oversaetter.t("spilleplade.felt.bernstorffsvej.beskrivelse"), 3600, Color.GRAY).setUnderBeskrivelse("kr. 3.600");
        nyFelt[17] = new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?");
        nyFelt[18] = new Ejendom(Oversaetter.t("spilleplade.felt.hellerupsvej.beskrivelse"), 3600, Color.GRAY).setUnderBeskrivelse("kr. 3.600");
        nyFelt[19] = new Ejendom(Oversaetter.t("spilleplade.felt.strandvejen.beskrivelse"), 4000, Color.GRAY).setUnderBeskrivelse("kr. 4.000");
        nyFelt[20] = new Parkering().setFeltNavn("Parkering").setUnderBeskrivelse("P");
        nyFelt[21] = new Ejendom(Oversaetter.t("spilleplade.felt.trianglen.beskrivelse"), 4400, Color.RED).setUnderBeskrivelse("kr. 4.400");
        nyFelt[22] = new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?");
        nyFelt[23] = new Ejendom(Oversaetter.t("spilleplade.felt.osterbrogade.beskrivelse"), 4400, Color.RED).setUnderBeskrivelse("kr. 4.400");
        nyFelt[24] = new Ejendom(Oversaetter.t("spilleplade.felt.gronningen.beskrivelse"), 4800, Color.RED).setUnderBeskrivelse("kr. 4.800");
        nyFelt[25] = new Rederi(true).setUnderBeskrivelse("Scandlines");
        nyFelt[26] = new Ejendom(Oversaetter.t("spilleplade.felt.bredgade.beskrivelse"), 5200, Color.WHITE).setUnderBeskrivelse("kr. 5.200");
        nyFelt[27] = new Ejendom(Oversaetter.t("spilleplade.felt.kgsnytorv.beskrivelse"), 5200, Color.WHITE).setUnderBeskrivelse("kr. 5.200");
        nyFelt[28] = new Ejendom(Oversaetter.t("spilleplade.felt.cocacola.beskrivelse"), 3000, Color.red).setUnderBeskrivelse("kr. 3.000");
        nyFelt[29] = new Ejendom(Oversaetter.t("spilleplade.felt.ostergade.beskrivelse"), 5600, Color.WHITE).setUnderBeskrivelse("kr. 5.600");
        nyFelt[30] = new Faengsel(false).setUnderBeskrivelse("Fængsel");
        nyFelt[31] = new Ejendom(Oversaetter.t("spilleplade.felt.amagertorv.beskrivelse"), 6000, Color.YELLOW).setUnderBeskrivelse("kr. 6.000");
        nyFelt[32] = new Ejendom(Oversaetter.t("spilleplade.felt.vimmelskaftet.beskrivelse"), 6000, Color.YELLOW).setUnderBeskrivelse("kr. 6.000");
        nyFelt[33] = new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?");
        nyFelt[34] = new Ejendom(Oversaetter.t("spilleplade.felt.nygade.beskrivelse"), 6400, Color.YELLOW).setUnderBeskrivelse("kr. 6.400");
        nyFelt[35] = new Rederi(true).setUnderBeskrivelse("Scandlines");
        nyFelt[36] = new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?");
        nyFelt[37] = new Ejendom(Oversaetter.t("spilleplade.felt.frederiksgade.beskrivelse"), 7000, new Color(73, 18, 134)).setUnderBeskrivelse("kr. 7.000");
        nyFelt[38] = new StatsSkat(true).setUnderBeskrivelse("Statsskat");
        nyFelt[39] = new Ejendom(Oversaetter.t("spilleplade.felt.raadhuspladsen.beskrivelse"), 8000, new Color(73, 18, 134)).setUnderBeskrivelse("kr. 8.000");

        return nyFelt;};*/
}



