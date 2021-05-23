import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int kwantCzasu = 10;
        int iloscProcesow = 50;
        int iloscCykli = 1000;
        int minimalnaDlugoscProcesu = 1;
        int maksymalnaDlugoscProcesu = 100;
        int maksymalnyCzasWejscia = 100;
//        Scanner skaner = new Scanner(System.in);
//        System.out.println("Podaj kwant czasu");
//        int kwantCzasu = skaner.nextInt();
//        System.out.println("Podaj ilosc Procesow");
//        int iloscProcesow = skaner.nextInt();
//        System.out.println("Podaj ilosc Cykli");
//        int iloscCykli = skaner.nextInt();
//        System.out.println("Podaj minimalna dlugosc Procesu");
//        int minimalnaDlugoscProcesu = skaner.nextInt();
//        System.out.println("Podaj maksymalna dlugosc Procesu");
//        int maksymalnaDlugoscProcesu = skaner.nextInt();
//        System.out.println("Podaj maksymalny czas wejscia procesu");
//        int maksymalnyCzasWejscia = skaner.nextInt();


        double cFCFS = 0;
        double cSJF = 0;
        double cSJFw = 0;
        double cRot = 0;

        double sFCFS;
        double sSJF;
        double sSJFw;
        double sRot;

        GeneratorProcesow generatorProcesow = new GeneratorProcesow();
        Procesor procesor = new Procesor();
        for (int i = 1; i <= iloscCykli; i++) {
            ArrayList<Proces> procesy = generatorProcesow.generujProcesy(iloscProcesow, maksymalnyCzasWejscia, minimalnaDlugoscProcesu, maksymalnaDlugoscProcesu);

            sFCFS = procesor.obliczFCFS(procesy);
            cFCFS += sFCFS;
            sSJF = procesor.obliczSJF(procesy);
            cSJF += sSJF;
            sSJFw = procesor.obliczSJFw(procesy);
            cSJFw += sSJFw;
            sRot = procesor.obliczRotacyjny(procesy, kwantCzasu);
            cRot += sRot;


            System.out.println("FCFS: " + sFCFS + " | SJF: " + sSJF + " | SJFw: " + sSJFw + " | Rot: " + sRot);

        }
        System.out.println("\n\nSrednie wyniki czasu Oczekiwania");
        System.out.println("FCFS: " + cFCFS / iloscCykli + " | SJF: " + cSJF / iloscCykli + " | SJFw: " + cSJFw / iloscCykli + " | Rot: " + cRot / iloscCykli);

    }
}
