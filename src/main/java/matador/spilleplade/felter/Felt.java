package matador.spilleplade.felter;

import gui_fields.GUI_Field;
import matador.spil.Spil;
import matador.spiller.Spiller;

import java.awt.Color;


public abstract class Felt {

    private String feltNavn; //navn af feltet.
    private String beskrivelse; //beskrivelse af feltet.
    private String underBeskrivelse; //sub-beskrivelse af feltet.
    protected GUI_Field gui_felt; //GUI_Field af dette felt.

    /*Konstruktør af Felt.*/
    public Felt()
    {
        this.gui_felt = this.opretGUIFelt();
    }


    /*Konstrutør af Felt med et navn.*/
    public Felt(String feltNavn) {
        this();
        this.setFeltNavn(feltNavn);
    }

    /*Konstrutør af Felt med alle nødvendige informationer.*/
    public Felt(String feltNavn, String beskrivelse, String underBeskrivelse, Color baggrundsfarve, Color forgrundsfarve) {
        this(feltNavn);
        this.setBeskrivelse(beskrivelse).setUnderBeskrivelse(underBeskrivelse);
        this.setBaggrundsFarve(baggrundsfarve).setForgrundsFarve(forgrundsfarve);
    }

    /*Opretter den korrekte GUI_field. Denne metode er kaldet i konstruktøren og skal returnere den korrekte GUI_field.*/
    abstract protected GUI_Field opretGUIFelt();

    /*Dette skal returnere GUI_field med den korrekte type.*/
    abstract public GUI_Field getGUIFelt();

    public abstract void koerHandling(Spil spil);

    /*Herunder fås navnet af det nuværende felt.*/
    public String getFeltNavn()
    {
        return this.feltNavn;
    }

    /*Oprette navnet af feltet, også på den korresponderet GUI_felt.*/
    public Felt setFeltNavn(String feltNavn) {
        this.feltNavn = feltNavn;
        this.gui_felt.setTitle(this.feltNavn);
        return this;
    }

    /*Giver beskrivelse af dette felt..*/
    public String getBeskrivelse()
    {
        return beskrivelse;
    }

    /*Opretter beskrivelsen af dette felt og med dens korresponderede GUI_field.*/
    public Felt setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
        this.gui_felt.setDescription(this.beskrivelse);
        return this;
    }

    /*Giver dig sub beskrivelse af dette felt.*/
    public String getUnderBeskrivelse()
    {
        return this.underBeskrivelse;
    }


    /*Opretter sub beskrivelsen af dette felt og med dens korresponderede GUI_field.*/
    public Felt setUnderBeskrivelse(String underBeskrivelse) {
        this.underBeskrivelse = underBeskrivelse;
        this.gui_felt.setSubText(this.underBeskrivelse);
        return this;
    }
    /*Opretter baggrundsfarven på dette felt og med dens korresponderede GUI_field.*/
    public Felt setBaggrundsFarve(Color baggrundsfarve) {
        this.gui_felt.setBackGroundColor(baggrundsfarve);
        return this;
    }

    /*Opretter forgrundsfarven på dette felt og med dens korresponderede GUI_field.*/
    public Felt setForgrundsFarve(Color forgrundsfarve) {
        this.gui_felt.setForeGroundColor(forgrundsfarve);
        return this;
    }

    public void setBil(Spiller spiller) {
        this.gui_felt.setCar(spiller.getSpiller(), true);
    }

    public void fjernBil(Spiller spiller) {
        this.gui_felt.setCar(spiller.getSpiller(), false);
    }
}