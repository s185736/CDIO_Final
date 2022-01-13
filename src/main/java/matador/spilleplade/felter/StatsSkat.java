package matador.spilleplade.felter;

import gui_fields.GUI_Jail;
import gui_fields.GUI_Tax;
import matador.spil.Spil;
import matador.spiller.Spiller;
import matador.Oversaetter;

import java.awt.Color;


public class StatsSkat extends Felt {

    private boolean erPaaSkatFelt;

    /*Konstruktør af en indkomstSkat med navn, beskrivelse samt underbeskrivelse, baggrunds samt forgrundsfarve.
     * Samt sætter erPaaBesoeg boolean.*/
    public StatsSkat(boolean erPaaFeltet) {
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
        String output = "";
        String[] valg = {"TotusindePenge"};
        String betalOption = spil.getGui().getUserSelection("Betal denne Statsskat nu!", valg);

        // if statement for drop ned menu over muligheder for at komme ud af feltet
        if ( betalOption.equals("TotusindePenge") ) {
            spiller.tilfoejBalance(-2000);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.indkomstskat.pay1"));
        }
    }
}