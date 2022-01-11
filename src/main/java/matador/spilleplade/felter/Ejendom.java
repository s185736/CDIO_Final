package matador.spilleplade.felter;

import gui_fields.GUI_Street;
import matador.spil.Spil;
import matador.spiller.Spiller;
import matador.spilleplade.Spilleplade;

import java.awt.Color;

public class Ejendom extends Felt {

    private int leje; //leje for dette hus felt.
    private Spiller ejer; //ejeren af hus feltet.
    private Color farveType; //typen af feltet.

    /*Konstrutør af Hus med et given navn og leje. Og opretter den standarde
    * beskrivelse samt under beskrivelse.*/
    public Ejendom(String feltNavn, int leje, Color farveType) {
        super(feltNavn);
        this.setBeskrivelse("");
        this.setUnderBeskrivelse("");
        this.setBaggrundsFarve(farveType);
        this.setForgrundsFarve(Color.BLACK);
        this.leje = leje;
        this.farveType = farveType;
        this.getGUIFelt().setRent("M" + this.leje);
    }

    /*{@inheritDoc}*/
    @Override
    protected GUI_Street opretGUIFelt()
    {
        return new GUI_Street();
    }

    /*{@inheritDoc}*/
    @Override
    public GUI_Street getGUIFelt()
    {
        return (GUI_Street) this.guiField;
    }

    /*Henter typen af dette Hus.*/
    public Color getFarveType()
    {
        return this.farveType;
    }

    /*Huslejen, lejen af dette Hus.*/
    public int getLeje()
    {
        return this.leje;
    }

    /*Køb dette Hus for en spiller...*/
    public void koebEjendom(Spiller spiller) {
        this.setEjer(spiller);
        spiller.tilfoejBalance(-this.getLeje());
    }

    /*Betal dette leje af Huset for en spiller.*/
    public void betaltLeje(Spilleplade spilleplade, Spiller spiller) {
        Ejendom[] felter = spilleplade.getFeltvedTypeFarve(this.farveType);
        for (Ejendom feltH : felter) {
            if (feltH.erEjetAfSpiller(this.ejer)) {
                spiller.tilfoejBalance(-this.getLeje());
                this.ejer.tilfoejBalance(this.getLeje());
            }
        }
    }

    public void betalLejeInt(Spilleplade spilleplade, Spiller spiller) {
        Ejendom[] felter = spilleplade.getFeltvedTypeFarve(this.farveType);
        for (Ejendom felt : felter) {
            if (felt.erEjetAfSpiller(this.ejer)) {
                spiller.tilfoejBalance(-this.getLeje());
                this.ejer.tilfoejBalance(this.getLeje());
            }
        }
    }

    /*Metode til at bestemme hvis dette hus felt er ejet.*/
    public boolean erEjet()
    {
        return this.ejer != null;
    }

    /*Tjekker hvis dette hus felt er ejet af et specifik spiller.*/
    public boolean erEjetAfSpiller(Spiller spiller)
    {
        return this.ejer == spiller;
    }

    /*Opretter ejer af dette hus felt.*/
    public void setEjer(Spiller spiller) {
        this.ejer = spiller;
        this.getGUIFelt().setOwnerName(spiller.getNavn());
        this.getGUIFelt().setBorder(spiller.getSpiller().getPrimaryColor());
    }

    /*Få ejeren af dette hus felt.*/
    public Spiller getEjer()
    {
        return this.ejer;
    }

    @Override
    public void koerHandling(Spil spil) {
        Spiller spiller = spil.getPlayers().getNuvarendeSpiller();
        if (this.erEjet()) {
            if (this.erEjetAfSpiller(spiller)) {
                return;
            }
            this.betaltLeje(spil.getBoard(), spiller);
            spil.getGui().showMessage("Feltet tilhører " + this.ejer.getNavn() + ", af denne grund tjener ejeren " + this.getLeje() + "M af " + spiller.getNavn()+".");
        } else {
            this.koebEjendom(spiller);
            spil.getGui().showMessage("Feltet har ingen ejer, " + spiller.getNavn() + " køber feltet for " + this.getLeje() + "M.");
        }
    }
}