package dj.leetcode.kadane;

public class LargestSumContinuousSubarray {
    public static Integer findLargestSumContinuousSubarray(int[] array){
        int max_so_far = 0;
        int max_ending_here = 0;

        for(int i = 0; i < array.length; i ++){
            max_ending_here = max_ending_here + array[i];
            if(max_ending_here <0){
                max_ending_here = 0;
            }
            if(max_so_far < max_ending_here){
                max_so_far = max_ending_here;
            }
        }

        return max_ending_here;
    }
}
