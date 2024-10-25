import java.util.Arrays;

public class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = left + ( right - left ) / 2;
            if (canFormPair(nums, p, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean canFormPair(int[] nums, int p, int minMaxDiff) {
        int pairCount = 0;
        for (int i = 1; i < nums.length ; i++) {
            if ( nums[i] - nums[i - 1] <= minMaxDiff ) {
                pairCount++;
                i++;
            }

            if ( pairCount == p ) {
                return true;
            }
        }
        return pairCount >= p;
    }
}