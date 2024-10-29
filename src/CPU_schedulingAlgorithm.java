import java.util.Arrays;
import java.util.Scanner;


public class CPU_schedulingAlgorithm {

    static class Process implements Comparable<Process> {
        int id;
        int bt;

        @Override
        public int compareTo(Process p) {
            return this.bt - p.bt;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n FCFS OR SJF: ");
        String input = scanner.nextLine().toUpperCase().toString();
        RUN_ALGORITHMS(input);
    }


    public static void RUN_ALGORITHMS(String input) {

        try {
            Scanner scanner = new Scanner(System.in);

            switch (input) {
                case "FCFS":
                    System.out.print("\nEnter the number of processes: ");

                    int numberOfProcesses = scanner.nextInt();

                    int[] processes = new int[numberOfProcesses];
                    int[] burstTime = new int[numberOfProcesses];

                    System.out.println("Enter the burst times for processes: ");
                    for (int i = 0; i < numberOfProcesses; i++) {
                        System.out.print("Process " + (i + 1) + ": ");
                        processes[i] = i + 1;
                        burstTime[i] = scanner.nextInt();
                    }

                    averageTime(processes, numberOfProcesses, burstTime);

                    scanner.close();
                    break;

                case "SJF":
                    System.out.print("Enter the number of processes: ");
                    int n = scanner.nextInt();

                    Process[] proc = new Process[n];

                    System.out.println("Enter the process IDs and their burst times:");
                    for (int i = 0; i < n; i++) {
                        System.out.print("Process " + (i + 1) + ": ");
                        proc[i] = new Process();
                        proc[i].id = i + 1;
                        proc[i].bt = scanner.nextInt();
                    }

                    Arrays.sort(proc);
                    findAverageTime(proc, n);

                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Enter a valid Information");
        }

    }

    public static void waitingTime(int[] processes, int numberOfProcesses, int[] bt, int[] wt) {

        wt[0] = 0;

        for (int i = 1; i < numberOfProcesses; i++)
            wt[i] = bt[i - 1] + wt[i - 1];
    }

    public static void turnaroundTime(int[] processes, int numberOfProcesses, int[] bt, int[] wt, int[] turn_around_time) { //
        for (int i = 0; i < numberOfProcesses; i++)
            turn_around_time[i] = bt[i] + wt[i];
    }

    public static void averageTime(int[] processes, int numberOfProcesses, int[] bt) {

        int[] wt = new int[numberOfProcesses];
        int[] tat = new int[numberOfProcesses];

        waitingTime(processes, numberOfProcesses, bt, wt);
        turnaroundTime(processes, numberOfProcesses, bt, wt, tat);

        float total_wt = 0, total_tat = 0;

        for (int i = 0; i < numberOfProcesses; i++) {
            total_wt += wt[i];
            total_tat += tat[i];
        }

        float avg_wt = total_wt / numberOfProcesses;
        float avg_tat = total_tat / numberOfProcesses;

        System.out.println("Average Waiting Time = " + avg_wt);
        System.out.println("Average Turnaround Time = " + avg_tat);

    }

    public static void findWaitingTime(Process[] proc, int n, int[] wt) {
        wt[0] = 0;

        for (int i = 1; i < n; i++)
            wt[i] = proc[i - 1].bt + wt[i - 1];
    }

    public static void findTurnaroundTime(Process[] proc, int n, int[] wt, int[] tat) {
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    public static void findAverageTime(Process[] proc, int n) {
        int[] wt = new int[n];
        int[] tat = new int[n];

        findWaitingTime(proc, n, wt);
        findTurnaroundTime(proc, n, wt, tat);

        float total_wt = 0, total_tat = 0;
        for (int i = 0; i < n; i++) {
            total_wt += wt[i];
            total_tat += tat[i];
        }

        float avg_wt = total_wt / n;
        float avg_tat = total_tat / n;

        System.out.println("Average Waiting Time: " + avg_wt);
        System.out.println("Average Turnaround Time: " + avg_tat);
    }


}
