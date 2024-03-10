package testing;

import java.util.ArrayList;
import java.util.List;

public class DijkstraInspiredPrimeFinder {
    private List<Integer> primesPool = new ArrayList<>();
    private List<Integer> primeSquaresPool = new ArrayList<>();
    private boolean[] isComposite = new boolean[1000001]; 
    static int counted=0;
    static int number=1_000_000;

    public DijkstraInspiredPrimeFinder() {
        primesPool.add(2);
        primeSquaresPool.add(4); 
    }

    public void findPrimesUpTo(int limit) {
        for (int num = 3; num <= limit; num++) {
            if (isPrime(num)) {
                primesPool.add(num);
                primeSquaresPool.add(num * num);
                counted++;
            } else {
                updateComposite(num);
            }
        }
    }

    private boolean isPrime(int num) {
        if (isComposite[num]) return false; // Check the array

        for (Integer square : primeSquaresPool) { 
            if (square > num) return true;  
            if (square == num) return false;  
        }
        return false; // Should never reach here 
    }

    public void updateComposite(int num) {
        for (int i = 0; i < primesPool.size(); i++) {
            int prime = primesPool.get(i);
            if (prime * prime <= num) { 
                int square = primeSquaresPool.get(i);
                if (square == num) {
                    int multiple = square;
                    while (multiple <= number) {   
                        isComposite[multiple] = true; // Mark as composite
                        multiple += prime; 
                    }
                }
            } else {
                break; 
            }
        }
    }

    public static void main(String[] args) {
    	long starting, ending;
    	starting= System.currentTimeMillis(); // get the starting time
        DijkstraInspiredPrimeFinder finder = new DijkstraInspiredPrimeFinder();
        finder.findPrimesUpTo(number);
        System.out.println("Primes found: " + counted);
        ending=System.currentTimeMillis(); // get ending time
        System.out.println("Elapsed time: "+(ending-starting));
    }
}