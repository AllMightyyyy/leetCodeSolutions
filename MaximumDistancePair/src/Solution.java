public class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        int j = 0;
        for (int i = 0; i < nums1.length; i++) {
            j = Math.max(j, i);
            int low = j, high = nums2.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums1[i] <= nums2[mid]) {
                    j = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            maxDist = Math.max(maxDist, j - i);
        }

        return maxDist;
    }
}