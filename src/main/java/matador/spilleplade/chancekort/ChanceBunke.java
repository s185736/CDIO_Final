package matador.spilleplade.chancekort;

import matador.spiller.Spiller;
import matador.spiller.Spillerliste;
import matador.Oversaetter;

import java.awt.Color;
import java.util.Random;


public class ChanceBunke {

    private final Chancekort[] korte;
    private int antalKortTrukket;

    /*Konstruktør*/
    public ChanceBunke() {
        this.korte = ChanceBunke.standardeChancekorte.clone();
        this.blandKort();
    }

    /*Chancekort, dette vil få vist kortet øverst på bunken.*/
    public Chancekort draw() {
        if (!this.erBunkenTom()) {
            return this.korte[this.antalKortTrukket++];
        }
        this.blandKort();
        final Chancekort chancekort = this.korte[this.antalKortTrukket++];
        return chancekort;
    }

    /*Blander kortene i bunken.*/
    public void blandKort() {
        Random random = new Random();
        int kortLaengde = this.korte.length;
        int i = 0;
        while (i < kortLaengde) {
            int slot = random.nextInt(kortLaengde);     //Vælg et tilfældigt index mellem 0 til kortets længde.
            Chancekort cKort = this.korte[slot]; //Skift kortene i bunken.
            this.korte[slot] = this.korte[i];
            this.korte[i] = cKort;
            i++;
        }
        this.antalKortTrukket = 0;
    }

    /*Denne metode tjekker hvis bunken er tom.
    * Returnerer hvis bunken er tom.*/
    public boolean erBunkenTom() {
        return this.antalKortTrukket >= this.korte.length;
    }

    /*Typer af chancekorte.
    * Disse er de standarde korte i bunken.*/
    static private final Chancekort[] standardeChancekorte= new Chancekort[] {
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.to_start"),
                        (spil) -> spil.rykSpiller(spil.getSpiller().getNuvarendeSpiller(), Oversaetter.t("spilleplade.felt.start.feltnavn"))
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.move_5_fields"),
                        (spil) -> {
                            String feltSvar = spil.getGui().getUserSelection(Oversaetter.t("chance.beskrivelse.move_5_fields.question"), "1", "2", "3", "4", "5");
                            int antalFelter = Integer.parseInt(feltSvar);
                            Spiller spiller = spil.getSpiller().getNuvarendeSpiller();
                            spil.rykSpiller(spiller, antalFelter);
                            spil.getSpillerplade().getSpillerFelt(spiller).koerHandling(spil);
                        }
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_orange"),
                        Chancekort.handlingTypeFelt(Color.ORANGE)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_orange_green"),
                        Chancekort.handlingTypeFelt(Color.ORANGE, Color.GREEN)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_pink_darkblue"),
                        Chancekort.handlingTypeFelt(Color.PINK, Color.BLUE)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_red"),
                        Chancekort.handlingTypeFelt(Color.RED)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_lightblue_red"),
                        Chancekort.handlingTypeFelt(Color.CYAN, Color.RED)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.ryk_til_brown_yellow"),
                        Chancekort.handlingTypeFelt(Color.GRAY, Color.YELLOW)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.felt_eller_kort"),
                        (spil) -> {
                            String answer = spil.getGui().getUserButtonPressed(
                                    Oversaetter.t("chance.beskrivelse.felt_eller_kort.question"),
                                    Oversaetter.t("chance.beskrivelse.felt_eller_kort.move"),
                                    Oversaetter.t("chance.beskrivelse.felt_eller_kort.card")
                            );

                            if (answer.equals(Oversaetter.t("chance.beskrivelse.felt_eller_kort.move"))) {
                                Spiller spiller = spil.getSpiller().getNuvarendeSpiller();

                                spil.rykSpiller(spiller, 1);
                                spil.getSpillerplade().getSpillerFelt(spiller).koerHandling(spil);
                            } else {
                                spil.getChanceBunke().draw().play(spil);
                            }
                        }
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.slik"),
                        (spil) -> spil.getSpiller().getNuvarendeSpiller().tilfoejBalance(-200)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.faengsel"),
                        (spil) -> spil.getSpiller().getNuvarendeSpiller().tilfoejFængselsKort(1)
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.foedselsdag"),
                        (spil) -> {
                            Spillerliste spillere = spil.getSpiller();
                            for (int i = 0, spillerAntal = spillere.size(); i < spillerAntal; i++) {
                                Spiller spiller = spillere.get(i);
                                spiller.tilfoejBalance(-500);
                                spillere.getNuvarendeSpiller().tilfoejBalance(500);
                            }
                        }
                ),
                new Chancekort(
                        Oversaetter.t("chance.beskrivelse.lektier"),
                        (spil) -> spil.getSpiller().getNuvarendeSpiller().tilfoejBalance(200)
                ),
        };
    }
