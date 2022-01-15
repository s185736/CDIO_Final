package matador.spiller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerTest {
    Spiller spiller;

    @Test
    void setIFaengsel() {
    }

    @Test
    void tilfoejBalanceTest() {
        int temp = spiller.getBalance();
        spiller.tilfoejBalance(1000);
        int forventet = temp + 1000;
        int aktuelle = spiller.getBalance();
        assertEquals(forventet, aktuelle);
    }

    @Test
    void fjernFaengselsKort() {
    }

    @Test
    void erGaaetFallit() {
    }
}