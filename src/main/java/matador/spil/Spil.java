package matador.spil;

import gui_main.GUI;
import matador.*;
import matador.spiller.Spiller;
import matador.spiller.Spillerliste;
import matador.spilleplade.chancekort.ChanceBunke;
import matador.spilleplade.felter.Felt;
import matador.spilleplade.Spilleplade;
import matador.spilleplade.felter.Ejendom;
import matador.spilleplade.felter.Faengsel;
import matador.spilleplade.genstand.Terning;
import java.util.Arrays;
import java.util.LinkedList;

public class Spil {

    private final GUI gui;
    private final Spilleplade spilleplade;
    private final Terning terning;
    private final Terning terning1;
    private final ChanceBunke chanceBunke;
    private final Spillerliste spillere;

    /*Konstruktør*/
    public Spil() {
        this.spilleplade = new Spilleplade();
        this.gui = new GUI(this.spilleplade.getGUIFelt());
        this.terning = new Terning();
        this.chanceBunke = new ChanceBunke();
        this.spillere = new Spillerliste();
        this.terning1 = new Terning();
    }

    public GUI getGui()
    {
        return this.gui;
    }

    public Spilleplade getSpillerplade()
    {
        return this.spilleplade;
    }

    public Spillerliste getSpiller()
    {
        return this.spillere;
    }

    public ChanceBunke getChanceBunke()
    {
        return this.chanceBunke;
    }

    public Felt[] getFelter()
    {
        return this.spilleplade.getFelter();
    }

    /*Rykker spiller med antal felter at rykke.*/
    public void rykSpiller(Spiller spiller, int rykAntalFelter) {
        Felt nuvaerendeFelt = this.spilleplade.getSpillerFelt(spiller);
        this.spilleplade.rykSpiller(spiller, rykAntalFelter);
        if (this.spilleretPasseretStartFelt(spiller, nuvaerendeFelt)) {
            spiller.tilfoejBalance(4000);
            this.gui.showMessage(spiller.getNavn() + Oversaetter.t("kast.terning3"));
        } else {
            return;
        }
    }

    /*Rykker spiller til et bestemt felt.*/
    public void rykSpiller(Spiller spiller, String feltNavn) {
        Felt nuvaerendeFelt = this.spilleplade.getSpillerFelt(spiller);
        Felt rykTilFelt = this.spilleplade.getFeltVedNavn(feltNavn);
        this.spilleplade.rykSpiller(spiller, rykTilFelt);

        if (nuvaerendeFelt instanceof Faengsel || rykTilFelt instanceof Faengsel) {
            return;
        }
        if (!this.spilleretPasseretStartFelt(spiller, nuvaerendeFelt)) {
            return;
        }
        spiller.tilfoejBalance(4000);
        this.gui.showMessage(spiller.getNavn() + Oversaetter.t("kast.terning3"));
    }

    /*Valg af antal spillere.
    * Dette vil vise et gui-dropdown menu som giver muligheden til spillerene at vælge hvor mange de vil spille spillet.*/
    private int valgAfAntalSpillere(){
        String valgAfAntalSpillere;
        valgAfAntalSpillere = this.gui.getUserSelection(Oversaetter.t("velkommen1.getAntalSpillere"),"3", "4", "5", "6");
        return Integer.valueOf(valgAfAntalSpillere);
    }

    private void opretSpillere(int antalSpillere) {
        LinkedList<String> types;
        types = new LinkedList<>(Arrays.asList(Spiller.SpillerBrik.valuesToString()));

        int i = 0;
        while (i < antalSpillere) {
            String navn = this.gui.getUserString(Oversaetter.t("velkommen2.getSpillerNavn"));
            String type = this.gui.getUserSelection(Oversaetter.t("type.hvemerhvem"), types.toArray(new String[] {}));
            types.remove(type);
            Spiller spiller = new Spiller(navn, this.getStarterPakke(antalSpillere));
            spiller.setType(Spiller.SpillerBrik.konvEnum(type));
            this.gui.addPlayer(spiller.getSpiller());
            this.spilleplade.addPlayer(spiller);
            this.spillere.tilfoejSpiller(spiller);
            i++;
        }
    }

    /*Starter pakke til spillerne.*/
    private int getStarterPakke(int antalSpillere) {
        return 30000;
    }

    /*Her bestemmer man hvem der skal starte.*/
    private void hvemSkalStarte(int spillereDeltaget){
        String spillerNummerString = "";
        switch (spillereDeltaget) {
            case 2:  spillerNummerString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.spillere.get(0).getNavn(), "2. " + this.spillere.get(1).getNavn());
                break;
            case 3:  spillerNummerString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.spillere.get(0).getNavn(), "2. " + this.spillere.get(1).getNavn(), "3. " + this.spillere.get(2).getNavn());
                break;
            case 4:  spillerNummerString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.spillere.get(0).getNavn(), "2. " + this.spillere.get(1).getNavn(), "3. " + this.spillere.get(2).getNavn(), "4. " + this.spillere.get(3).getNavn());
                break;
            case 5:  spillerNummerString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.spillere.get(0).getNavn(), "2. " + this.spillere.get(1).getNavn(), "3. " + this.spillere.get(2).getNavn(), "4. " + this.spillere.get(3).getNavn(), "5. " + this.spillere.get(4).getNavn());
                break;
            case 6:  spillerNummerString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.spillere.get(0).getNavn(), "2. " + this.spillere.get(1).getNavn(), "3. " + this.spillere.get(2).getNavn(), "4. " + this.spillere.get(3).getNavn(), "5. " + this.spillere.get(4).getNavn(), "4. " + this.spillere.get(5).getNavn());
                break;
        }
        spillerNummerString = spillerNummerString.split("\\.", 2)[0];
        int i = Integer.parseInt(spillerNummerString);
        this.spillere.setSpillerIndex(i - 1);
    }


    private boolean spilleretPasseretStartFelt(Spiller spiller, Felt nuvaerendeFelt) {
        Felt nyFelt;
        nyFelt = this.spilleplade.getSpillerFelt(spiller);
        return this.spilleplade.erFeltFoer(nyFelt, nuvaerendeFelt);
    }


    public void visVinderMatador() {
        Spiller[] vinder = this.spillere.getVinderMatador();
        if (vinder.length <= 1) {
        } else {
            for (int i = 0, winnersLength = vinder.length; i < winnersLength; i++) {
                Spiller spiller = vinder[i];
                int feltVaerdi = 0;
                Ejendom[] feltEjetAfSpiller = this.spilleplade.getFelterEjetAfSpiller(spiller);
                for (int j = 0, fieldsOwnedByPlayerLength = feltEjetAfSpiller.length; j < fieldsOwnedByPlayerLength; j++) {
                    Ejendom felt = feltEjetAfSpiller[j];
                    feltVaerdi += felt.getLeje();
                }
                spiller.tilfoejBalance(feltVaerdi);
            }
            vinder = this.spillere.getVinderMatador();

            /*Navnet af vinderen..*/
            if (vinder.length > 1) {
                String[] vinderSpiller = new String[vinder.length];
                for (int i = 0; i < vinder.length; i++) {
                    vinderSpiller[i] = vinder[i].getNavn();
                }
                this.gui.showMessage("Oops, det står vist lige imellen " + String.join(", ", vinderSpiller)+".");
            }
        }
        this.gui.showMessage("Woow, vi har en Matador Vinder, som er: " + vinder[0].getNavn()+".");
    }

    /*Herunder starter spillets og spillerne oprettes og der skal bestemmes
    * hvem skal der skal starte spillet.*/
    public void spilMatador() {
        int antalSpillere = valgAfAntalSpillere();
        this.opretSpillere(antalSpillere);
        hvemSkalStarte(antalSpillere);
        Spiller nuvaerendeSpiller = this.spillere.getNuvarendeSpiller();

        if (nuvaerendeSpiller.harTurHandlinger()) nuvaerendeSpiller.koerTurHandlinger();
        else {
            spilMatadorRunde(nuvaerendeSpiller);
        }

        this.spillere.forstoerrePlayerIndex();
        while (!this.spillere.erNogenFallit()) {
            nuvaerendeSpiller = this.spillere.getNuvarendeSpiller();
            if (nuvaerendeSpiller.harTurHandlinger()) nuvaerendeSpiller.koerTurHandlinger();
            else {
                spilMatadorRunde(nuvaerendeSpiller);
            }
            this.spillere.forstoerrePlayerIndex();
        }

        Spiller falitSpiller = this.spillere.getSpillerFallit();
        this.gui.showMessage(falitSpiller.getNavn() + Oversaetter.t("slut.matador.fallit"));
        this.visVinderMatador();
        this.gui.showMessage("Venligst klik på 'OK' for at afslutte spillet.");
        System.exit(0);
    }

    public void spilMatadorRunde(Spiller nuvaerendeSpiller) {
        if (nuvaerendeSpiller.getIFaengsel() == true){
            Faengsel faengsel = new Faengsel(false);
            faengsel.koerHandling(this);
        }
        for (int i = 0; i < 3;){
            switch (this.gui.getUserButtonPressed(Oversaetter.t("kast.terning") + " " + nuvaerendeSpiller.getNavn() + Oversaetter.t("kast.terning1"), "Kast")) {
            }
            this.terning.kast();
            this.terning1.kast();
            int faceValue1 = this.terning.getFaceValue();
            int faceValue2 = this.terning1.getFaceValue();
            int rykDistance = faceValue1 + faceValue2;
            this.gui.setDice(faceValue1, faceValue2);
            this.gui.showMessage(new StringBuilder().append(nuvaerendeSpiller.getNavn()).append(Oversaetter.t("kast.terning2")).append(" ").append(rykDistance).toString());
            this.rykSpiller(nuvaerendeSpiller, rykDistance);
            Felt felt = this.spilleplade.getSpillerFelt(nuvaerendeSpiller);
            felt.koerHandling(this);
            if (faceValue1 == faceValue2){
                i++;
                gui.showMessage("Du har slået 2 ens, dermed får du en ekstra tur!");
                if (i == 3){
                    //Funktioner der skal laves hvor spilleren bliver sat i fængsel
                    this.spilleplade.rykSpiller(nuvaerendeSpiller, this.getFelter()[30]);
                    gui.showMessage("Du har slået 2 ens 3 gange i streg, du ryger direkte i fængsel");
                    nuvaerendeSpiller.setIFaengsel(true);
                }
            }
            else{
                i = 3;
            }
        }
    }
}