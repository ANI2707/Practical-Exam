import java.util.Scanner;

public class Priority {
    // Method to sort a specific column in a 2D array (selection sort)
    public static void sortColumn(int arr[][], int start, int end, int column) {
        for (int a = start; a < end - 1; a++) {
            int min = a;
            for (int b = a + 1; b < end; b++) {
                if (arr[b][column] < arr[min][column]) {
                    min = b;
                }
            }
            // Swap values in the array based on the sorting
            int temp1 = arr[a][0];
            arr[a][0] = arr[min][0];
            arr[min][0] = temp1;
            int temp2 = arr[a][1];
            arr[a][1] = arr[min][1];
            arr[min][1] = temp2;
            int temp3 = arr[a][2];
            arr[a][2] = arr[min][2];
            arr[min][2] = temp3;
        }
    }

    public static void main(String[] args) {
        int p_no;
        float avgTAT = 0, avgWT = 0;
        int process[][] = new int[10][6]; // Assuming a maximum of 10 processes with 6 attributes
        Scanner sc = new Scanner(System.in);
        
        // Input the number of processes and their details
        System.out.print("Enter the number of processes: ");
        p_no = sc.nextInt();
        for (int i = 0; i < p_no; i++) {
            System.out.print("Arrival Time for Process " + (i + 1) + ": ");
            process[i][0] = sc.nextInt();
            System.out.print("Burst Time for Process " + (i + 1) + ": ");
            process[i][1] = sc.nextInt();
            System.out.print("Priority for Process " + (i + 1) + ": ");
            process[i][2] = sc.nextInt();
        }

        // Sort the processes based on arrival time
        sortColumn(process, 0, p_no, 0);

        int flag = 0, c_ptr = process[0][1];
        for (int a = 0; a < p_no - 1; a++) {
            int first = a + 1, last = 0;
            // Find the range of processes that have arrived by the current time
            for (int b = a + 1; b < p_no; b++) {
                if (process[b][0] <= c_ptr) {
                    last = b;
                    flag = 1;
                } else {
                    break;
                }
            }
            // If there are processes in the range, sort them based on priority
            if (flag == 1) {
                sortColumn(process, first, last + 1, 2);
                c_ptr = c_ptr + process[first][1];
            }
        }

        int temp = 0;
        // Calculate completion time, turnaround time, and waiting time for each process
        for (int j = 0; j < p_no; j++) {
            process[j][3] = temp + process[j][1];
            temp = process[j][3];
            process[j][4] = process[j][3] - process[j][0];
            avgTAT = avgTAT + process[j][4];
            process[j][5] = process[j][4] - process[j][1];
            avgWT = avgWT + process[j][5];
        }

        // Calculate average turnaround time and average waiting time
        avgTAT = avgTAT / p_no;
        avgWT = avgWT / p_no;

        // Print Priority Scheduling table
        System.out.println("\n\tPriority Scheduling");
        System.out.println("Arrival | Burst | Priority | Completion | TurnAround | Waiting");
        System.out.println("--------------------------------------------------------------");
        for (int k = 0; k < p_no; k++) {
            System.out.println(process[k][0] + "\t| " + process[k][1] + "\t| " + process[k][2] + "\t | " + process[k][3]
                    + "\t\t| " + process[k][4] + "\t | " + process[k][5]);
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("Average Turn Around Time: " + avgTAT);
        System.out.println("Average Waiting Time: " + avgWT);
        
        // Close the scanner
        sc.close();
    }
}
