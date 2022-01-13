package matador.spilleplade.felter;

import gui_fields.GUI_Jail;
import gui_fields.GUI_Tax;
import matador.spil.Spil;
import matador.spiller.Spiller;
import matador.Oversaetter;

import java.awt.Color;


public class StatsSkat extends Felt {

    /*Konstruktør af en StatsSkat med navn, beskrivelse samt underbeskrivelse, baggrunds samt forgrundsfarve.*/
    public StatsSkat() {
        super();

        this.setFeltNavn(Oversaetter.t("spilleplade.felt.statsskat.feltnavn"));
        this.setBeskrivelse(Oversaetter.t("spilleplade.felt.statsskat.beskrivelse"));
        this.setUnderBeskrivelse(Oversaetter.t("spilleplade.felt.statsskat.underBeskrivelse"));

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
        Spiller spiller = spil.getSpiller().getNuvarendeSpiller();
        String[] valg = {"TotusindePenge"};
        String mulighedOption = spil.getGui().getUserSelection("Få betalt denne Statsskat med det samme!", valg);

        // if statement for drop ned menu over muligheder for at komme ud af feltet
        if ( mulighedOption.equals("TotusindePenge") ) {
            spiller.tilfoejBalance(-2000);
            spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.statsskat.pay1"));
        }
    }
}