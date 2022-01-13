package matador.spiller;

import gui_fields.GUI_Player;
import matador.LagerStash;

public class Spiller {

    private GUI_Player spiller;
    private String navn;
    private LagerStash lagerStash;
    private SpillerBrik spillerBrik = SpillerBrik.BIL1;
    private int faengselsKort;
    private boolean iFaengsel;

    /*Dette er handlingen til at køre for spillerens næste tur.*/
    private TurHandlinger turHandling;

    /*Enum til at bestemme typen af spiller.*/
    public enum SpillerBrik {
        BIL1,
        BIL2,
        BIL3,
        BIL4,
        BIL5,
        BIL6;

    /*Konverterer en string til den korresponderet type.*/
    public static SpillerBrik konvEnum(String navn) throws IllegalArgumentException {
        return SpillerBrik.valueOf(navn.toUpperCase());
    }

    /*Henter alle typer som strings.*/
    public static String[] valuesToString() {
        SpillerBrik[] spillerBriks = SpillerBrik.values();
        String[] typesString = new String[spillerBriks.length];
        for (int i = 0; i < spillerBriks.length; i++) {
            typesString[i] = spillerBriks[i].toString();
        }
        return typesString;
      }
    }

    /*Konstruktør af en spiller med et navn og start balance..*/
    public Spiller(String navn)
    {
        this(navn, 0);
    }

    /*Konstruktør af en spiller med et navn og start balance.*/
    public Spiller(String navn, int startBalance) {
        this.navn = navn;
        this.lagerStash = new LagerStash(startBalance);
        this.spiller = new GUI_Player(this.navn, this.lagerStash.getMaengdeBeloeb());
        this.faengselsKort = 0;
        this.iFaengsel = false;
    }

    /*Henter navn af dette spiller.*/
    public String getNavn()
    {
        return this.navn;
    }

    /*Henter balance af spilleren.*/
    public int getBalance()
    {
        return this.lagerStash.getMaengdeBeloeb();
    }

    //Sætter True/False for om spilleren er i fængsel
    public void setIFaengsel(boolean iFaengsel){
        this.iFaengsel = iFaengsel;
    }
    public boolean getIFaengsel(){
        return this.iFaengsel;
    }

    /*Tilføjer balance spiller.*/
    public void tilfoejBalance(int balance) {
        this.lagerStash.tilfoejBeloeb(balance);
        this.spiller.setBalance(this.lagerStash.getMaengdeBeloeb());
    }

    /*Henter mængde af fængselskorte.*/
    public int getFængselsKort() {return this.faengselsKort; }

    /*Tilføj mængde af fængselskorte.*/
    public int tilfoejFængselsKort(int antalFaengselsKorte) {
        this.faengselsKort += antalFaengselsKorte;
        return this.faengselsKort;
    }

    /*Fjerne antallet fængselskorte.*/
    public int fjernFaengselsKort(int antalFaengselsKorte) {
        this.faengselsKort -= antalFaengselsKorte;
        return this.faengselsKort;
    }

    /*Få dette spiller reference til GUI'en.*/
    public GUI_Player getSpiller()
    {
        return this.spiller;
    }

    /*Ændring af type af spiller der skal være.*/
    public void setType(SpillerBrik spillerBrik)
    {
        this.spillerBrik = spillerBrik;
    }

    /*Henter dette spiller type.*/
    public SpillerBrik getType()
    {
        return this.spillerBrik;
    }

    /*Grænseflade for spiller runnable handlinger istedet for normal tur.*/
    public interface TurHandlinger {
        /*Dette er handlingen for at køre dette for spillerens næste tur.*/
        void run();
    }

    /*Opretter en handling for den næste tur.*/
    public void setTurHandling(TurHandlinger turHandling)
    {
        this.turHandling = turHandling;
    }

    /*Kør handlingen, handlingen til blive fjerned efter brug.*/
    public void koerTurHandlinger() {
        if (this.harTurHandlinger()) {
            this.turHandling.run();
            this.turHandling = null;
        }
    }

    /*Bestemmer hvis spiller har en TurHandling.*/
    public boolean harTurHandlinger()
    {
        return this.turHandling != null;
    }

    /*Tjekker hvis en spiller er gået fallit.*/
    public static boolean erGaaetFallit(Spiller spiller)
    {
        return spiller.getBalance() < 0;
    }

}