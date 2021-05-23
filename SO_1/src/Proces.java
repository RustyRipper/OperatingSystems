import java.util.Comparator;

public class Proces {

    public static ComparatorCzasWejscia comparatorCzasWejscia;
    public static ComparatorCzasPozostaly comparatorCzasPozostaly;
    public static ComparatorCzasProcesu comparatorCzasProcesu;
    private int czasWejscia;
    private int czasProcesu;
    private int czasPozostaly;
    private int czasOczekiwania;


    public Proces(int czasWejscia, int czasProcesu) {
        this.czasWejscia = czasWejscia;
        this.czasProcesu = czasProcesu;
        this.czasPozostaly = czasProcesu;
        this.czasOczekiwania = 0;
        comparatorCzasWejscia = new ComparatorCzasWejscia();
        comparatorCzasProcesu = new ComparatorCzasProcesu();
        comparatorCzasPozostaly = new ComparatorCzasPozostaly();
    }


    static class ComparatorCzasWejscia implements Comparator<Proces> {

        @Override
        public int compare(Proces o1, Proces o2) {
            return o1.czasWejscia - o2.czasWejscia;
        }
    }

    static class ComparatorCzasPozostaly implements Comparator<Proces> {

        @Override
        public int compare(Proces o1, Proces o2) {
            return o1.czasPozostaly - o2.czasPozostaly;
        }
    }

    static class ComparatorCzasProcesu implements Comparator<Proces> {

        @Override
        public int compare(Proces o1, Proces o2) {
            return o1.czasProcesu - o2.czasProcesu;
        }
    }


    public int getCzasWejscia() {
        return czasWejscia;
    }

    public void setCzasWejscia(int czasWejscia) {
        this.czasWejscia = czasWejscia;
    }

    public int getCzasProcesu() {
        return czasProcesu;
    }

    public void setCzasProcesu(int czasProcesu) {
        this.czasProcesu = czasProcesu;
    }

    public int getCzasPozostaly() {
        return czasPozostaly;
    }

    public void setCzasPozostaly(int czasPozostaly) {
        this.czasPozostaly = czasPozostaly;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }
}
