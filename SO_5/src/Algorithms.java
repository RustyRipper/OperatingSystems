import java.util.ArrayList;
import java.util.Random;

public class Algorithms {

    int maxLoad;           //p
    int amountTrial;       //z
    int minLoad;           //r

    int inquiries = 0;
    int migrations = 0;

    public Algorithms(int maxLoad, int amountTrial, int minLoad) {
        this.maxLoad = maxLoad;
        this.amountTrial = amountTrial;
        this.minLoad = minLoad;
    }

    public void simulate(ArrayList<Process> processes, ArrayList<Processor> processors, int variant) {
        inquiries = 0;
        migrations = 0;
        int counter = 0;
        double sum = 0;
        boolean check = true;
        ArrayList<Double> deviation = new ArrayList<>();
        Random random = new Random();
        do {
            check = false;
            for (Processor processor : processors) {
                if (processor.sumLoad() != 0) {
                    check = true;
                }
            }
            // losuje ile procesow ma trafic w jednej chwilii do procesorow
            int rand = random.nextInt(10) + 1;

            for (int i = 0; i < rand && processes.size() > 0; i++) {
                int r = random.nextInt(processors.size());
                processors.get(r).addProcess(processes.remove(0));
                if (variant == 1) variant1(r, processors);
                else if (variant == 2) variant2(r, processors);
                else if (variant == 3) variant3(r, processors);
            }
            double sum2 = 0;
            for (Processor processor : processors) {
                sum2 += processor.sumLoad();
                processor.doWork();


            }

            sum2 /= processors.size();
            deviation.add(sum2);
            sum += sum2;
            counter++;
        } while (check || !processes.isEmpty());
        double sumDevitation = 0;
        for (Double number : deviation) {
            sumDevitation += Math.abs(number - (sum / counter));

        }
        System.out.printf("Srednie obciazenie: %.2f%s\n", (sum / counter), "%");
        System.out.printf("Srednie Odchylenie: %.2f%s\n", sumDevitation / deviation.size(), "%");
        System.out.println("Zapytania: " + inquiries);
        System.out.println("Migracje:  " + migrations);

    }

    private void variant1(int indexOfProcessor, ArrayList<Processor> processors) {
        Random random = new Random();
        for (int i = 0; i < amountTrial; i++) {
            inquiries++;
            int x = random.nextInt(processors.size());
            if (x == indexOfProcessor) {
                i--;
                inquiries--;
            } else {
                if (processors.get(x).sumLoad() < maxLoad) {

                    processors.get(x).addProcess(processors.get(indexOfProcessor).removeProcess());
                    migrations++;
                    return;
                }
            }
        }

    }

    private void variant2(int indexOfProcessor, ArrayList<Processor> processors) {
        Random random = new Random();
        if (processors.get(indexOfProcessor).sumLoad() < maxLoad) {
            return;
        }

        boolean allOverLoad = true;
        for (int i = 0; i < processors.size() && allOverLoad; i++) {

            if (processors.get(i).sumLoad() < maxLoad)
                allOverLoad = false;
        }
        while (!allOverLoad) {
            inquiries++;
            int x = random.nextInt(processors.size());
            if (x == indexOfProcessor) {
                inquiries--;
            } else {
                if (processors.get(x).sumLoad() < maxLoad) {

                    processors.get(x).addProcess(processors.get(indexOfProcessor).removeProcess());
                    migrations++;
                    return;
                }
            }
        }


    }

    private void variant3(int indexOfProcessor, ArrayList<Processor> processors) {
        variant2(indexOfProcessor, processors);
        Random random = new Random();
        int y = 0;
        for (int i = 0; i < processors.size() && y < processors.size() / 4; i++) {
            if (processors.get(i).sumLoad() < minLoad) {
                int x = random.nextInt(processors.size());
                inquiries++;
                if (processors.get(x).sumLoad() > maxLoad) {
                    y = 0;
                    int z = processors.get(x).processes.size() / 4;
                    for (int j = 0; j < z; j++) {
                        processors.get(i).addProcess(processors.get(x).removeProcess());
                        migrations++;
                    }
                } else {
                    i--;
                    y++;
                }

            }
        }


    }

}
