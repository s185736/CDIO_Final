package matador.spil;

import gui_main.GUI;
import matador.*;
import matador.spiller.Spiller;
import matador.spiller.Spillerliste;
import matador.spilleplade.chancekort.ChanceBunke;
import matador.spilleplade.felter.Felt;
import matador.spilleplade.Spilleplade;
import matador.spilleplade.genstand.Hus;
import matador.spilleplade.felter.Faengsel;
import matador.spilleplade.genstand.Terning;

import java.util.Arrays;
import java.util.LinkedList;

public class Spil {

    private GUI gui;
    private Spilleplade spilleplade;
    private Terning terning;
    private Terning terning1;
    private ChanceBunke chanceBunke;
    private Spillerliste players;

    /*Konstruktør*/
    public Spil() {
        this.spilleplade = new Spilleplade();
        this.gui = new GUI(this.spilleplade.getGUIFelt());
        this.terning = new Terning();
        this.terning1 = new Terning();
        this.chanceBunke = new ChanceBunke();
        this.players = new Spillerliste();
    }

    public GUI getGui()
    {
        return this.gui;
    }

    public Spilleplade getBoard()
    {
        return this.spilleplade;
    }

    public Spillerliste getPlayers()
    {
        return this.players;
    }

    public ChanceBunke getChanceDeck()
    {
        return this.chanceBunke;
    }

    public Felt[] getFelter()
    {
        return this.spilleplade.getFelter();
    }

    /*Rykker spiller med antal felter at rykke.*/
    public void rykSpiller(Spiller spiller, int rykAntalFelter) {
        Felt nuvaerendeFelt = this.spilleplade.getPlayerField(spiller);
        this.spilleplade.movePlayer(spiller, rykAntalFelter);
        if (this.spilleretPasseretStartFelt(spiller, nuvaerendeFelt)) {
            spiller.tilfoejBalance(2);
            this.gui.showMessage(spiller.getNavn() + Oversaetter.t("kast.terning3"));
        } else {
            return;
        }
    }

    /*Rykker spiller til et bestemt felt.*/
    public void rykSpiller(Spiller spiller, String feltNavn) {
        Felt nuvaerendeFelt = this.spilleplade.getPlayerField(spiller);
        Felt rykTilFelt = this.spilleplade.getFeltVedNavn(feltNavn);
        this.spilleplade.movePlayer(spiller, rykTilFelt);

        if (nuvaerendeFelt instanceof Faengsel || rykTilFelt instanceof Faengsel) {
            return;
        }
        if (!this.spilleretPasseretStartFelt(spiller, nuvaerendeFelt)) {
            return;
        }
        spiller.tilfoejBalance(2);
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
        types = new LinkedList<>(Arrays.asList(Spiller.Type.valuesToString()));
        //ArrayList<String> types;
        //types = new ArrayList<>(Arrays.asList(Spiller.Type.valuesToString()));

        int i = 0;
        while (i < antalSpillere) {
            String navn = this.gui.getUserString(Oversaetter.t("velkommen2.getSpillerNavn"));
            String type = this.gui.getUserSelection(Oversaetter.t("type.hvemerhvem"), types.toArray(new String[] {}));

            types.remove(type);
            Spiller spiller = new Spiller(navn, this.getStarterPakke(antalSpillere));
            spiller.setType(Spiller.Type.konvEnum(type));

            this.gui.addPlayer(spiller.getSpiller());
            this.spilleplade.addPlayer(spiller);
            this.players.tilfoejSpiller(spiller);
            i++;
        }
    }

    /*Starter pakke til spillerne.*/
    private int getStarterPakke(int antalSpillere) {
        if (antalSpillere == 2) {
            return 20;
        } else if (antalSpillere == 3) {
            return 1;
        } else if (antalSpillere == 4) {
            return 16;
        }
        return 20;
    }

    /*Her bestemmer man hvem der skal starte.*/
    private void hvemSkalStarte(int spillereDeltaget){
        String playerNumberString = "";
        switch (spillereDeltaget) {
            case 2:  playerNumberString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.players.get(0).getNavn(), "2. " + this.players.get(1).getNavn());
                break;
            case 3:  playerNumberString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.players.get(0).getNavn(), "2. " + this.players.get(1).getNavn(), "3. " + this.players.get(2).getNavn());
                break;
            case 4:  playerNumberString = this.gui.getUserSelection(Oversaetter.t("velkommen3.getHvemSkalStarte"), "1. " + this.players.get(0).getNavn(), "2. " + this.players.get(1).getNavn(), "3. " + this.players.get(2).getNavn(), "4. " + this.players.get(3).getNavn());
                break;
        }
        playerNumberString = playerNumberString.split("\\.", 2)[0];
        int i = Integer.parseInt(playerNumberString);
        this.players.setSpillerIndex(i - 1);
    }


    private boolean spilleretPasseretStartFelt(Spiller spiller, Felt nuvaerendeFelt) {
        Felt nyFelt;
        nyFelt = this.spilleplade.getPlayerField(spiller);
        return this.spilleplade.isFieldBefore(nyFelt, nuvaerendeFelt);
    }


    public void visVinderMatador() {
        Spiller[] vinder = this.players.getVinderMatador();
        if (vinder.length <= 1) {
        } else {
            for (int i = 0, winnersLength = vinder.length; i < winnersLength; i++) {
                Spiller spiller = vinder[i];
                int feltVaerdi = 0;
                Hus[] feltEjetAfSpiller = this.spilleplade.getFieldsOwnedByPlayer(spiller);
                for (int j = 0, fieldsOwnedByPlayerLength = feltEjetAfSpiller.length; j < fieldsOwnedByPlayerLength; j++) {
                    Hus felt = feltEjetAfSpiller[j];
                    feltVaerdi += felt.getLeje();
                }
                spiller.tilfoejBalance(feltVaerdi);
            }
            vinder = this.players.getVinderMatador();

            /*Navnet af vinderen..*/
            if (vinder.length > 1) {
                String[] vinderSpiller = new String[vinder.length];
                for (int i = 0; i < vinder.length; i++) {
                    vinderSpiller[i] = vinder[i].getNavn();
                }
                this.gui.showMessage("Oops, der står vist lige imellen " + String.join(", ", vinderSpiller)+".");
            }
        }

        this.gui.showMessage("Woow, vi har en Matador Vinder: " + vinder[0].getNavn()+".");
    }


    /*Herunder starter spillets og spillerne oprettes og der skal bestemmes
    * hvem skal der skal starte spillet.*/
    public void spilMatador() {
        int antalSpillere = valgAfAntalSpillere();
        this.opretSpillere(antalSpillere);
        hvemSkalStarte(antalSpillere);
        Spiller nuvaerendeSpiller = this.players.getNuvarendeSpiller();

        if (nuvaerendeSpiller.harTurHandlinger()) nuvaerendeSpiller.koerTurHandlinger();
        else {
            spilMatadorRunde(nuvaerendeSpiller);
        }

        this.players.forstoerrePlayerIndex();
        while (!this.players.erNogenFallit()) {
            nuvaerendeSpiller = this.players.getNuvarendeSpiller();
            if (nuvaerendeSpiller.harTurHandlinger()) nuvaerendeSpiller.koerTurHandlinger();
            else {
                spilMatadorRunde(nuvaerendeSpiller);
            }
            this.players.forstoerrePlayerIndex();
        }

        Spiller falitSpiller = this.players.getSpillerFallit();
        this.gui.showMessage(falitSpiller.getNavn() + Oversaetter.t("slut.matador.fallit"));
        this.visVinderMatador();
        this.gui.showMessage("Venligst klik på 'OK' for at afslutte spillet.");
        System.exit(0);
    }


    public void spilMatadorRunde(Spiller nuvaerendeSpiller) {
        switch (this.gui.getUserButtonPressed(Oversaetter.t("kast.terning") + " " + nuvaerendeSpiller.getNavn() + Oversaetter.t("kast.terning1"), "Kast")) {
        }
        this.terning.kast();
        int faceValue = this.terning.getFaceValue();
        this.gui.setDie(faceValue);
        this.gui.showMessage(new StringBuilder().append(nuvaerendeSpiller.getNavn()).append(Oversaetter.t("kast.terning2")).append(" ").append(faceValue).toString());
        this.rykSpiller(nuvaerendeSpiller, faceValue);
        Felt felt = this.spilleplade.getPlayerField(nuvaerendeSpiller);
        felt.koerHandling(this);
    }
}