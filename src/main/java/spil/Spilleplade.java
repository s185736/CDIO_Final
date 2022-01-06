package spil;

import genstand.Felt;
import genstand.Felter;
import genstand.Spiller;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.Arrays;

public class Spilleplade {

    private GUI_Field[] felter;
    private int[] listeAfLeje;
    GUI gui;

    public Spilleplade(){
        Felt[] feltInfo = Felter.getFelter();
        felter = new GUI_Field[feltInfo.length];
        listeAfLeje = new int[feltInfo.length];

        for (int i = 0; i < feltInfo.length; i++) {
            listeAfLeje[i] = feltInfo[i].getPris();

            if (feltInfo[i].getFeltNavn() == "Start")
                felter[i] = new GUI_Start();
            else if (feltInfo[i].getFeltNavn() == "Chance")
                felter[i] = new GUI_Chance();
            else if (feltInfo[i].getFeltNavn() == "Parkering")
                felter[i] = new GUI_Refuge();
            else if (feltInfo[i].getFeltNavn() == "Besøg i fængsel")
                felter[i] = new GUI_Jail();
            else if (feltInfo[i].getFeltNavn() == "Gå i fængsel")
                felter[i] = new GUI_Jail();
            else {
                // Kun ejendomme har priser og farver.
                felter[i] = new GUI_Street();
                ((GUI_Street) felter[i]).setRent("M" + feltInfo[i].getPris());
                felter[i].setBackGroundColor(feltInfo[i].getFarve());
            }
            //Tilføjer feltnavn, beskrivelse og pris på spillerplade GUI
            felter[i].setTitle(feltInfo[i].getFeltNavn());
            felter[i].setDescription(feltInfo[i].getBeskrivelse());
            felter[i].setSubText(feltInfo[i].getPrisUdskrift());
        }

        gui = new GUI(felter);
    }

    public GUI_Field[] getFelter()
    {
        return felter;
    }
    public GUI_Field getFelt(int index)
    {
        return felter[index];
    }

    public void købFelt(int index, String køberNavn)
    {
        ((GUI_Street) felter[index]).setOwnerName(køberNavn);
        ((GUI_Street) felter[index]).setBorder(Color.RED);
    }

    public int getBilFarve() {
        return gui.getUserInteger("Vælg bilfarve 1=sort, 2=rød, 3=gul, 4=blå", 1, 4);
    }

    public String getNavn() {
        return gui.getUserString("Skriv dit navn: ");
    }

    public int getAntalletAfSpillere() {
        return gui.getUserInteger("Skriv antallet af spillere", 2, 4);
    }

    public GUI_Player addSpillerTilPladen(Spiller spiller) {
        GUI_Player player = new GUI_Player(spiller.getNavn(), spiller.getKonto().getSaldo(), spiller.getBil().getBil());
        gui.addPlayer(player);
        return player;
    }

    public void sendMeddelelse(String meddelelse)
    {
        gui.showMessage(meddelelse);
    }

    public void setTerning(int øjne)
    {
        gui.setDie(øjne);
    }

    public String toString() {
        return "Spilleplade [felter=" + Arrays.toString(felter) + "]";
    }


}
