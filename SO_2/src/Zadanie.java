import java.util.ArrayList;
import java.util.Comparator;

public class Zadanie {
    private final int czasPrzybycia;
    private final int numerBloku;
    private int deadline;

    public Zadanie(int czasPrzybycia, int numerBloku) {
        this.czasPrzybycia = czasPrzybycia;
        this.numerBloku = numerBloku;
    }

    public Zadanie(int czasPrzybycia, int numerBloku, int deadline) {
        this.czasPrzybycia = czasPrzybycia;
        this.numerBloku = numerBloku;
        this.deadline = deadline;
    }

    public int getCzasPrzybycia() {
        return czasPrzybycia;
    }

    public int getNumerBloku() {
        return numerBloku;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public static Comparator<Zadanie> comparatorCzasPrzybycia = new Comparator<>() {
        @Override
        public int compare(Zadanie o1, Zadanie o2) {
            return o1.czasPrzybycia - o2.czasPrzybycia;
        }
    };
    public static Comparator<Zadanie> comparatorDeadline = new Comparator<>() {
        @Override
        public int compare(Zadanie o1, Zadanie o2) {
            return o1.deadline - o2.deadline;
        }
    };

    public static void sortujNumerBlokuObecny(int aktualnyBlok, ArrayList<Zadanie> lista) {
        Comparator<Zadanie> comparatorNumerBlokuObecny = new Comparator<>() {

            public int compare(Zadanie o1, Zadanie o2) {

                return Math.abs(o1.numerBloku - aktualnyBlok) - Math.abs(o2.numerBloku - aktualnyBlok);
            }
        };
        lista.sort(comparatorNumerBlokuObecny);
    }

}
