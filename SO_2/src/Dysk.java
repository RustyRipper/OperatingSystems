import java.util.ArrayList;


public class Dysk {
    private static int WIELKOSC_DYSKU;
    ArrayList<Zadanie> listaZadan;

    public Dysk(int wielkosc_dysku, ArrayList<Zadanie> listaZadan) {
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


    public int obliczIloscPrzemieszczen(String typAlgorytmu, String typAlgorytmuRealTime) {
        int iloscPrzemieszczen = 0;
        int aktualnyCzas = 0;
        int aktualnyBlok = 1;
        boolean strona = true;
        boolean flaga = true;
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

                if (typAlgorytmuRealTime.equals("EDF")) {
                    kolejkaP.sort(Zadanie.comparatorDeadline);

                    while (kolejkaP.size() != 0 && kolejkaP.get(0).getNumerBloku() == aktualnyBlok)
                        kolejkaP.remove(0);

                    if (kolejkaP.size() != 0 && aktualnyBlok > kolejkaP.get(0).getNumerBloku()) {
                        aktualnyBlok--;
                        iloscPrzemieszczen++;
                    } else if (kolejkaP.size() != 0 && aktualnyBlok < kolejkaP.get(0).getNumerBloku()) {
                        aktualnyBlok++;
                        iloscPrzemieszczen++;

                    } else flaga = true;
                } else if (typAlgorytmuRealTime.equals("FD_SCAN")) {

                    for (int j = 0; j < kolejkaP.size(); j++) {
                        if (kolejkaP.get(j).getNumerBloku() == aktualnyBlok) {
                            kolejkaP.remove(kolejkaP.get(j));
                            j--;
                        }
                    }
                    if (kolejkaP.size() != 0) {

                        kolejkaP.sort(Zadanie.comparatorDeadline);
                        if (kolejkaP.get(0).getNumerBloku() >= aktualnyBlok) {
                            strona = true;
                        } else if (kolejkaP.get(0).getNumerBloku() < aktualnyBlok) {
                            strona = false;
                        }


                        iloscPrzemieszczen++;
                        if (strona) aktualnyBlok++;
                        else aktualnyBlok--;

                    } else flaga = true;


                }
                if (flaga) {
                    flaga = false;

                    if (typAlgorytmu.equals("FCFS") || typAlgorytmu.equals("SSTF")) {

                        if (typAlgorytmu.equals("SSTF")) Zadanie.sortujNumerBlokuObecny(aktualnyBlok, kolejka);

                        while (kolejka.size() != 0 && kolejka.get(0).getNumerBloku() == aktualnyBlok)
                            kolejka.remove(0);

                        if (kolejka.size() != 0 && aktualnyBlok > kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok--;
                            iloscPrzemieszczen++;
                        } else if (kolejka.size() != 0 && aktualnyBlok < kolejka.get(0).getNumerBloku()) {
                            aktualnyBlok++;
                            iloscPrzemieszczen++;

                        }


                    } else if (typAlgorytmu.equals("SCAN") || typAlgorytmu.equals("C_SCAN")) {
                        for (int j = 0; j < kolejka.size(); j++) {
                            if (kolejka.get(j).getNumerBloku() == aktualnyBlok) {
                                kolejka.remove(kolejka.get(j));
                                j--;
                            }
                        }
                        if (kolejka.size() != 0) {
                            if (typAlgorytmu.equals("SCAN")) {
                                if (aktualnyBlok == WIELKOSC_DYSKU) {
                                    strona = false;

                                } else if (aktualnyBlok == 1) {
                                    strona = true;
                                }


                                if (strona) aktualnyBlok++;
                                else aktualnyBlok--;
                            } else if (typAlgorytmu.equals("C_SCAN")) {
                                if (aktualnyBlok == WIELKOSC_DYSKU) {

                                    // Jeśli chcemy żeby przy przejściu głowicy z jednej strony na drugą nie liczył przejść
                                    // Należy zakomentować poniższą linie
                                    iloscPrzemieszczen += WIELKOSC_DYSKU;

                                    aktualnyBlok = 0;
                                }

                                aktualnyBlok++;
                            }
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


}


