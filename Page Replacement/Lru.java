import java.util.*;

public class Lru {
    // Scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Method to implement the LRU page replacement algorithm
    public void LruImplementation(int pages[], int capacity) {
        int pageFaults = 0; // Counter for page faults
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store page numbers and their access times
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
                // perform LRU replacement
                if (!currentSet.contains(pages[i])) {
                    Iterator<Integer> it = currentSet.iterator();
                    int lru = Integer.MAX_VALUE;
                    int val = 0;

                    // Find the page with the least recent access time (LRU)
                    while (it.hasNext()) {
                        int temp = it.next();
                        if (map.get(temp) < lru) {
                            lru = map.get(temp);
                            val = temp;
                        }
                    }

                    // Remove the least recently used page from the set and map
                    currentSet.remove(val);
                    map.remove(val);
                    // Add the current page to the set and update its access time
                    currentSet.add(pages[i]);
                    pageFaults++;
                }
                // Update the access time of the current page in the map
                map.put(pages[i], i);
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
        Lru lru = new Lru();

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

        // Execute LRU algorithm
        lru.LruImplementation(pages, capacity);
    }
}
