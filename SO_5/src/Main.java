import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int amountProcesses = 1000;
        int amountProcessors = 75;    //N
        int maxLoad = 50;             //p
        int amountTrial = 5;          //z
        int minLoad = 40;             //r

        ArrayList<Process> processes = generateProcesses(amountProcesses);
        Algorithms algorithms = new Algorithms(maxLoad, amountTrial, minLoad);
       for (int i = 1; i <= 3; i++) {
            ArrayList<Processor> processors = generateProcessors(amountProcessors);
            ArrayList<Process> copyProcesses = copyProcesses(processes);
           System.out.println("---------------");
            System.out.println("Wariant " + i);
            algorithms.simulate(copyProcesses, processors, i);
        }
    }


    private static ArrayList<Process> generateProcesses(int amount) {
        ArrayList<Process> processes = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            processes.add(new Process());
        }
        return processes;
    }

    private static ArrayList<Process> copyProcesses(ArrayList<Process> processes) {
        ArrayList<Process> processes2 = new ArrayList<>();
        for (Process process : processes) {
            processes2.add(new Process(process.loadCPU, process.amountTasks));
        }
        return processes2;
    }

    private static ArrayList<Processor> generateProcessors(int amount) {
        ArrayList<Processor> processors = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            processors.add(new Processor());
        }
        return processors;
    }

}
