import java.util.*;

public class Optimal {
    // Scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Method to predict the future page access for optimal replacement
    private int predict(int pages[], HashSet<Integer> currentSet, int index) {
        Iterator<Integer> it = currentSet.iterator();
        int val = -1;
        int farthestIndex = index - 1;

        // Iterate through the pages in the current set
        while (it.hasNext()) {
            int temp = it.next();
            int i;
            // Find the farthest occurrence of the page in the remaining page sequence
            for (i = index; i < pages.length; i++) {
                if (pages[i] == temp) {
                    if (i > farthestIndex) {
                        farthestIndex = i;
                        val = temp;
                    }
                    break;
                }
            }
            // If the page is not found in the remaining sequence, return it for replacement
            if (i == pages.length)
                return temp;
        }
        return val;
    }

    // Method to implement the Optimal page replacement algorithm
    public void optimalImplementation(int pages[], int capacity) {
        int pageFaults = 0; // Counter for page faults
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store page numbers and their indices
        HashSet<Integer> currentSet = new HashSet<>(); // Set to keep track of pages currently in the page frame

        // Iterate through the pages
        for (int i = 0; i < pages.length; i++) {
            // If the page frame is not full, simply add the page to the set
            if (currentSet.size() < capacity) {
                if (!currentSet.contains(pages[i])) {
                    currentSet.add(pages[i]);
                    pageFaults++;
                }
            } else {
                // If the page frame is full and the current page is not in the set,
                // perform optimal replacement
                if (!currentSet.contains(pages[i])) {
                    int predictedElement = predict(pages, currentSet, i + 1);
                    // Remove the predicted page from the set and add the current page
                    currentSet.remove(predictedElement);
                    currentSet.add(pages[i]);
                    pageFaults++;
                }
            }
        }

        // Output results
        System.out.println("Page Faults: " + pageFaults);
        int pageHits = pages.length - pageFaults;
        System.out.println("Page Hits: " + pageHits);
        System.out.println("Hit Ratio: " + pageHits + "/" + pages.length + " = " + (double) pageHits / pages.length);
    }

    // Main method
    public static void main(String[] args) {
        int capacity, n, pages[];
        Optimal optimal = new Optimal();

        // Input capacity and number of pages
        System.out.print("Enter capacity of page frame: ");
        capacity = scanner.nextInt();
        System.out.print("Enter number of page sequence: ");
        n = scanner.nextInt();

        // Input page values
        pages = new int[n];
        System.out.print("Enter values (space separated): ");
        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }

        // Execute Optimal algorithm
        optimal.optimalImplementation(pages, capacity);
    }
}
