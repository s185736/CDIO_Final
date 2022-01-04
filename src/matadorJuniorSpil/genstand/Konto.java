package matadorJuniorSpil.genstand;

public class Konto {

    private int penge;

    // Constructor for Konto med 1 variabel, penge
    public Konto(int penge) {
        this.penge = penge;
    }

    //Metode bruges til at se nuværende saldo på kontoen
    public int getSaldo() {
        return penge;
    }

    //Metode bruges til at sætte kontosaldo til 0
    public void setFallit(int penge) {
        this.penge = penge;
    }

    //Metode bruges til at give kontoen penge
    public void givPenge(int penge){
        this.penge = this.penge + penge;
    }

    //Metode bruges til at trække penge fra kontoen samt anvende setFallit, hvis kontosaldo går i minus
    public void tagPenge(int penge){
        int temp = getSaldo() - penge;
        if (temp < 0){
            setFallit(0);
        } else {
            this.penge = this.penge - penge;
        }
    }

    public String toString(){
        return "["+penge+"]";
    }
}

