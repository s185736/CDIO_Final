package test;

import matadorJuniorSpil.genstand.Konto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCases {

    @Test
    public void kontoTrækPenge(){
        // Opretter et eksempel af Konto klassen med 20 point
        Konto konto = new Konto(20);
        // Anvender tagPenge() metoden for at trække 5 point
        konto.tagPenge(5);
        int forventetSaldo = 15;
        // Gemmer kontoens nuværende saldo
        int balance = konto.getSaldo();
        // assertEquals giver fejlmelding hvis forventetSaldo og balance ikke er det samme
        assertEquals(forventetSaldo, balance);
    }

    @Test
    public void kontoGivPenge(){
        // Opretter et eksempel af Konto klassen med 20 point
        Konto konto = new Konto(20);
        // Anvender givPenge() metoden for at give 8 point
        konto.givPenge(8);
        int forventetSaldo = 28;
        // Gemmer kontoens nuværende saldo
        int balance = konto.getSaldo();
        // assertEquals giver fejlmelding hvis forventetSaldo og balance ikke er det samme
        assertEquals(forventetSaldo, balance);
    }

    @Test
    public void kontoSetFallit(){
        // Opretter et eksempel af Konto klassen med 20 point
        Konto konto = new Konto(20);
        // Anvender setFallit() metoden for at sætte kontoens saldo til 0
        konto.setFallit(0);
        int forventetSaldo = 0;
        // Gemmer kontoens nuværende saldo
        int balance = konto.getSaldo();
        // assertEquals giver fejlmelding hvis forventetSaldo og balance ikke er det samme
        assertEquals(forventetSaldo, balance);
    }

}
