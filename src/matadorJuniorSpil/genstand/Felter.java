package matadorJuniorSpil.genstand;

import java.awt.*;

public class Felter {

    public static Felt[] getFelter() {

        Felt[] felter = new Felt[24];
        felter[0] = new Felt();
        felter[0].setFeltNavn("Start");
        felter[0].setPris(-1);
        felter[0].setBeskrivelse("Navnet giver sig selv");
        felter[0].setPrisUdskrift("");

        felter[1] = new Felt();
        felter[1].setFeltNavn("Burgerbaren");
        felter[1].setPris(1);
        felter[1].setBeskrivelse("Slidte men loyale McDonalds <3");
        felter[1].setFarve(Color.DARK_GRAY);
        felter[1].setPrisUdskrift("M1");


        felter[2] = new Felt();
        felter[2].setFeltNavn("Pizzeriaet");
        felter[2].setPris(1);
        felter[2].setBeskrivelse("Stedet med sindssyg salatpizza med kebab");
        felter[2].setFarve(Color.DARK_GRAY);
        felter[2].setPrisUdskrift("M1");


        felter[3] = new Felt();
        felter[3].setFeltNavn("Chance");
        felter[3].setPris(-1);
        felter[3].setBeskrivelse("Ludomani på høj plan");
        felter[3].setPrisUdskrift("");

        felter[4] = new Felt();
        felter[4].setFeltNavn("Slikbutikken");
        felter[4].setPris(1);
        felter[4].setBeskrivelse("Over 900 forskellige måder at få diabetes");
        felter[4].setFarve(Color.CYAN);
        felter[4].setPrisUdskrift("M1");

        felter[5] = new Felt();
        felter[5].setFeltNavn("Iskiosken");
        felter[5].setPris(1);
        felter[5].setBeskrivelse("Stedet med Redbull sorbet");
        felter[5].setFarve(Color.CYAN);
        felter[5].setPrisUdskrift("M1");

        felter[6] = new Felt();
        felter[6].setFeltNavn("I fængsel");
        felter[6].setPris(-1);
        felter[6].setBeskrivelse("Besøg holdet bag tremmer");
        felter[6].setPrisUdskrift("");

        felter[7] = new Felt();
        felter[7].setFeltNavn("Museet");
        felter[7].setPris(2);
        felter[7].setBeskrivelse("Udstilling af en banan med værdi af millioner");
        felter[7].setFarve(Color.PINK);
        felter[7].setPrisUdskrift("M2");

        felter[8] = new Felt();
        felter[8].setFeltNavn("Biblioteket");
        felter[8].setPris(2);
        felter[8].setBeskrivelse("Shhhh...");
        felter[8].setFarve(Color.PINK);
        felter[8].setPrisUdskrift("M2");

        felter[9] = new Felt();
        felter[9].setFeltNavn("Chance");
        felter[9].setPris(-1);
        felter[9].setBeskrivelse("ludomani.dk, pls stop inden det er for sent");
        felter[9].setPrisUdskrift("");

        felter[10] = new Felt();
        felter[10].setFeltNavn("Skaterparken");
        felter[10].setPris(2);
        felter[10].setBeskrivelse("Tony Hawks Pro Skater");
        felter[10].setFarve(Color.ORANGE);
        felter[10].setPrisUdskrift("M2");

        felter[11] = new Felt();
        felter[11].setFeltNavn("Swimmingpoolen");
        felter[11].setPris(2);
        felter[11].setBeskrivelse("Frem med dine speedos");
        felter[11].setFarve(Color.ORANGE);
        felter[11].setPrisUdskrift("M2");

        felter[12] = new Felt();
        felter[12].setFeltNavn("Parkeringspladsen");
        felter[12].setBeskrivelse("1 runde gratis parkering");
        felter[12].setPris(-1);
        felter[12].setPrisUdskrift("");

        felter[13] = new Felt();
        felter[13].setFeltNavn("Spillehallen");
        felter[13].setPris(3);
        felter[13].setBeskrivelse("Join holdet i CSGO turneringen");
        felter[13].setFarve(Color.RED);
        felter[13].setPrisUdskrift("M3");

        felter[14] = new Felt();
        felter[14].setFeltNavn("Biografen");
        felter[14].setPris(3);
        felter[14].setBeskrivelse("Twilight maraton /:");
        felter[14].setFarve(Color.RED);
        felter[14].setPrisUdskrift("M3");

        felter[15] = new Felt();
        felter[15].setFeltNavn("Chance");
        felter[15].setPris(-1);
        felter[15].setBeskrivelse("En sidste sats /:");
        felter[15].setPrisUdskrift("");

        felter[16] = new Felt();
        felter[16].setFeltNavn("Legetøjsbutikken");
        felter[16].setPris(3);
        felter[16].setBeskrivelse("RIP Toys'R'Us ):" );
        felter[16].setFarve(Color.YELLOW);
        felter[16].setPrisUdskrift("M3");

        felter[17] = new Felt();
        felter[17].setFeltNavn("Dyrehandleren");
        felter[17].setPris(3);
        felter[17].setBeskrivelse("Så meget for den dværghamster ):");
        felter[17].setFarve(Color.YELLOW);
        felter[17].setPrisUdskrift("M3");

        felter[18] = new Felt();
        felter[18].setFeltNavn("Gå i fængsel");
        felter[18].setPris(-1);
        felter[18].setBeskrivelse("Join holdet bag tremmerne");
        felter[18].setPrisUdskrift("");

        felter[19] = new Felt();
        felter[19].setFeltNavn("Bowlinghallen");
        felter[19].setPris(4);
        felter[19].setBeskrivelse("Tekken superbowl time");
        felter[19].setFarve(Color.GREEN);
        felter[19].setPrisUdskrift("M4");

        felter[20] = new Felt();
        felter[20].setFeltNavn("Zoo");
        felter[20].setPris(4);
        felter[20].setBeskrivelse("Madagascar pingviner på mission");
        felter[20].setFarve(Color.GREEN);
        felter[20].setPrisUdskrift("M4");

        felter[21] = new Felt();
        felter[21].setFeltNavn("Chance");
        felter[21].setPris(-1);
        felter[21].setBeskrivelse("):");
        felter[21].setPrisUdskrift("");

        felter[22] = new Felt();
        felter[22].setFeltNavn("Vandlandet");
        felter[22].setPris(5);
        felter[22].setBeskrivelse("Lalandia lets go!!!!");
        felter[22].setFarve(Color.BLUE);
        felter[22].setPrisUdskrift("M5");

        felter[23] = new Felt();
        felter[23].setFeltNavn("Strandpromenaden");
        felter[23].setPris(5);
        felter[23].setBeskrivelse("< Brøndby Strand Strand):");
        felter[23].setFarve(Color.BLUE);
        felter[23].setPrisUdskrift("M5");

        return felter;
    }
}
