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
        String fieldType;
        fieldType = erPaaBesoeg ? "besoeg" : "gaatil"; // fieldType for de to fængselsfelter

        this.setFeltNavn(Oversaetter.t("spilleplade.felt.faengsel." + fieldType + ".feltnavn"));
        this.setBeskrivelse(Oversaetter.t("spilleplade.felt.faengsel." + fieldType + ".beskrivelse"));
        this.setUnderBeskrivelse(Oversaetter.t("spilleplade.felt.faengsel." + fieldType + ".underBeskrivelse"));

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
        return (GUI_Jail) this.guiField;
    }

    /*Returnere hvis dette felt er til at besøge.*/
    public boolean isErPaaBesoeg()
    {
        return this.erPaaBesoeg;
    }


    /*Hvis Spiller er i fængsel.*/
    @Override
    public void koerHandling(Spil spil) {
        if (this.erPaaBesoeg) {
            return;
        }
      /*  Spiller spiller = spil.getPlayers().getNuvarendeSpiller();
        switch (spiller.getFængselsKort()) {
            case 0: //Betal for at komme ud af fængsel
                spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.action"));
                spil.rykSpiller(spiller, Oversaetter.t("spilleplade.felt.faengsel.besoeg.feltnavn"));

                spiller.tilfoejBalance(-2);
                spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.pay"));
                break;
            default: //Brug slip ud af Fængsels kort
                spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.action"));
                spil.rykSpiller(spiller, Oversaetter.t("spilleplade.felt.faengsel.besoeg.feltnavn"));
                spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.free.card"));

                spiller.fjernFaengselsKort(1);
                spil.getGui().showMessage(Oversaetter.t("spilleplade.felt.faengsel.free.card.num") + spiller.getFængselsKort() + Oversaetter.t("spilleplade.felt.faengsel.free.card.num2"));
                break;
        } */

        Spiller spiller = spil.getPlayers().getNuvarendeSpiller();
            // switchcase for drop ned menu over muligheder for at komme ud af fængsel
        switch (spiller.forladFaengsel()){

        }




    }
}
