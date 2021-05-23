import java.util.ArrayList;

public class Procesor {


    public double obliczFCFS(ArrayList<Proces> procesy) {

        double czasOczekiwania = 0;
        procesy.sort(Proces.comparatorCzasWejscia);

        int czasZakonczenia = (procesy.get(0)).getCzasProcesu() + procesy.get(0).getCzasWejscia();
        for (int i = 1; i < procesy.size(); i++) {
            if (czasZakonczenia > procesy.get(i).getCzasWejscia()) {
                czasOczekiwania += (czasZakonczenia - procesy.get(i).getCzasWejscia());
                czasZakonczenia += (procesy.get(i)).getCzasProcesu();
            } else {
                czasZakonczenia = procesy.get(i).getCzasWejscia() + (procesy.get(i)).getCzasProcesu();
            }

        }

        return czasOczekiwania / procesy.size();
    }

    public double obliczSJF(ArrayList<Proces> procesy2) {

        ArrayList<Proces> procesy = new ArrayList<>();
        for (Proces proces : procesy2) {
            procesy.add(new Proces(proces.getCzasWejscia(), proces.getCzasProcesu()));
        }
        procesy.sort(Proces.comparatorCzasProcesu);
        procesy.sort(Proces.comparatorCzasWejscia);

        double czasOczekiwania = 0;
        int czasZakonczenia = (procesy.get(0)).getCzasProcesu() + procesy.get(0).getCzasWejscia();
        int wielkosc = procesy.size();
        procesy.remove(0);
        ArrayList<Proces> kolejka = new ArrayList<>();


        while (kolejka.size() > 0 || procesy.size() > 0) {

            while (procesy.size() != 0 && procesy.get(0).getCzasWejscia() <= czasZakonczenia) {
                kolejka.add(procesy.get(0));
                procesy.remove(0);

            }

            kolejka.sort(Proces.comparatorCzasProcesu);


            if (kolejka.size() != 0) {
                if (czasZakonczenia > kolejka.get(0).getCzasWejscia()) {
                    czasOczekiwania += (czasZakonczenia - kolejka.get(0).getCzasWejscia());
                    czasZakonczenia += (kolejka.get(0)).getCzasProcesu();
                } else {
                    czasZakonczenia = kolejka.get(0).getCzasWejscia() + (kolejka.get(0)).getCzasProcesu();
                }
                kolejka.remove(0);
            } else {
                czasZakonczenia++;
            }

        }


        return czasOczekiwania / wielkosc;
    }

    public double obliczSJFw(ArrayList<Proces> procesy2) {
        ArrayList<Proces> procesy = new ArrayList<>();
        for (Proces proces : procesy2) {
            procesy.add(new Proces(proces.getCzasWejscia(), proces.getCzasProcesu()));
        }

        procesy.sort(Proces.comparatorCzasProcesu);
        procesy.sort(Proces.comparatorCzasWejscia);

        double czasOczekiwania = 0;
        int czasZakonczenia = 0;
        int wielkosc = procesy.size();


        ArrayList<Proces> kolejka = new ArrayList<>();


        while (kolejka.size() > 0 || procesy.size() > 0) {

            while (procesy.size() != 0 && procesy.get(0).getCzasWejscia() <= czasZakonczenia) {
                kolejka.add(procesy.get(0));
                procesy.remove(0);
            }
            kolejka.sort(Proces.comparatorCzasPozostaly);


            if (kolejka.size() != 0) {

                for (int i = 0; i < kolejka.size(); i++) {
                    if (i == 0) {
                        kolejka.get(0).setCzasPozostaly(kolejka.get(0).getCzasPozostaly() - 1);

                    } else kolejka.get(i).setCzasOczekiwania(kolejka.get(i).getCzasOczekiwania() + 1);
                }
            }
            if (kolejka.size() != 0 && kolejka.get(0).getCzasPozostaly() == 0) {
                czasOczekiwania += kolejka.get(0).getCzasOczekiwania();
                kolejka.remove(0);
            }

            czasZakonczenia++;
        }


        return czasOczekiwania / wielkosc;
    }

    public double obliczRotacyjny(ArrayList<Proces> procesy2, int kwantCzasu) {
        ArrayList<Proces> procesy = new ArrayList<>();
        for (Proces proces : procesy2) {
            procesy.add(new Proces(proces.getCzasWejscia(), proces.getCzasProcesu()));
        }


        procesy.sort(Proces.comparatorCzasWejscia);

        double czasOczekiwania = 0;
        int czasZakonczenia = 0;
        int wielkosc = procesy.size();
        int index = 0;

        ArrayList<Proces> kolejka = new ArrayList<>();

        int x = kwantCzasu;
        while (kolejka.size() > 0 || procesy.size() > 0) {

            while (procesy.size() != 0 && procesy.get(0).getCzasWejscia() <= czasZakonczenia) {
                kolejka.add(procesy.get(0));
                procesy.remove(0);
            }


            if (kolejka.size() != 0) {
                for (int i = 0; i < kolejka.size(); i++) {
                    if (i == index) {
                        kolejka.get(i).setCzasPozostaly(kolejka.get(i).getCzasPozostaly() - 1);
                        x--;

                    } else kolejka.get(i).setCzasOczekiwania(kolejka.get(i).getCzasOczekiwania() + 1);
                }

            }
            czasZakonczenia++;
            if (kolejka.size() != 0) {
                if (kolejka.get(index).getCzasPozostaly() == 0 || x == 0) {

                    if (kolejka.get(index).getCzasPozostaly() == 0) {
                        czasOczekiwania += kolejka.get(index).getCzasOczekiwania();
                        kolejka.remove(index--);

                    }
                    index++;
                    if (index >= kolejka.size()) {
                        index = 0;
                    }

                    x = kwantCzasu;

                }
            }


        }
        return czasOczekiwania / wielkosc;
    }


}


