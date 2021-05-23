public class Main {

    public static void main(String[] args) {

        int iloscStron = 10000;
        int zakresPrzestrzenii =250;
        int iloscProcesow = 10;
        int iloscRamek = 100;
        int minBlad=6;
        int maxBlad=15;
        int oknoZbioru=17;

        System.out.println("Ilosc stron:           "+iloscStron);
        System.out.println("Zakres przestrzenii:   "+zakresPrzestrzenii);
        System.out.println("Ilosc procesow:        "+iloscProcesow);
        System.out.println("Ilosc ramek:           "+iloscRamek);
        System.out.println("Minimalny blad:        "+minBlad);
        System.out.println("Maksymalny blad:       "+maxBlad);
        System.out.println("Okno zbioru roboczego: "+oknoZbioru);
        System.out.println();


        Algorytmy a = new Algorytmy(iloscRamek,iloscProcesow,zakresPrzestrzenii,iloscStron);


        System.out.println("Equal:        "+a.equal());
        System.out.println("Proportional: "+a.proportional());
        System.out.println("Steering:     "+a.steering(minBlad,maxBlad));
        System.out.println("Working-Set:  "+a.workingModel(oknoZbioru));
        System.out.println();



    }



}
