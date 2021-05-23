import java.util.ArrayList;

public class Main3 {
    public static void main(String[] args) {

        int maksymalnyCzasPrzybycia = 100;
        int iloscZadan = 50;
        int iloscBlokow = 100;
        int maksymalnyDeadline = 1000;
        int procentRealTime = 20;
        int iloscCykli = 500;


        int sumaFCFS_EDF = 0;
        int sumaFCFS_FD = 0;
        int sumaSSTF_EDF = 0;
        int sumaSSTF_FD = 0;
        int sumaSCAN_EDF = 0;
        int sumaSCAN_FD = 0;
        int sumaC_SCAN_EDF = 0;
        int sumaC_SCAN_FD = 0;

        GeneratorZadan generatorZadan = new GeneratorZadan();
        for (int i = 0; i < iloscCykli; i++) {
            ArrayList<Zadanie> listaZadan = generatorZadan.generujZadania(iloscZadan, maksymalnyDeadline, maksymalnyCzasPrzybycia, iloscBlokow, procentRealTime);
            Dysk3 dysk3 = new Dysk3(iloscBlokow, listaZadan);

            sumaFCFS_EDF += dysk3.obliczFCFS_EDF();
            sumaFCFS_FD += dysk3.obliczFCFS_FD_SCAN();
            sumaSSTF_EDF += dysk3.obliczSSTF_EDF();
            sumaSSTF_FD += dysk3.obliczSSTF_FD_SCAN();
            sumaSCAN_EDF += dysk3.obliczSCAN_EDF();
            sumaSCAN_FD += dysk3.obliczSCAN_FD_SCAN();
            sumaC_SCAN_EDF += dysk3.obliczC_SCAN_EDF();
            sumaC_SCAN_FD += dysk3.obliczC_SCAN_FD_SCAN();
        }
        System.out.println("------------------------------");
        System.out.println("--------Średnie wyniki--------");
        System.out.println("------------------------------");
        System.out.println("FCFS_EDF   : " + sumaFCFS_EDF / iloscCykli);
        System.out.println("FCFS_FD    : " + sumaFCFS_FD / iloscCykli);
        System.out.println("------------------------------");
        System.out.println("SSTF_EDF   : " + sumaSSTF_EDF / iloscCykli);
        System.out.println("SSTF_FD    : " + sumaSSTF_FD / iloscCykli);
        System.out.println("------------------------------");
        System.out.println("SCAN_EDF   : " + sumaSCAN_EDF / iloscCykli);
        System.out.println("SCAN_FD    : " + sumaSCAN_FD / iloscCykli);
        System.out.println("------------------------------");
        System.out.println("C_SCAN_EDF : " + sumaC_SCAN_EDF / iloscCykli);
        System.out.println("C_SCAN_FD  : " + sumaC_SCAN_FD / iloscCykli);


    }
}
