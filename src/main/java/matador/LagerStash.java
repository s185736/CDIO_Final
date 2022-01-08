package matador;

public class LagerStash {

    private int maengdeBeloeb = 0;

    /*Konstruktør, opretter en ny stash.*/
    public LagerStash() {}

    /*Opretter en ny start med en startsmængde.*/
    public LagerStash(int startAmount)
    {
        this.maengdeBeloeb = startAmount;
    }

    /*Tilføj beløb til dette stash.
    * Belobet der skal tilføjes.*/
    public void tilfoejBeloeb(int amount)
    {
        this.maengdeBeloeb += amount;
    }

    /*Faa det nuværende belob af dette stash.*/
    public int getMaengdeBeloeb()
    {
        return this.maengdeBeloeb;
    }

}
