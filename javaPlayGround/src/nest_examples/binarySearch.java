package nest_examples;

import static java.util.Arrays.sort;

public class binarySearch {
    public static void main(String[] args){
        /*two inputs array and target*/
        int[] array = new int[]{7,3,8,4,2,9,2,5,3,2,6,8,2};
        int target = 7;

        sort(array);
        System.out.print("Array Sorted: [");
        for(int i=0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.print("]\nLocated at: ");
        System.out.print(binarySearchR(array,target,0,array.length) + " in the array.");

    }
    //private recursive helper to implement binary search
    private static int binarySearchR(int[] numbers, int target, int min, int max){
        if(min > max){
            //base case
            return -1;
        }
        else{
            //recursive case
            int mid = (max + min)/2;
            if(numbers[mid] == target){
                return mid;
            }
            else if(numbers[mid] < target){
                return binarySearchR(numbers, target, mid+1, max);
            }
            else{
                return binarySearchR(numbers, target, min, mid-1);
            }
        }
    }
}
