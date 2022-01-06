package spil;

import java.util.ArrayList;
import java.util.Collections;
import genstand.Felt;
import genstand.Felter;
import genstand.Spiller;
import genstand.Spillerliste;
import genstand.Terning;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Street;

public class Spil {
    private Spillerliste spillere;
    private Spilleplade plade;
    private Felt[] feltInfo;

    //Constructor for Spil med 2 variabler, spillere og plade
    public Spil(Spillerliste spillere, Spilleplade plade) {
        this.spillere = spillere;
        this.plade = plade;
        feltInfo = Felter.getFelter();
    }

    //Giver spiller en ny placering efter terningslag.
    public void spillerHandling(Terning terning, Terning terning2, Spiller spiller)
    {
        int kast = terning.getØjne();
        int kast2 = terning2.getØjne();
        String meddelelse = spiller.getNavn();

        int forrigePosition = spiller.getPosition();
        int nyePosition = (forrigePosition + kast + kast2);

        if (nyePosition >= plade.getFelter().length)
        {
            plade.sendMeddelelse(meddelelse + " Modtagere M2 for passering af start");
            spiller.getKonto().givPenge(2);
            nyePosition -= plade.getFelter().length;
        }

        //'Fjerner' spiller fra forrige position.
        plade.getFelt(forrigePosition).setCar(spiller.getGUI_PLayer(), false);
        //Opdater spiller nye position.
        spiller.opdaterPosition(nyePosition);
        GUI_Field landetFelt = plade.getFelt(nyePosition);
        String landetFeltNavn = landetFelt.getTitle();


        if (landetFeltNavn == "Start") {
        }
        else if (landetFelt instanceof GUI_Chance) {
            int tilfældig = (int) (Math.random()*2)+1;
            if(tilfældig == 1 ) {
                spiller.getKonto().givPenge(1);
                meddelelse += " landet på chancen, modtager derned M1";
            } else if(tilfældig == 2) {
                spiller.getKonto().tagPenge(1);
                meddelelse += " landet på chancen, mister dermed M1";
            }
        }
        else if (landetFelt instanceof GUI_Refuge)
            // Parkerings feltet er gratis
            meddelelse += " gratis parkering";
        else if (landetFelt == plade.getFelt(6))
            // På besøg i fængsel
            meddelelse += " fængsels besøg";
        else if (landetFelt == plade.getFelt(18))
        {
            nyePosition = 6;
            landetFelt = plade.getFelt(nyePosition);
            spiller.opdaterPosition(nyePosition);
            meddelelse += " skal i fængsel";
        }
        else if (landetFelt instanceof GUI_Street){
            String ejerNavn = ((GUI_Street) landetFelt).getOwnerName();
            int pris = feltInfo[nyePosition].getPris();
            Spiller ejer = feltInfo[nyePosition].getEjer();

            if (ejerNavn != null)
            {
                if (spiller.getNavn() != ejerNavn)
                {
                    spiller.getKonto().tagPenge(pris);
                    ejer.getKonto().givPenge(pris);
                    ejer.opdaterKonto();
                    meddelelse += " landet på " + landetFelt.getTitle() +
                            " tilhøreres af " + ejerNavn +
                            ", der betales dem M" + pris;
                }
                else
                {
                    meddelelse += " landet på " + landetFelt.getTitle() +
                            " Som er egen ejendom";
                }

            }
            else
            {
                spiller.getKonto().tagPenge(feltInfo[nyePosition].getPris());
                plade.købFelt(nyePosition, spiller.getNavn());
                feltInfo[nyePosition].setEjer(spiller);
                meddelelse += " har købt " + feltInfo[nyePosition].getFeltNavn() +
                        " for M" + feltInfo[nyePosition].getPris();
            }
        }
        landetFelt.setCar(spiller.getGUI_PLayer(), true);
        spiller.opdaterKonto();
        if (!meddelelse.equals(spiller.getNavn()))
            plade.sendMeddelelse(meddelelse);

        if(spiller.getKonto().getSaldo() == 0) {
            plade.sendMeddelelse(spiller.getNavn() + " har tabt spillet ):");
            spiller.setHarTabt(true);

            ArrayList<Integer> placeringer = new ArrayList<Integer>();
            for(int i = 0; i < spillere.antalAfSpiller(); i++) {
                placeringer.add(spillere.getSpiller(i).getKonto().getSaldo());
            }

            Collections.sort(placeringer);
            plade.sendMeddelelse("Vinderen af spillet er spilleren med beløbet på " + placeringer.get(placeringer.size()-1));
        }

    }
}

