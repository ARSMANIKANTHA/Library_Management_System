package Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // System.out.println(Arrays.toString(selectionSort(new int[]{7,1,3,9,6,1,-1,-9})));
        // System.out.println(Arrays.toString(insertionSort(new int[]{7,1,3,9,6,1,-1,-9})));
        // System.out.println(Arrays.toString(insertionSort(new int[]{9,8,7,6,5,3,4,3,2,1})));
        System.out.println(leaders(new int[]{1, 2, 5, 3, 1, 2}));
        System.out.println(leaders(new int[]{-3, 4, 5, 1, -4, -5}));
    }

    public static int[] selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for(int j=i+1;j<n;j++){
                if(nums[minIndex] > nums[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
        return nums;
    }

    public static int[] insertionSort(int[] nums){
        int n = nums.length;
        if(n <= 1) return nums;

        for(int i = 1; i< n; i++){
            int val = nums[i];
            int j = i-1;
            while(j>=0 && nums[j] > val){
                nums[j+1] = nums[j];
                --j;
            }
            nums[j+1] = val;
        }
        return nums;
    }   


    public static List<Integer> leaders(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        if(n==0){return list;}
        int curMax = nums[n-1];
        list.add(curMax);
        --n;
        while(n >= 0){
            if(nums[n] > curMax){
                list.add(0,nums[n]);
            }
            curMax = Math.max(curMax,nums[n]);
            --n;
        }
        return list;
    }
}
