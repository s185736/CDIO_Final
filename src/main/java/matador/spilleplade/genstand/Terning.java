package matador.spilleplade.genstand;

public class Terning {

    private int antalSider; //antal sider på terningen.
    private int faceValue; //den nuværende værdi af terningen.
    public Terning()
    {
        this(12);
    } //Konstruktør, af en terning med 6 sider.

    /*Konstruktør af en terning med ethvert nummer af sider.*/
    public Terning(int antalSider)
    {
        this.antalSider = antalSider;
    }

    /*Kast terningen, dette vil ændre faceValue..*/
    public void kast()
    {
        this.faceValue = (int) (Math.random() * this.antalSider) + 1;
    }

    /*Få fat på den nuværende faceValue af terningen.*/
    public int getFaceValue()
    {
        return this.faceValue;
    }

    /*Få antal af siderne for terningen.*/
    public int getAntalSider()
    {
        return this.antalSider;
    }
}