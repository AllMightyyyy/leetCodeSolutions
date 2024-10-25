import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GCDPairsSolver solver = new GCDPairsSolver();

        int[] nums1 = {2, 3, 4};
        int[] queries1 = {0, 2, 2};
        System.out.println(Arrays.toString(solver.solveGCDPairs(nums1, queries1)));

        int[] nums2 = {4, 4, 2, 1};
        int[] queries2 = {5, 3, 1, 0};
        System.out.println(Arrays.toString(solver.solveGCDPairs(nums2, queries2)));

        int[] nums3 = {2, 2};
        int[] queries3 = {0, 0};
        System.out.println(Arrays.toString(solver.solveGCDPairs(nums3, queries3)));


    }

}

