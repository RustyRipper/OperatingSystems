import java.util.ArrayList;
import java.util.Random;

public class GeneratorProcesow {

    public ArrayList<Proces> generujProcesy(int iloscProcesow, int maksymalnyCzasWejscia, int minimalnaDlugoscProcesu, int maksymalnaDlugoscProcesu) {

        ArrayList<Proces> procesy = new ArrayList<>(iloscProcesow);
        Random random = new Random();
        for (int i = 0; i < iloscProcesow; i++) {

            procesy.add(new Proces(random.nextInt(maksymalnyCzasWejscia), random.nextInt(maksymalnaDlugoscProcesu) + 1 + minimalnaDlugoscProcesu));
        }

        return procesy;
    }
}
