package genstand;

public class Terning {

    private int øjne;
    private int terningSider = 6;

    //Metode til at simulere et terning kast
    public int kast() {
        return this.øjne = (int) (Math.random() * terningSider) + 1;
    }

    //Metode til at hente øjne fra terning kast
    public int getØjne() {
        return this.øjne;
    }
}
