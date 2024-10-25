import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] position1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println(maxDistance(position1, m1)); // Output: 3

        int[] position2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println(maxDistance(position2, m2)); // Output: 999999999
    }

    public static int maxDistance(int[] position, int m) {
        /*
        My idea of solving this, is first sorting so we get access to binary search possibility, can access max distance, have direct access to min distance
         */
        Arrays.sort(position);
        // Minimum distance -> smallest force
        int low = 1;
        // Maximum distance -> biggest force possible
        int high = position[position.length - 1] - position[0];
        int result = 0;

        // Binary search for the max min distance
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if it's possible to place balls with at least 'mid' distance
            if (canPlaceBalls(position, m, mid)) {
                result = mid; // store it as potential answer
                low = mid + 1;
            } else {
                high = mid - 1; // if not, try for a smaller minimum distance
            }
        }
        return result;
    }

    /*
    Check if we can place all 'm' balls with at least 'minDist' between them
    Here is how i think i m going to implement this method, basically first ball put it in first basket,
    then we start iterating through the full list, we keep trying the spot, if we surpass minDist or equal to it
    then that means we are good to place, this would push us into having to keep track of the last position used
    count will keep track of balls placed, if we reach 'm' that means we were successful, else, failed.
     */
    private static boolean canPlaceBalls(int[] position, int m, int minDist) {
        int count = 1; // Place first ball in the first basket
        int lastPosition = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPosition >= minDist) {
                count++;
                lastPosition = position[i];

                if (count == m) {
                    return true;
                }
            }
        }
        return false;
    }
}