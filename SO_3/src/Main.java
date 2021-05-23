import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ilosc stron");
        int iloscStron = scanner.nextInt();
        System.out.println("Podaj zakres przestrzenii");
        int zakresPrzestrzenii = scanner.nextInt();
        System.out.println("Podaj ilosc Testow");
        int iloscTestow = scanner.nextInt();


        ArrayList<Strona> strony = generujStrony(iloscStron, zakresPrzestrzenii);
        double sredniaFIFO = 0;
        double sredniaOPT = 0;
        double sredniaRAND = 0;
        double sredniaLRU = 0;
        double sredniaSCA = 0;


        for (int i = 0; i < iloscTestow; i++) {
            System.out.println("Podaj ilosc Ramek");
            int iloscRamek = scanner.nextInt();
            Algorytmy algorytmy = new Algorytmy(iloscRamek, strony);

            double FIFO = algorytmy.FIFO();
            double OPT = algorytmy.OPT();
            double RAND =algorytmy.RAND();
            double LRU =algorytmy.LRU();
            double SCA = algorytmy.SCA();

            System.out.println("FIFO  : " + FIFO );
            System.out.println("OPT   : " + OPT );
            System.out.println("RAND  : " + RAND);
            System.out.println("LRU   : " + LRU );
            System.out.println("SCA   : " + SCA);

            sredniaFIFO+=FIFO;
            sredniaOPT+=OPT;
            sredniaRAND+=RAND;
            sredniaLRU+=LRU;
            sredniaSCA+=SCA;


        }
        System.out.println("------------");
        System.out.println("FIFO  : " + sredniaFIFO/iloscTestow );
        System.out.println("OPT   : " + sredniaOPT/iloscTestow );
        System.out.println("RAND  : " + sredniaRAND/iloscTestow);
        System.out.println("LRU   : " + sredniaLRU/iloscTestow );
        System.out.println("SCA   : " + sredniaSCA/iloscTestow);

    }

    private static ArrayList<Strona> generujStrony(int iloscStron, int zakresPrzestrzenii) {
        ArrayList<Strona> strony = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < iloscStron; i++) {
            int nr = random.nextInt(zakresPrzestrzenii) + 1;
            strony.add(new Strona(nr));
        }
        return strony;
    }
}
