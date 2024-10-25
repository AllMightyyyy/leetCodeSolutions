import java.util.*;

public class GCDPairsSolver {

    public int[] solveGCDPairs(int[] nums, long[] queries) {
        int n = nums.length;

        Map<Integer, Integer> gcdCountMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int gcdValue = gcd(nums[i], nums[j]);
                gcdCountMap.put(gcdValue, gcdCountMap.getOrDefault(gcdValue, 0) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> gcdEntries = new ArrayList<>(gcdCountMap.entrySet());
        gcdEntries.sort(Map.Entry.comparingByKey());

        List<Integer> cumulativeCounts = new ArrayList<>();
        List<Integer> gcdList = new ArrayList<>();

        int cumulativeCount = 0;
        for (Map.Entry<Integer, Integer> entry : gcdEntries) {
            cumulativeCount += entry.getValue();
            gcdList.add(entry.getKey());
            cumulativeCounts.add(cumulativeCount);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryIndex = (int) queries[i];

            int gcdPosition = binarySearch(cumulativeCounts, queryIndex + 1);
            result[i] = gcdList.get(gcdPosition);
        }

        return result;
    }

    // Utility -> calculates gcd using Euclidean algorithm
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int binarySearch(List<Integer> cumulativeCounts, int target) {
        int left = 0, right = cumulativeCounts.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (cumulativeCounts.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
