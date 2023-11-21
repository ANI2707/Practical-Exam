import java.util.*;

public class FIFO {
    // Scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Method to implement the FIFO page replacement algorithm
    public void FIFOImplementation(int pages[], int capacity) {
        int pageFaults = 0; // Counter for page faults
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store page numbers and their arrival times
        HashSet<Integer> currentSet = new HashSet<>(); // Set to keep track of pages currently in the page frame

        // Iterate through the pages
        for (int i = 0; i < pages.length; i++) {
            // If the page frame is not full, simply add the page to the set
            if (currentSet.size() < capacity) {
                if (!currentSet.contains(pages[i])) {
                    currentSet.add(pages[i]);
                    pageFaults++;
                }
                map.put(pages[i], i);
            } else {
                // If the page frame is full and the current page is not in the set,
                // perform FIFO replacement
                if (!currentSet.contains(pages[i])) {
                    Iterator<Integer> it = currentSet.iterator();
                    int fifo = Integer.MAX_VALUE;
                    int val = 0;
                    // Find the page with the earliest arrival time (FIFO)
                    while (it.hasNext()) {
                        int temp = it.next();
                        if (map.get(temp) < fifo) {
                            fifo = map.get(temp);
                            val = temp;
                        }
                    }
                    // Remove the oldest page from the set and map
                    currentSet.remove(val);
                    map.remove(val);
                    // Add the current page to the set and map
                    map.put(pages[i], i);
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
        FIFO fifo = new FIFO();

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

        // Execute FIFO algorithm
        fifo.FIFOImplementation(pages, capacity);
    }
}
