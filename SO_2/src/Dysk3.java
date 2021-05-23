import java.util.ArrayList;


public class Dysk3 {
    private static int WIELKOSC_DYSKU;
    ArrayList<Zadanie> listaZadan;

    public Dysk3(int wielkosc_dysku, ArrayList<Zadanie> listaZadan) {
        WIELKOSC_DYSKU = wielkosc_dysku;
        this.listaZadan = listaZadan;
    }

    public ArrayList<Zadanie> generujKopieZadan() {
        ArrayList<Zadanie> kopia = new ArrayList<>();
        for (Zadanie zadanie : listaZadan) {
            kopia.add(new Zadanie(zadanie.getCzasPrzybycia(), zadanie.getNumerBloku(), zadanie.getDeadline()));
        }
        kopia.sort(Zadanie.comparatorCzasPrzybycia);
        return kopia;
    }

    //done
    public int obliczFCFS_EDF() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;


        ArrayList<Zadanie> kopia = generujKopieZadan();

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();


        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {

            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            kolejkaP.sort(Zadanie.comparatorDeadline);

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                while (kolejkaP.size() != 0 && kolejkaP.get(0).getNumerBloku() == aktualnyBlok)
                    kolejkaP.remove(0);

                if (kolejkaP.size() != 0 && aktualnyBlok > kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok--;
                    iloscPrzemieszczen++;
                } else if (kolejkaP.size() != 0 && aktualnyBlok < kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok++;
                    iloscPrzemieszczen++;

                } else {
                    while (kolejka.size() != 0 && kolejka.get(0).getNumerBloku() == aktualnyBlok)
                        kolejka.remove(0);

                    if (kolejka.size() != 0 && aktualnyBlok > kolejka.get(0).getNumerBloku()) {
                        aktualnyBlok--;
                        iloscPrzemieszczen++;
                    } else if (kolejka.size() != 0 && aktualnyBlok < kolejka.get(0).getNumerBloku()) {
                        aktualnyBlok++;
                        iloscPrzemieszczen++;

                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }

        return iloscPrzemieszczen;
    }

    //done
    public int obliczFCFS_FD_SCAN() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;

        ArrayList<Zadanie> kopia = generujKopieZadan();


        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();


        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {


            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {


                for (int j = 0; j < kolejkaP.size(); j++) {
                    if (kolejkaP.get(j).getNumerBloku() == aktualnyBlok) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
                if (kolejkaP.size() != 0) {

                    if (aktualnyBlok == WIELKOSC_DYSKU) {
                        strona = false;

                    } else if (aktualnyBlok == 1) {
                        strona = true;
                    }

                    iloscPrzemieszczen++;
                    if (strona) aktualnyBlok++;
                    else aktualnyBlok--;
                } else {
                    if (kolejka.size() != 0) {

                        while (kolejka.size() != 0 && kolejka.get(0).getNumerBloku() == aktualnyBlok)
                            kolejka.remove(0);

                        if (kolejka.size() != 0 && aktualnyBlok > kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok--;
                            iloscPrzemieszczen++;
                        } else if (kolejka.size() != 0 && aktualnyBlok < kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok++;
                            iloscPrzemieszczen++;

                        }
                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }

    //done
    public int obliczSSTF_EDF() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;


        ArrayList<Zadanie> kopia = generujKopieZadan();

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();

        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {

            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            kolejkaP.sort(Zadanie.comparatorDeadline);
            Zadanie.sortujNumerBlokuObecny(aktualnyBlok, kolejka);

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                while (kolejkaP.size() != 0 && kolejkaP.get(0).getNumerBloku() == aktualnyBlok)
                    kolejkaP.remove(0);

                if (kolejkaP.size() != 0 && aktualnyBlok > kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok--;
                    iloscPrzemieszczen++;
                } else if (kolejkaP.size() != 0 && aktualnyBlok < kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok++;
                    iloscPrzemieszczen++;

                } else {
                    while (kolejka.size() != 0 && kolejka.get(0).getNumerBloku() == aktualnyBlok)
                        kolejka.remove(0);

                    if (kolejka.size() != 0 && aktualnyBlok > kolejka.get(0).getNumerBloku()) {
                        aktualnyBlok--;
                        iloscPrzemieszczen++;
                    } else if (kolejka.size() != 0 && aktualnyBlok < kolejka.get(0).getNumerBloku()) {
                        aktualnyBlok++;
                        iloscPrzemieszczen++;

                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }

    //done
    public int obliczSSTF_FD_SCAN() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;

        ArrayList<Zadanie> kopia = generujKopieZadan();
        kopia.sort(Zadanie.comparatorCzasPrzybycia);

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();


        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {
            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            Zadanie.sortujNumerBlokuObecny(aktualnyBlok, kolejka);

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {


                for (int j = 0; j < kolejkaP.size(); j++) {
                    if (kolejkaP.get(j).getNumerBloku() == aktualnyBlok) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
                if (kolejkaP.size() != 0) {

                    if (aktualnyBlok == WIELKOSC_DYSKU) {
                        strona = false;

                    } else if (aktualnyBlok == 1) {
                        strona = true;
                    }

                    iloscPrzemieszczen++;
                    if (strona) aktualnyBlok++;
                    else aktualnyBlok--;
                } else {
                    if (kolejka.size() != 0) {

                        while (kolejka.size() != 0 && kolejka.get(0).getNumerBloku() == aktualnyBlok)
                            kolejka.remove(0);

                        if (kolejka.size() != 0 && aktualnyBlok > kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok--;
                            iloscPrzemieszczen++;
                        } else if (kolejka.size() != 0 && aktualnyBlok < kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok++;
                            iloscPrzemieszczen++;

                        }

                    }
                }
                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }


        return iloscPrzemieszczen;
    }

    //done
    public int obliczSCAN_EDF() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;

        ArrayList<Zadanie> kopia = generujKopieZadan();
        kopia.sort(Zadanie.comparatorCzasPrzybycia);

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();
        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {

            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }

            kolejkaP.sort(Zadanie.comparatorDeadline);

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                while (kolejkaP.size() != 0 && kolejkaP.get(0).getNumerBloku() == aktualnyBlok)
                    kolejkaP.remove(0);

                if (kolejkaP.size() != 0 && aktualnyBlok > kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok--;
                    iloscPrzemieszczen++;
                } else if (kolejkaP.size() != 0 && aktualnyBlok < kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok++;
                    iloscPrzemieszczen++;

                } else {
                    for (int j = 0; j < kolejka.size(); j++) {
                        if (kolejka.get(j).getNumerBloku() == aktualnyBlok) {
                            kolejka.remove(kolejka.get(j));
                            j--;
                        }
                    }
                    if (kolejka.size() != 0) {

                        if (aktualnyBlok == WIELKOSC_DYSKU) {
                            strona = false;

                        } else if (aktualnyBlok == 1) {
                            strona = true;
                        }

                        iloscPrzemieszczen++;
                        if (strona) aktualnyBlok++;
                        else aktualnyBlok--;

                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }

    //done
    public int obliczSCAN_FD_SCAN() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;

        ArrayList<Zadanie> kopia = generujKopieZadan();
        kopia.sort(Zadanie.comparatorCzasPrzybycia);

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();

        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {

            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                for (int j = 0; j < kolejkaP.size(); j++) {
                    if (kolejkaP.get(j).getNumerBloku() == aktualnyBlok) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
                if (kolejkaP.size() != 0) {

                    if (aktualnyBlok == WIELKOSC_DYSKU) {
                        strona = false;

                    } else if (aktualnyBlok == 1) {
                        strona = true;
                    }

                    iloscPrzemieszczen++;
                    if (strona) aktualnyBlok++;
                    else aktualnyBlok--;
                } else {
                    for (int j = 0; j < kolejka.size(); j++) {
                        if (kolejka.get(j).getNumerBloku() == aktualnyBlok) {
                            kolejka.remove(kolejka.get(j));
                            j--;
                        }
                    }
                    if (kolejka.size() != 0) {

                        if (aktualnyBlok == WIELKOSC_DYSKU) {
                            strona = false;

                        } else if (aktualnyBlok == 1) {
                            strona = true;
                        }

                        iloscPrzemieszczen++;
                        if (strona) aktualnyBlok++;
                        else aktualnyBlok--;

                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }

    public int obliczC_SCAN_EDF() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;


        ArrayList<Zadanie> kopia = generujKopieZadan();
        kopia.sort(Zadanie.comparatorCzasPrzybycia);

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();
        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {
            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            kolejkaP.sort(Zadanie.comparatorDeadline);
            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                while (kolejkaP.size() != 0 && kolejkaP.get(0).getNumerBloku() == aktualnyBlok)
                    kolejkaP.remove(0);

                if (kolejkaP.size() != 0 && aktualnyBlok > kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok--;
                    iloscPrzemieszczen++;
                } else if (kolejkaP.size() != 0 && aktualnyBlok < kolejkaP.get(0).getNumerBloku()) {
                    aktualnyBlok++;
                    iloscPrzemieszczen++;

                } else {
                    for (int j = 0; j < kolejka.size(); j++) {
                        if (kolejka.get(j).getNumerBloku() == aktualnyBlok) {
                            kolejka.remove(kolejka.get(j));
                            j--;
                        }
                    }
                    if (kolejka.size() != 0) {

                        if (aktualnyBlok == WIELKOSC_DYSKU) {

                            // Jeśli chcemy żeby przy przejściu głowicy z jednej strony na drugą nie liczył przejść
                            // Należy zakomentować poniższą linie
                            iloscPrzemieszczen+=WIELKOSC_DYSKU;

                            aktualnyBlok = 0;
                        }


                        iloscPrzemieszczen++;
                        aktualnyBlok++;

                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }

    public int obliczC_SCAN_FD_SCAN() {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;


        ArrayList<Zadanie> kopia = generujKopieZadan();
        kopia.sort(Zadanie.comparatorCzasPrzybycia);

        ArrayList<Zadanie> kolejkaP = new ArrayList<>();
        ArrayList<Zadanie> kolejka = new ArrayList<>();
        while (kopia.size() != 0 || kolejka.size() != 0 || kolejkaP.size() != 0) {
            for (int i = 0; i < kopia.size(); ) {
                if (kopia.get(0).getCzasPrzybycia() == aktualnyCzas) {
                    if (kopia.get(0).getDeadline() > 0) {
                        kolejkaP.add(kopia.remove(0));
                    } else kolejka.add(kopia.remove(0));


                } else i = kopia.size();
            }


            kolejkaP.sort(Zadanie.comparatorDeadline);

            if (kolejkaP.size() != 0 || kolejka.size() != 0) {

                for (int j = 0; j < kolejkaP.size(); j++) {
                    if (kolejkaP.get(j).getNumerBloku() == aktualnyBlok) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
                if (kolejkaP.size() != 0) {

                    if (aktualnyBlok == WIELKOSC_DYSKU) {
                        strona = false;

                    } else if (aktualnyBlok == 1) {
                        strona = true;
                    }

                    iloscPrzemieszczen++;
                    if (strona) aktualnyBlok++;
                    else aktualnyBlok--;

                } else {
                    for (int j = 0; j < kolejka.size(); j++) {
                        if (kolejka.get(j).getNumerBloku() == aktualnyBlok) {
                            kolejka.remove(kolejka.get(j));
                            j--;
                        }
                    }
                    if (kolejka.size() != 0) {

                        if (aktualnyBlok == WIELKOSC_DYSKU) {

                            // Jeśli chcemy żeby przy przejściu głowicy z jednej strony na drugą nie liczył przejść
                            // Należy zakomentować poniższą linie
                            iloscPrzemieszczen+=WIELKOSC_DYSKU;

                            aktualnyBlok = 0;
                            iloscPrzemieszczen++;
                        }


                        iloscPrzemieszczen++;
                        aktualnyBlok++;


                    }
                }

                for (int j = 0; j < kolejkaP.size(); j++) {
                    kolejkaP.get(j).setDeadline(kolejkaP.get(j).getDeadline() - 1);
                    if (kolejkaP.get(j).getDeadline() <= 0) {
                        kolejkaP.remove(kolejkaP.get(j));
                        j--;
                    }
                }
            }
            aktualnyCzas++;
        }
        return iloscPrzemieszczen;
    }
}
