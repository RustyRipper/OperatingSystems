import java.util.ArrayList;


public class Processor {

    public ArrayList<Process> processes;

    public Processor() {
        processes = new ArrayList<>();
    }

    public void addProcess(Process process) {
        processes.add(process);
    }

    public Process removeProcess() {
        return processes.remove(processes.size() - 1);
    }


    public int sumLoad() {
        int sum = 0;
        for (Process p : processes)
            sum += p.loadCPU;

        return Math.min(sum, 100);


    }

    public int sumAmount() {
        int sum = 0;
        for (Process p : processes)
            sum += p.amountTasks;
        return sum;
    }


    public void doWork() {
        int currentLoad = 100;
        for (Process process : processes) {
            if (!process.isDone() && currentLoad >= process.loadCPU) {
                process.work();
                currentLoad -= process.loadCPU;
            }
        }
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).isDone()) {
                processes.remove(i);
                i--;
            }
        }
    }
}
