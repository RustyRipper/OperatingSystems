public class Ramka {
    private Strona strona;
    public int czasUzycia=0;
    public int nr;

    public Ramka(int nr) {
        this.nr = nr;
    }

    public Strona getStrona() {
        return strona;
    }

    public void setStrona(Strona strona) {
        this.strona = strona;
    }
}
