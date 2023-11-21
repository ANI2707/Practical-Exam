import java.util.Scanner;

public class RoundRobin {
    public static void main(String args[]) {
        int n, i, qt, count = 0, temp, sq = 0, bt[], wt[], tat[], rem_bt[];
        float awt = 0, atat = 0;
        
        // Arrays to store burst time, waiting time, turnaround time, and remaining burst time for each process
        bt = new int[10];
        wt = new int[10];
        tat = new int[10];
        rem_bt = new int[10];
        
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes (maximum 10) = ");
        n = s.nextInt();
        
        // Input burst times for each process
        System.out.print("Enter the burst time of the processes:\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + " = ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i]; // Initialize remaining burst time
        }
        
        // Input time quantum
        System.out.print("Enter the quantum time: ");
        qt = s.nextInt();
        
        // Round Robin Scheduling Algorithm
        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;
                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }
                if (rem_bt[i] > qt)
                    rem_bt[i] = rem_bt[i] - qt;
                else if (rem_bt[i] >= 0) {
                    temp = rem_bt[i];
                    rem_bt[i] = 0;
                }
                sq = sq + temp;
                tat[i] = sq; // Turnaround time is the total time elapsed
            }
            if (n == count)
                break;
        }
        
        // Calculate waiting time, turnaround time, average waiting time, and average turnaround time
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\nProcess\t Burst Time\t Turnaround Time\t Waiting Time\n");
        System.out.print("--------------------------------------------------------------------------------");
        for (i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
            awt = awt + wt[i];
            atat = atat + tat[i];
            System.out.print("\n " + (i + 1) + "\t " + bt[i] + "\t\t " + tat[i] + "\t\t " + wt[i] + "\n");
        }
        awt = awt / n;
        atat = atat / n;
        
        // Output average waiting time and average turnaround time
        System.out.println("\nAverage waiting Time = " + awt + "\n");
        System.out.println("Average turnaround time = " + atat);
        
        // Close the scanner
        s.close();
    }
}
