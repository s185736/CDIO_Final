package matador.spiller;

import java.util.ArrayList;

/*Spillerliste*/
public class Spillerliste extends ArrayList<Spiller> {

    private int spillerIndex; //Indekset, der bruges til at bestemme, hvilken spiller der er den aktuelle.

    /*Konstruktør*/
    public Spillerliste() {
        this.spillerIndex = 0;
    }

    /*Tilføjer en spiller til spillerlisten.*/
    public void tilfoejSpiller(Spiller spiller) {
        this.add(spiller);
    }

    /*Henter den nuværende spiller for spillerlisten.*/
    public Spiller getNuvarendeSpiller() {
        return this.get(this.spillerIndex);
    }

    /*Indstil spillerindekset for at skifte spiller.*/
    public void setSpillerIndex(int spillerIndex) {
        this.spillerIndex = spillerIndex;
    }

    /*Øg spillerindekset for at gå til næste spiller..*/
    public void forstoerrePlayerIndex() {
        this.spillerIndex = (this.spillerIndex < (this.size() - 1)) ? this.spillerIndex + 1 : 0;
    }

    /*Tjek hvis nogen spiller er fallit i spillerlisten.*/
    public boolean erNogenFallit() {
        return this.stream().anyMatch(Spiller::erGaaetFallit);
    }

    /*Få en spiller i spillelisten ved dens spiller type.*/
    public Spiller getSpillerVedType(Spiller.Type type) {
        return this.stream()
                .filter(player -> player.getType() == type)
                .findFirst()
                .orElse(null);
    }

    /*Få fat i en spiller der er gået fallit.*/
    public Spiller getSpillerFallit() {
        return this.stream()
                .filter(Spiller::erGaaetFallit)
                .findFirst()
                .orElse(null);
    }

    /*Få spiller med mest penge. Og returnere vinderen af spillet.*/
    public Spiller[] getVinderMatador() {
        Spiller vinder = null;
        ArrayList<Spiller> vindere = new ArrayList<>();
        int i = 0, thisSize = this.size();
        while (i < thisSize) {
            Spiller spiller = this.get(i);
            if (vinder != null) {
                if (spiller.getBalance() <= vinder.getBalance()) {
                    if (spiller.getBalance() >= vinder.getBalance()) {
                        vinder = spiller;
                        vindere.add(spiller);
                    }
                } else {
                    vinder = spiller;
                    vindere.clear();
                    vindere.add(spiller);
                }
            } else {
                vinder = spiller;
                vindere.add(spiller);
            }
            i++;
        }
        return vindere.toArray(new Spiller[] {});
    }
}