import java.util.ArrayList;
import java.util.Random;

public class Algorytmy {
    private final int iloscRamek;
    private final ArrayList<Strona> strony;

    public Algorytmy(int iloscRamek, ArrayList<Strona> strony) {
        this.iloscRamek = iloscRamek;
        this.strony = strony;
    }

    public ArrayList<Ramka> generujRamki() {
        ArrayList<Ramka> ramki = new ArrayList<>(iloscRamek);
        for (int i = 0; i < iloscRamek; i++) {
            ramki.add(new Ramka());
        }
        return ramki;
    }

    public int FIFO() {
        int liczbaBledow = 0;
        int licznik = 0;
        boolean czyDodano = false;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);
        ArrayList<Ramka> ramki = generujRamki();
        while (!skopiowaneStrony.isEmpty()) {

            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    break;
                } else czyDodano = false;
            }
            if (!czyDodano) {
                ramki.get(licznik).setStrona(skopiowaneStrony.remove(0));
                liczbaBledow++;

                licznik++;
                if (licznik == iloscRamek) licznik = 0;
            }

        }


        return liczbaBledow;

    }

    public int OPT() {
        int liczbaBledow = 0;

        boolean czyDodano = false;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);
        ArrayList<Ramka> ramki = generujRamki();
        while (!skopiowaneStrony.isEmpty()) {

            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    break;

                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    break;

                } else czyDodano = false;
            }

            if (!czyDodano) {
                int index = 0;
                int odleglosc = 0;
                for (Ramka ramka : ramki) {
                    if (odleglosc < skopiowaneStrony.indexOf(ramka.getStrona())) {
                        odleglosc = skopiowaneStrony.indexOf(ramka.getStrona());
                        index = ramki.indexOf(ramka);
                    } else if (!skopiowaneStrony.contains(ramka.getStrona())) {
                        index = ramki.indexOf(ramka);
                        break;
                    }

                }
                ramki.get(index).setStrona(skopiowaneStrony.remove(0));
                liczbaBledow++;


            }

        }


        return liczbaBledow;

    }

    public int RAND() {
        Random random = new Random();
        int liczbaBledow = 0;

        boolean czyDodano = false;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);
        ArrayList<Ramka> ramki = generujRamki();
        while (!skopiowaneStrony.isEmpty()) {

            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    break;
                } else czyDodano = false;
            }
            if (!czyDodano) {

                ramki.get(random.nextInt(iloscRamek)).setStrona(skopiowaneStrony.remove(0));
                liczbaBledow++;
            }

        }


        return liczbaBledow;

    }

    public int LRU() {
        int liczbaBledow = 0;

        boolean czyDodano = false;
        int czas = 1;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);
        ArrayList<Ramka> ramki = generujRamki();
        while (!skopiowaneStrony.isEmpty()) {


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

    public int SCA() {
        int liczbaBledow = 0;
        int licznik = 0;
        boolean czyDodano = false;
        ArrayList<Strona> skopiowaneStrony = new ArrayList<>(strony);
        ArrayList<Ramka> ramki = generujRamki();
        while (!skopiowaneStrony.isEmpty()) {

            for (Ramka ramka : ramki) {
                if (ramka.getStrona() == null) {
                    ramka.setStrona(skopiowaneStrony.remove(0));
                    liczbaBledow++;
                    czyDodano = true;
                    ramka.bitOdwolania = 1;
                    break;
                } else if (ramka.getStrona().equals(skopiowaneStrony.get(0))) {
                    skopiowaneStrony.remove(0);
                    czyDodano = true;
                    ramka.bitOdwolania = 1;

                    break;

                } else czyDodano = false;
            }
            if (!czyDodano) {


                while (true) {

                    if (ramki.get(licznik).bitOdwolania == 1) {
                        ramki.get(licznik).bitOdwolania = 0;
                        licznik++;

                    } else {
                        ramki.get(licznik).setStrona(skopiowaneStrony.remove(0));
                        ramki.get(licznik).bitOdwolania = 1;
                        licznik++;
                        break;
                    }

                    if (licznik == iloscRamek) licznik = 0;


                }
                if (licznik == iloscRamek) licznik = 0;
                liczbaBledow++;
            }

        }


        return liczbaBledow;

    }
}
