package matadorJuniorSpil.genstand;

import gui_fields.GUI_Player;

public class Spiller {

    private Konto konto;
    private String navn;
    private Bil bil;
    private boolean harTabt = false;
    private int position;
    private GUI_Player spiller;

    //Constructor for Spiller med 3 variabler, navn, bil og konto
    public Spiller(String navn, Bil bil, Konto konto) {
        this.navn = navn;
        this.bil = bil;
        this.konto = konto;
    }

    public String getNavn() {
        return navn;
    }
    public Bil getBil() {
        return bil;
    }
    public Konto getKonto() {
        return konto;
    }

    //Metoder bruges til at tjekke om spiller har tabt og sætte at spiller har tabt
    public boolean getHarTabt() {
        return harTabt;}
    public void setHarTabt(boolean harTabt){
        this.harTabt = harTabt;
    }

    //Metoder bruges til at hente og opdatere spillers position
    public int getPosition() {
        return position;}
    public void opdaterPosition(int position){
        this.position = position;
    }

    //Metoder bruges til rykke spiller frem på spillepladen
    public GUI_Player getGUI_PLayer() {
        return spiller;
    }
    public void setGUI_Player(GUI_Player spiller) {
        this.spiller = spiller;
    }

    public void opdaterKonto() {
        spiller.setBalance(konto.getSaldo());
    }

    @Override
    public String toString() {
        return "\t\nSpiller: navn=" + navn + ", konto=" + konto + ", bil=" + bil;
    }

}