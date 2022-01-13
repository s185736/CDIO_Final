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
    private HashMap<Spiller, Felt> spillerFeltMap = new HashMap<>();

    /*Konstruktør af Spilleplade.*/
    public Spilleplade()
    {
        this.felter = this.opretFelter();
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

    /*Alle felter for dette spilleplade.*/
    private Felt[] opretFelter() {
        return new Felt[] {
                /*Herunder skal felterne tilføjes.
                * Det er kronologisk rækkefølge.
                * HUSK BLOT AT FØLGE SAMME STRUKTUR
                * ÆNDRING AF FELTER FOREGÅR BÅDE I Spillerplade.java samt dansk.txt*/
                new Start().setFeltNavn("Start") .setUnderBeskrivelse("") .setBaggrundsFarve(Color.GREEN),
                new Ejendom(Oversaetter.t("spilleplade.felt.roedovrevej.beskrivelse"), 1, Color.BLUE).setUnderBeskrivelse("kr. 1.200"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.hvidovrevej.beskrivelse"), 1, Color.blue).setUnderBeskrivelse("kr. 1.200"),
                new IndkomstSkat(true).setUnderBeskrivelse("IndkomstSkat"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Ejendom(Oversaetter.t("spilleplade.felt.roskildevej.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("kr. 2.000"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.valbylanggade.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("kr. 2.000"),
                new Ejendom(Oversaetter.t("spilleplade.felt.allegade.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("kr. 2.400"),
                new Faengsel(true).setUnderBeskrivelse("På besøg"),
                new Ejendom(Oversaetter.t("spilleplade.felt.frederiksbergalle.beskrivelse"), 1, Color.yellow).setUnderBeskrivelse("kr. 2.800"),
                new Ejendom(Oversaetter.t("spilleplade.felt.tuborgsquash.beskrivelse"), 1, Color.red).setUnderBeskrivelse("kr. 3.000"),
                new Ejendom(Oversaetter.t("spilleplade.felt.bulowsvej.beskrivelse"), 1, Color.yellow).setUnderBeskrivelse("kr. 2.800"),
                new Ejendom(Oversaetter.t("spilleplade.felt.glkongevej.beskrivelse"), 1, Color.yellow).setUnderBeskrivelse("kr. 3.200"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Ejendom(Oversaetter.t("spilleplade.felt.bernstorffsvej.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("kr. 3.600"),
                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.hellerupsvej.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("kr. 3.600"),
                new Ejendom(Oversaetter.t("spilleplade.felt.strandvejen.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("kr. 4.000"),
                new Parkering().setFeltNavn("Parkering").setUnderBeskrivelse("P"),
                new Ejendom(Oversaetter.t("spilleplade.felt.trianglen.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("kr. 4.400"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.osterbrogade.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("kr. 4.400"),
                new Ejendom(Oversaetter.t("spilleplade.felt.gronningen.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("kr. 4.800"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Ejendom(Oversaetter.t("spilleplade.felt.bredgade.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("kr. 5.200"),
                new Ejendom(Oversaetter.t("spilleplade.felt.kgsnytorv.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("kr. 5.200"),
                new Ejendom(Oversaetter.t("spilleplade.felt.cocacola.beskrivelse"), 1, Color.red).setUnderBeskrivelse("kr. 3.000"),
                new Ejendom(Oversaetter.t("spilleplade.felt.ostergade.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("kr. 5.600"),
                new Faengsel(false).setUnderBeskrivelse("Fængsel"),
                new Ejendom(Oversaetter.t("spilleplade.felt.amagertorv.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("kr. 6.000"),
                new Ejendom(Oversaetter.t("spilleplade.felt.vimmelskaftet.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("kr. 6.000"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.nygade.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("kr. 6.400"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Ejendom(Oversaetter.t("spilleplade.felt.frederiksgade.beskrivelse"), 1, new Color(73, 18, 134)).setUnderBeskrivelse("kr. 7.000"),
                new StatsSkat(true).setUnderBeskrivelse("Statsskat"),
                new Ejendom(Oversaetter.t("spilleplade.felt.raadhuspladsen.beskrivelse"), 1, new Color(73, 18, 134)).setUnderBeskrivelse("kr. 8.000"),
        };
    }
}

