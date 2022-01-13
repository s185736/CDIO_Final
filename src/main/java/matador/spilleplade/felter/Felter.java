package matador.spilleplade.felter;

import matador.Oversaetter;

import java.awt.*;

public class Felter {
    /*Herunder skal felterne tilføjes.
     * Det er kronologisk rækkefølge.
     * HUSK BLOT AT FØLGE SAMME STRUKTUR
     * ÆNDRING AF FELTER FOREGÅR BÅDE I Spillerplade.java samt dansk.txt*/
    public static Felt[] getFelter(){
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

        return nyFelt;
    }
}




