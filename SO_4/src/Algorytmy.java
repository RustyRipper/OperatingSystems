import java.util.ArrayList;
import java.util.Random;


public class Algorytmy {
    private final int iloscStron;
    private final int iloscRamek;
    private final int iloscProcesow;
    private final ArrayList<Strona> strony;
    private final ArrayList<Ramka> ramki;
    private final ArrayList<Proces> procesy;
    private final int zakresPrzestrzeni;

    public Algorytmy(int iloscRamek, int iloscProcesow, int zakresPrzestrzeni, int iloscStron) {
        this.iloscRamek = iloscRamek;
        this.iloscStron = iloscStron;
        this.iloscProcesow = iloscProcesow;
        this.zakresPrzestrzeni = zakresPrzestrzeni;
        strony = generujStrony();
        ramki = generujRamki();
        procesy = generujProcesy();


    }

    //Algorytmy
    public int equal() {
        ArrayList<Proces> procesyKopia = new ArrayList<>(procesy);
        ArrayList<Ramka> ramki2 = kopiujRamki();
        int x = iloscRamek / iloscProcesow;
        int z = x;
        int y = 0;
        int licznikBledow = 0;
        for (Proces proces : procesyKopia) {
            ArrayList<Ramka> ramkiKopia = new ArrayList<>();
            for (; y < z; y++) {
                ramkiKopia.add(ramki2.get(y));
            }
            licznikBledow += LRU(ramkiKopia, proces.getStrony());
            z += x;
        }
        return licznikBledow;
    }

    public int proportional() {
        ArrayList<Proces> procesyKopia = new ArrayList<>(procesy);
        ArrayList<Ramka> ramki2 = kopiujRamki();

        int z = 0;
        int y = 0;
        int licznikBledow = 0;
        for (Proces proces : procesyKopia) {
            int x = iloscRamek * proces.strony.size() / iloscStron;
            z += x;
            ArrayList<Ramka> ramkiKopia = new ArrayList<>();
            for (; y < z; y++) {
                ramkiKopia.add(ramki2.get(y));
            }
            licznikBledow += LRU(ramkiKopia, proces.getStrony());
            y = z;
        }
        return licznikBledow;
    }

    public int steering(int minBlad, int maxBlad) {


        ArrayList<Proces> procesyKopia = new ArrayList<>(procesy);
        int ilosc = iloscProcesow;
        ArrayList<Ramka> ramkiGlobalne = kopiujRamki();
        int licznikBledow = 0;
        for (Proces proces : procesyKopia) {
            ArrayList<Ramka> ramkiKopia = new ArrayList<>();


            int x = (ramkiGlobalne.size()) / (ilosc);
            ilosc--;


            for (int i = 0; i < x && ramkiGlobalne.size() > 1; i++) {
                ramkiKopia.add(ramkiGlobalne.remove(0));
            }
            licznikBledow += LRU2(ramkiGlobalne, ramkiKopia, proces.getStrony(), minBlad, maxBlad);

        }
        return licznikBledow;
    }

    public int workingModel(int oknoZbioru) {
        ArrayList<Proces> procesyKopia = new ArrayList<>(procesy);
        ArrayList<Ramka> ramki2 = kopiujRamki();
        int x = iloscRamek / iloscProcesow;
        int z = x;
        int y = 0;
        int licznikBledow = 0;
        for (Proces proces : procesyKopia) {
            ArrayList<Ramka> ramkiKopia = new ArrayList<>();
            for (; y < z; y++) {
                ramkiKopia.add(ramki2.get(y));
            }
            licznikBledow += LRU3(ramkiKopia, proces.getStrony(),oknoZbioru);
            z += x;
        }
        return licznikBledow;
    }



    //Pomocnicze metody
    private  ArrayList<Ramka> kopiujRamki(){
        ArrayList<Ramka> skopiowaneRamki = new ArrayList<>();
        for(Ramka ramka: ramki){
            skopiowaneRamki.add(new Ramka(ramka.nr));
        }
        return skopiowaneRamki;
    }

    private ArrayList<Strona> generujStrony() {
        ArrayList<Strona> strony = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < iloscStron; i++) {
            int nr = (int) (random.nextInt(zakresPrzestrzeni) + 1 + (5.75 * random.nextInt(zakresPrzestrzeni)));

            if (nr > zakresPrzestrzeni + 1)
                nr %= zakresPrzestrzeni;
            if (nr < (zakresPrzestrzeni / 2))
                nr += random.nextInt(zakresPrzestrzeni) / 3;
            strony.add(new Strona(nr));
        }
        return strony;
    }

    public ArrayList<Ramka> generujRamki() {
        ArrayList<Ramka> ramki = new ArrayList<>(iloscRamek);
        for (int i = 0; i < iloscRamek; i++) {
            ramki.add(new Ramka(i + 1));
        }
        return ramki;
    }

    public ArrayList<Proces> generujProcesy() {
        ArrayList<Proces> procesy = new ArrayList<>(iloscProcesow);
        int x = zakresPrzestrzeni / iloscProcesow;
        int z = x;
        int y = 0;
        for (int i = 0; i < iloscProcesow; i++) {
            Proces proces = new Proces();
            for (Strona strona : strony) {
                if (strona.nr < z && strona.nr >= y) {
                    proces.strony.add(strona);
                }

            }
            procesy.add(proces);
            y = z;
            z += x;

        }

        return procesy;
    }

    public int LRU(ArrayList<Ramka> ramki, ArrayList<Strona> strony) {
        int liczbaBledow = 0;

        boolean czyDodano = false;
        int czas = 1;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);

        while (!skopiowaneStrony.isEmpty()) {

            if(ramki.isEmpty()){
                ramki.add(new Ramka(0));
            }
            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    ramka.czasUzycia = czas;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    ramka.czasUzycia = czas;
                    break;
                } else czyDodano = false;
            }
            if (!czyDodano) {
                int czasUzycia = 0;
                int index = 0;
                for (Ramka ramka : ramki) {
                    if (ramka.czasUzycia < czasUzycia || czasUzycia == 0) {
                        czasUzycia = ramka.czasUzycia;
                        index = ramki.indexOf(ramka);
                    }
                }
                ramki.get(index).setStrona(skopiowaneStrony.remove(0));
                ramki.get(index).czasUzycia = czas;
                liczbaBledow++;

            }
            czas++;

        }


        return liczbaBledow;

    }

    private int LRU2(ArrayList<Ramka> ramkiGlobalne, ArrayList<Ramka> ramki, ArrayList<Strona> strony, int minBlad, int maxBlad) {
        int liczbaBledow = 0;
        int chwiloweBledy = 0;
        int chwilowyCzas = 0;
        boolean czyDodano = false;
        int czas = 1;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);

        while (!skopiowaneStrony.isEmpty()) {

            if (ramki.isEmpty()) {
                if(!ramkiGlobalne.isEmpty())
                ramki.add(ramkiGlobalne.remove(0));
                else ramki.add(new Ramka(0));
            }
            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    chwiloweBledy++;
                    ramka.czasUzycia = czas;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    ramka.czasUzycia = czas;
                    break;
                } else czyDodano = false;
            }
            if (!czyDodano) {
                int czasUzycia = 0;
                int index = 0;
                for (Ramka ramka : ramki) {
                    if (ramka.czasUzycia < czasUzycia || czasUzycia == 0) {
                        czasUzycia = ramka.czasUzycia;
                        index = ramki.indexOf(ramka);
                    }
                }
                ramki.get(index).setStrona(skopiowaneStrony.remove(0));
                ramki.get(index).czasUzycia = czas;
                liczbaBledow++;
                chwiloweBledy++;

            }
            czas++;
            chwilowyCzas++;
            if (chwiloweBledy < minBlad && chwilowyCzas > minBlad) {
                if (ramki.size() > 1) {

                    ramkiGlobalne.add(ramki.remove(ramki.size() - 1));
                    chwiloweBledy = 0;
                    chwilowyCzas = 0;

                }
            }
            if (chwiloweBledy > maxBlad && chwilowyCzas > maxBlad) {
                if (ramki.size() <= (3*iloscRamek/(iloscProcesow))&& ramki.size()<=ramkiGlobalne.size()-1) {
                    ramki.add(ramkiGlobalne.remove(0));
                    chwiloweBledy = 0;
                    chwilowyCzas = 0;


                }

            }

        }


        return liczbaBledow;
    }

    private int LRU3(ArrayList<Ramka> ramki, ArrayList<Strona> strony, int oknoZbioru) {
        int liczbaBledow = 0;

        boolean czyDodano = false;
        int czas = 1;

        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);

        while (!skopiowaneStrony.isEmpty()) {

            if(ramki.isEmpty()){
                ramki.add(new Ramka(0));
            }

            for(Ramka ramka:ramki){
                if(ramka.czasUzycia==(czas-oknoZbioru))
                    ramka.setStrona(null);
            }


            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    ramka.czasUzycia = czas;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    ramka.czasUzycia = czas;
                    break;
                } else czyDodano = false;
            }



            if (!czyDodano) {
                int czasUzycia = 0;
                int index = 0;
                for (Ramka ramka : ramki) {
                    if (ramka.czasUzycia < czasUzycia || czasUzycia == 0) {
                        czasUzycia = ramka.czasUzycia;
                        index = ramki.indexOf(ramka);
                    }
                }
                ramki.get(index).setStrona(skopiowaneStrony.remove(0));
                ramki.get(index).czasUzycia = czas;
                liczbaBledow++;

            }
            czas++;

        }


        return liczbaBledow;
    }

}
