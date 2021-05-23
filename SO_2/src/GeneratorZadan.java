import java.util.ArrayList;
import java.util.Random;

public class GeneratorZadan {

    public ArrayList<Zadanie> generujZadania(int iloscZadan, int maksymalnyDeadline, int maksymalnyCzasPrzybycia, int iloscBlokow, int procentRealTime) {
        ArrayList<Zadanie> listaZadan = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < iloscZadan; i++) {
            if (iloscZadan * procentRealTime / 100 > i) {
                listaZadan.add(new Zadanie(random.nextInt(maksymalnyCzasPrzybycia) + 1, random.nextInt(iloscBlokow) + 1, random.nextInt(maksymalnyDeadline) + 1));
            } else {
                listaZadan.add(new Zadanie(random.nextInt(maksymalnyCzasPrzybycia) + 1, random.nextInt(iloscBlokow) + 1));
            }

        }


        return listaZadan;

    }
}
