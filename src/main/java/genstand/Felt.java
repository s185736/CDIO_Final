package genstand;

import java.awt.*;

public class Felt {

    private int pris;
    private String feltNavn, prisUdskrift, beskrivelse;
    private Color farve;
    private Spiller ejer;

    //Metoder for at hente ejer og sætte ejer
    public Spiller getEjer() {
        return ejer;
    }
    public void setEjer(Spiller ejer) {
        this.ejer = ejer;
    }

    //Metoder for at hente navn på et felt og give navn til et felt
    public String getFeltNavn() {
        return feltNavn; }
    public void setFeltNavn(String feltNavn){
        this.feltNavn = feltNavn;
    }

    //Metoder for at hente beskrivelse fra et felt og give beskrivelse til et felt
    public String getBeskrivelse() {
        return beskrivelse; }
    //Metode der giver felt en beskrivelse.
    public void setBeskrivelse(String beskrivelse){
        this.beskrivelse = beskrivelse;
    }

    //Metoder der henter pris på et felt og give pris til et felt
    public int getPris() {
        return pris; }
    public void setPris(int pris){
        this.pris = pris;
    }

    //Metoder bruges til at vise og give tekst af prisen til spillepladen
    public String getPrisUdskrift(){
        return prisUdskrift;}
    public void setPrisUdskrift(String prisUdskrift){
        this.prisUdskrift = prisUdskrift;
    }

    //Metoder bruges til at vise og give farve til felt på spilleplade
    public Color getFarve() {
        return farve;
    }
    public void setFarve(Color farve) {
        this.farve = farve;
    }

    public String toString() {
        return "";
    }

}