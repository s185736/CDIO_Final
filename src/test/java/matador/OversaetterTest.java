package matador;

import org.junit.Test;
import org.junit.Assert;

public class OversaetterTest {

    @Test
    public void getInstance() throws Exception {
        Assert.assertEquals("Oversættelse er ikke i den samme instance", Oversaetter.getInstance(), Oversaetter.getInstance());
    }

    @Test
    public void setSprog() throws Exception {
        Assert.assertTrue("Nuværende sprog er standardsprog (Dansk)", Oversaetter.getSprog().equals("dansk"));
        Oversaetter.setSprog("engelsk");
        Assert.assertTrue("Nuværende sprog er Engelsk.", Oversaetter.getSprog().equals("engelsk"));
    }

   /* @Test
    public void getSprog() throws Exception {
        Assert.assertTrue("Nuværende sprog er standardsprog.", Oversaetter.getSprog().equals("dansk"));
    }*/

    @Test
    public void t() throws Exception {
        Assert.assertTrue(Oversaetter.t("velkommen1").equals("Velkommen til Matador spillet."));
        Assert.assertTrue("Oversættelsen tager højde for parametre", Oversaetter.t("skrivNavn", new String[] {"2"}).equals("Nummer 2's navn"));
        Assert.assertTrue("Oversættelsen har en ugyldig værdi", Oversaetter.t("tom").equals("tom"));
        Assert.assertTrue("Oversættelsen virker ikke", Oversaetter.t("ikkeeksisteres").equals("ikkeeksisteres"));
    }
}