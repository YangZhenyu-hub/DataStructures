package binarysearchnorecursion;

/**
 * @ClassName BinarySearchNoRecursion
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-03-23 19:01
 * @Version
 **/
public class BinarySearchNoRecursion {
    public static void main(String[] args) {

    }

    public static int binarySearch(int[] arr,int target) {
        int left=0;
        int right=arr.length-1;
        while (left <= right) {
            int mid=(left+right)/2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
