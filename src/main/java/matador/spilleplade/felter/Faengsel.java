package matador.spilleplade.felter;

import gui_fields.GUI_Jail;
import matador.spil.Spil;
import matador.spiller.Spiller;
import matador.Oversaetter;

import java.awt.Color;


public class Faengsel extends Felt {

    /*Boolean der bestemmer om fængselsfelt er besøg eller gå til fængslet felt.*/
    private boolean erPaaBesoeg;

    /*Konstruktør af en fængselsfelt med navn, beskrivelse samt underbeskrivelse, baggrunds samt forgrundsfarve.
    * Samt sætter erPaaBesoeg boolean.*/
    public Faengsel(boolean erPaaBesoeg) {
        super();

        this.erPaaBesoeg = erPaaBesoeg;
        String feltType;
        feltType = erPaaBesoeg ? "besoeg" : "gaatil";

        this.setFeltNavn(Oversaetter.t("spilleplade.felt.faengsel." + feltType + ".feltnavn"));
        this.setBeskrivelse(Oversaetter.t("spilleplade.felt.faengsel." + feltType + ".beskrivelse"));
        this.setUnderBeskrivelse(Oversaetter.t("spilleplade.felt.faengsel." + feltType + ".underBeskrivelse"));

        this.setBaggrundsFarve(Color.BLACK);
        this.setForgrundsFarve(Color.WHITE);
    }


    /*{@inheritDoc}*/
    @Override
    protected GUI_Jail opretGUIFelt()
    {
        return new GUI_Jail();
    }


    /*{@inheritDoc}*/
    @Override
    public GUI_Jail getGUIFelt()
    {
        return (GUI_Jail) this.gui_felt;
    }

    /*Returnere hvis dette felt er til at besøge.*/
    public boolean isErPaaBesoeg()
    {
        return this.erPaaBesoeg;
    }

    /*Handling for at komme ud af fængsel*/
    @Override
    public void koerHandling(Spil spil) {
        if (this.erPaaBesoeg) {
            return;
        }
        Spiller spiller = spil.getSpiller().getNuvarendeSpiller();
        String output = "";
        String[] valg = {"Betal", "Fængselskort"};
        String faengselMulighed = spil.getGui().getUserSelection("Hvordan vil du ud af faengsel?", valg);

        // if statement for drop ned menu over muligheder for at komme ud af fængsel
        if ( faengselMulighed.equals("Betal") ) {
            spiller.tilfoejBalance(-1000);
            spiller.setIFaengsel(false);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.action"));
            spil.rykSpiller(spiller, Oversaetter.t("spilleplade.felt.faengsel.besoeg.feltnavn"));
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.pay"));

        }
        if ( faengselMulighed.equals("Fængselskort") ) {
            spiller.getFængselsKort();
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.action"));
            spil.rykSpiller(spiller, Oversaetter.t("spilleplade.felt.faengsel.besoeg.feltnavn"));
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.free.card"));

            spiller.fjernFaengselsKort(1);
            spiller.setIFaengsel(false);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.free.card.num") + spiller.getFængselsKort() + Oversaetter.t("spilleplade.felt.faengsel.free.card.num2"));
        }
           /* if ( faengselMulighed.equals("Terningekast") ) {
                    this.terning.kast();
                    int faceValue = this.terning.getFaceValue();
                    this.getGui().setDie(faceValue);

                    if (faceValue1 == faceValue2) {
                        erPaaBesoeg = true;
                        spil.rykSpiller(spiller, Oversaetter.t("spilleplade.felt.faengsel.besoeg.feltnavn"));
                    }
                    else if (faceValue1 != faceValue2){

                    }

                } */
    }
}
