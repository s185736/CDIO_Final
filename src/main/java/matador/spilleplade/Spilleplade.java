package matador.spilleplade;

import gui_fields.GUI_Field;
import matador.spiller.Spiller;
import matador.Oversaetter;
import matador.spilleplade.felter.*;
import matador.spilleplade.genstand.Hus;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;


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
        LinkedList<Hus> felter = new LinkedList<>();
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
        LinkedList<Hus> felter = new LinkedList<>();
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
        LinkedList<Hus> felter = new LinkedList<>();
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
                new Hus(Oversaetter.t("spilleplade.felt.roedovrevej.beskrivelse"), 1, Color.BLUE).setUnderBeskrivelse("M1"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.hvidovrevej.beskrivelse"), 1, Color.BLUE).setUnderBeskrivelse("M1"),
                new IndkomstSkat(true).setUnderBeskrivelse("IndkomstSkat"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Hus(Oversaetter.t("spilleplade.felt.roskildevej.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("M1"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.valbylanggade.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.allegade.beskrivelse"), 1, Color.ORANGE).setUnderBeskrivelse("M1"),
                new Faengsel(true).setUnderBeskrivelse("På besøg"),
                new Hus(Oversaetter.t("spilleplade.felt.frederiksbergalle.beskrivelse"), 1, Color.GREEN).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.tuborgsquash.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.bulowsvej.beskrivelse"), 1, Color.GREEN).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.glkongevej.beskrivelse"), 1, Color.GREEN).setUnderBeskrivelse("M1"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Hus(Oversaetter.t("spilleplade.felt.bernstorffsvej.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("M1"),
                new Chancekort().setFeltNavn("Chance").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.hellerupsvej.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.strandvejen.beskrivelse"), 1, Color.GRAY).setUnderBeskrivelse("M1"),
                new Parkering().setFeltNavn("Parkering").setUnderBeskrivelse("P"),
                new Hus(Oversaetter.t("spilleplade.felt.trianglen.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("M1"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.osterbrogade.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.gronningen.beskrivelse"), 1, Color.RED).setUnderBeskrivelse("M1"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Hus(Oversaetter.t("spilleplade.felt.bredgade.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.kgsnytorv.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.cocacola.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.ostergade.beskrivelse"), 1, Color.WHITE).setUnderBeskrivelse("M1"),
                new Faengsel(false).setUnderBeskrivelse("Fængsel"),
                new Hus(Oversaetter.t("spilleplade.felt.amagertorv.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("M1"),
                new Hus(Oversaetter.t("spilleplade.felt.vimmelskaftet.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("M1"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.nygade.beskrivelse"), 1, Color.YELLOW).setUnderBeskrivelse("M1"),
                new Rederi(true).setUnderBeskrivelse("Scandlines"),
                new Chancekort().setFeltNavn("Prøv-Lykken").setUnderBeskrivelse("?"),
                new Hus(Oversaetter.t("spilleplade.felt.frederiksgade.beskrivelse"), 1, Color.PINK).setUnderBeskrivelse("M1"),
                new StatsSkat(true).setUnderBeskrivelse("Statsskat"),
                new Hus(Oversaetter.t("spilleplade.felt.raadhuspladsen.beskrivelse"), 1, Color.PINK).setUnderBeskrivelse("M1"),
        };
    }
}