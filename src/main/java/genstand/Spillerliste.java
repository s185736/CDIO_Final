package genstand;
import java.util.Arrays;

public class Spillerliste {
    private Spiller[] sListe;

    public Spillerliste(int antalAfSpiller) {
        this.sListe = new Spiller[antalAfSpiller];
    }

    //Metode til tilføjelse af spiller til liste
    public void addSpiller(Spiller spiller, int i){
        this.sListe[i] = spiller;
    }

    public Spiller getSpiller(int i) {
        return sListe[i];
    }

    public int antalAfSpiller() {
        return sListe.length;
    }

    @Override
    public String toString() {
        return "Spillerliste: " + Arrays.toString(sListe);
    }

}
