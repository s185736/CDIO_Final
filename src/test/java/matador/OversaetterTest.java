package matador;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OversaetterTest {

    @Test
    public void getInstance() throws Exception {
        assertEquals("Oversættelse er ikke i den samme instance", Oversaetter.getInstance(), Oversaetter.getInstance());
    }


    @Test
    public void setSprog() throws Exception {
        assertTrue("Nuværende sprog er standardsprog (Dansk)", Oversaetter.getSprog().equals("dansk"));
        /*Oversaetter.setSprog("engelsk");
        assertTrue("Nuværende sprog er Engelsk.", Oversaetter.getSprog().equals("engelsk"));
    */}


    @Test
    public void getSprog() throws Exception {
        assertTrue("Nuværende sprog er standardsprog.", Oversaetter.getSprog().equals("dansk"));
    }


    @Test
    public void t() throws Exception {
        assertTrue(Oversaetter.t("velkommen1").equals("Velkommen til spillet."));
        assertTrue("Oversættelsen tager højde for parametre", Oversaetter.t("writeName", new String[] {"2"}).equals("Nummer 2's navn"));
        assertTrue("Oversættelsen har en ugyldig værdi", Oversaetter.t("empty").equals("empty"));
        assertTrue("Oversættelsen virker ikke", Oversaetter.t("notexist").equals("notexist"));
    }

}