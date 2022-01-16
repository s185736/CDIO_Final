package matador.spiller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerTest {
    //Opretter en test spiller med en konto balance på 5000 kr.
    Spiller spiller = new Spiller("Test Spiller", 5000);

    @Test
    void tilfoejBalanceTest() {
        spiller.tilfoejBalance(1000);
        int forventet = 5000 + 1000;
        int aktuelle = spiller.getBalance();
        assertEquals(forventet, aktuelle);
    }

    @Test
    void trækBalanceTest() {
        spiller.tilfoejBalance(-1000);
        int forventet = 5000 - 1000;
        int aktuelle = spiller.getBalance();
        assertEquals(forventet, aktuelle);
    }

    @Test
    void setIFaengsel() {
    }

    @Test
    void fjernFaengselsKort() {
    }

    @Test
    void erGaaetFallit() {
    }

}