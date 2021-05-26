import java.util.Random;

public class Process {

    protected int loadCPU;
    protected int amountTasks;


    public Process(int loadCPU, int amountTasks) {
        this.loadCPU = loadCPU;
        this.amountTasks = amountTasks;
    }

    public int getLoadCPU() {
        return loadCPU;
    }

    public void setLoadCPU(int loadCPU) {
        this.loadCPU = loadCPU;
    }

    public int getAmountTasks() {
        return amountTasks;
    }

    public void setAmountTasks(int amountTasks) {
        this.amountTasks = amountTasks;
    }

    public Process() {
        Random random = new Random();
        loadCPU = random.nextInt(50)+1;
        amountTasks = random.nextInt(100)+1;
    }

    public void work() {
        amountTasks--;
    }

    public boolean isDone() {
        return amountTasks <= 0;
    }

}
