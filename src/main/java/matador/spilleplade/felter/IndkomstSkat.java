package matador.spilleplade.felter;

import gui_fields.GUI_Tax;
import matador.spil.Spil;
import matador.spiller.Spiller;
import matador.Oversaetter;

import java.awt.Color;


public class IndkomstSkat extends Felt {

    private boolean erPaaSkatFelt;

    /*Konstruktør af en indkomstSkat med navn, beskrivelse samt underbeskrivelse, baggrunds samt forgrundsfarve.
     * Samt sætter erPaaBesoeg boolean.*/
    public IndkomstSkat(boolean erPaaFeltet) {
        super();

        this.erPaaSkatFelt = erPaaFeltet;

        this.setFeltNavn(Oversaetter.t("spilleplade.felt.indkomstSkat.feltnavn"));
        this.setBeskrivelse(Oversaetter.t("spilleplade.felt.indkomstSkat.beskrivelse"));
        this.setUnderBeskrivelse(Oversaetter.t("spilleplade.felt.indkomstSkat.underBeskrivelse"));

        this.setBaggrundsFarve(Color.BLACK);
        this.setForgrundsFarve(Color.WHITE);
    }

    /*{@inheritDoc}*/
    @Override
    protected GUI_Tax opretGUIFelt()
    {
        return new GUI_Tax();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Tax getGUIFelt()
    {
        return (GUI_Tax) this.gui_felt;
    }

    /*Handling for at betale skat*/
    @Override
    public void koerHandling(Spil spil) {
        if (this.erPaaSkatFelt) {
            return;
        }
        Spiller spiller = spil.getSpiller().getNuvarendeSpiller();
        String[] valg = {"FiretusindePenge", "TiProcentAfDineVaerdier"};
        String mulighedOption = spil.getGui().getUserSelection("Hvordan vil du betale denne Indkomstskat?", valg);

        /**
         * penge: 4000,-
         * procent: 10% af sine værdier
         */
        // if statement for drop ned menu over muligheder for at komme ud af feltet
        if ( mulighedOption.equals("FiretusindePenge") ) {
            spiller.tilfoejBalance(-4000);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.indkomstskat.pay1"));

        }
        if ( mulighedOption.equals("TiProcentAfDineVaerdier") ) {
            int temp = (int) (spiller.getBalance() * 0.10);
            spiller.tilfoejBalance(-temp);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.indkomstskat.pay2"));
        }
    }
}