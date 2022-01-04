package matadorJuniorSpil.spil;

import matadorJuniorSpil.genstand.Bil;
import matadorJuniorSpil.genstand.Konto;
import matadorJuniorSpil.genstand.Spiller;
import matadorJuniorSpil.genstand.Spillerliste;
import matadorJuniorSpil.genstand.Terning;
import gui_fields.GUI_Player;

import java.awt.Color;

public class Kontrol {

    private Spillerliste listeAfSpillere;
    private Spilleplade plade;
    private Terning terning;
    private Spil spil;

    //Constructor for Kontrol der laver objekter af en spilleplade samt en terning
    public Kontrol() {
        plade = new Spilleplade();
        terning = new Terning();
        terning.kast();
    }

    public void spilKontrol() {
        plade.sendMeddelelse("Velkommen til IOOuterActive's Monopoly Junior");
        spil = new Spil(listeAfSpillere, plade);
        skabSpillere();

        int nuværendeSpillere = -1;
        do {
            nuværendeSpillere = (nuværendeSpillere + 1) % listeAfSpillere.antalAfSpiller();
            plade.setTerning(terning.kast());
            spil.spillerHandling(terning, listeAfSpillere.getSpiller(nuværendeSpillere));

        } while (!listeAfSpillere.getSpiller(nuværendeSpillere).getHarTabt());

    }

    public void skabSpillere() {
        int spillerAntal = plade.getAntalletAfSpillere();
        listeAfSpillere = new Spillerliste(spillerAntal);

        //Opretter spillerliste med input fra bruger gennem GUI.
        for (int i = 0; i < spillerAntal; i++) {
            int startPoint;
            if (spillerAntal == 2)
                startPoint = 20;
            else if (spillerAntal == 3)
                startPoint = 18;
            else
                startPoint = 16;

            String navn = plade.getNavn();
            int farve = plade.getBilFarve();

            //Spillere vælger hvilken bil(farve) de vil have gennem GUI.
            Color farve1;
            Color farve2;
            if (farve == 1) {
                farve1 = Color.BLACK;
                farve2 = Color.white;

            } else if (farve == 2) {
                farve1 = Color.RED;
                farve2 = Color.white;

            } else if (farve == 3) {
                farve1 = Color.YELLOW;
                farve2 = Color.white;

            } else {
                farve1 = Color.BLUE;
                farve2 = Color.white;
            }
                Spiller spiller = new Spiller(navn, new Bil(farve1, farve2), new Konto(startPoint));
                listeAfSpillere.addSpiller(spiller, i);
                GUI_Player player = plade.addSpillerTilPladen(spiller);
                spiller.setGUI_Player(player);
                spiller.opdaterKonto();
            }
        }
    }


