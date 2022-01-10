package matador.spilleplade.chancekort;

import matador.spiller.Spiller;
import matador.Oversaetter;
import matador.spil.Spil;
import matador.spilleplade.felter.Felt;
import matador.spilleplade.genstand.Hus;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Chancekort {

    private String beskrivelse;
    private Action handling;
    
    /*Konstruktør med en beskrivelse og en tilgængelig handling når spillet startes.*/
    public Chancekort(String beskrivelse, Action handling) {
        this.beskrivelse = beskrivelse;
        this.handling = handling;
    }
    
    /*Metode til at spille chancekortene og vise det i GUI'en.*/
    public void play(Spil spil) {
        spil.getGui().displayChanceCard(this.beskrivelse);
        this.handling.run(spil);
    }

    /*Metoden til at beslutte hvem den type chancekort skal gives til.*/
    public static Action handlingType(Spiller.Type type) {
        Action action = (Spil spil) -> {
            Spiller spiller = spil.getPlayers().getSpillerVedType(type);

            if (spiller == null) {
            } else {
                spiller.setTurHandling(() -> {
                    LinkedList<Felt> felts = new LinkedList<>(Arrays.asList(spil.getFelter()));
                    //ArrayList<Felt> felts = new ArrayList<>(Arrays.asList(spil.getFelter()));
                    felts.removeIf(felt -> !(felt instanceof Hus)); //Anvendes kun til felt huse.
                    LinkedList<Felt> feltAtBruge = new LinkedList<>();
                    //ArrayList<Felt> feltAtBruge = new ArrayList<>();
                    
                    /*For loop til at tjekke om der er tilgængelige felter.*/
                    for (int i = 0, feltsSize = felts.size(); i < feltsSize; i++) {
                        Felt felt = felts.get(i);
                        if (!((Hus) felt).erEjet()) {
                            continue;
                        }
                        feltAtBruge.add(felt);
                    }
                    /*Hvis der ikke er nogle tilgængelige felter.*/
                    if (feltAtBruge.size() != 0) {
                    } else {
                        feltAtBruge = felts;
                    }
                    String[] feltNavne = new String[feltAtBruge.size()];
                    for (int i = feltAtBruge.size() - 1; i >= 0; i--) {
                        feltNavne[i] = feltAtBruge.get(i).getFeltNavn();
                    }
                    String feltNavn = spil.getGui().getUserSelection(Oversaetter.t("chance.beskrivelse.type.action"), feltNavne);

                    spil.rykSpiller(spiller, feltNavn);

                    Hus felt = (Hus) spil.getBoard().getPlayerField(spiller); //Købe felter.

                    if (!felt.erEjet()) {
                    } else {
                        felt.getEjer().tilfoejBalance(felt.getLeje());
                    }
                    felt.koebEjendom(spiller);
                });
            }
            spil.getChanceDeck().draw().play(spil);
        };
        return action;
    }


    public static Action handlingTypeFelt(Color ...types) {
        return (Spil spil) -> {
            Spiller spiller = spil.getPlayers().getNuvarendeSpiller();
            Hus[] felt = spil.getBoard().getFeltvedTypeFarve(types);

            String[] feltNavne = new String[felt.length];
            for (int i = 0; i < felt.length; i++) {
                feltNavne[i] = felt[i].getFeltNavn();
            }

            String feltNavn = spil.getGui().getUserSelection(Oversaetter.t("chance.beskrivelse.ryk_til.action"), feltNavne);
            spil.rykSpiller(spiller, feltNavn);
            Hus feltH = (Hus) spil.getBoard().getFeltVedNavn(feltNavn);

            if (feltH.erEjet()) {
                feltH.betaltLeje(spil.getBoard(), spiller);
            } else {
                feltH.setEjer(spiller);
            }
        };
    }

    /*Grænseflade for chancekort runnable handling..*/
    @FunctionalInterface
    public interface Action {
        void run(Spil spil);
    }
}