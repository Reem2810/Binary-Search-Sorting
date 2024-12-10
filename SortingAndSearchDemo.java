

import java.util.Arrays;
import java.util.Random;

public class SortingAndSearchDemo {

    // Binary Search implementation
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Insertion Sort implementation
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int toBeInserted = array[i];
            int j = i;

            while (j > 0 && array[j - 1] > toBeInserted) {
                array[j] = array[j - 1];
                j--;
            }
            
            array[j] = toBeInserted;
        }
    }



    // Selection Sort implementation
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Test the algorithms in different scenarios
    public static void main(String[] args) {
        int[] testArray = generateSortedArray(1000000);

        // Test Binary Search
        System.out.println("Binary Search:");
        testBinarySearchScenarios(testArray);

        // Test Insertion Sort
        System.out.println("Insertion Sort:");
        int[] unsortedArray = generateRandomArray(10000);
        testSortingAlgorithmScenarios(unsortedArray, "Insertion Sort", SortingAndSearchDemo::insertionSort);

        // Test Selection Sort
        System.out.println("Selection Sort:");
        unsortedArray = generateRandomArray(10000);
        testSortingAlgorithmScenarios(unsortedArray, "Selection Sort", SortingAndSearchDemo::selectionSort);
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i - 1;
        }
        return array;
    }
    private static void reverseArray(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    private static void testBinarySearchScenarios(int[] array) {
        // Worst Case Scenario
        System.out.println("Worst Case Scenario:");
        testBinarySearch(array, -1); // Element not in array

        // Additional Worse Scenario
        System.out.println("bad Scenario:");
        testBinarySearch(array, array[array.length - 1]); // Element at the end

        //  Bad Scenario
        System.out.println("Bad Scenario 1:");
        testBinarySearch(array, array[0]); // Element at the start

        // Normal Scenario
        System.out.println("Normal Scenario:");
        testBinarySearch(array, array[array.length / 4]); // Element in the first quarter

        // Best Case Scenario
        System.out.println("Best Case Scenario:");
        testBinarySearch(array, array[array.length / 2]); // Middle element


    }

    private static void testSortingAlgorithmScenarios(int[] array, String algorithmName, java.util.function.Consumer<int[]> sortFunction) {
        // Worst Case Scenario
        System.out.println(" Reversed Sorted Array Scenario:");
        int[] reversedArray = generateReverseSortedArray(array.length);
        testSortingAlgorithm(reversedArray, algorithmName, sortFunction);

        // Normal Scenario
        System.out.println("Normal Scenario:");
        testSortingAlgorithm(array, algorithmName, sortFunction);

        // Best Case Scenario
        System.out.println("Sorted Array Scenario:");
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        testSortingAlgorithm(sortedArray, algorithmName, sortFunction);


    }

    private static void testBinarySearch(int[] array, int target) {
        long startTime = System.nanoTime();
        int result = binarySearch(array, target);
        long endTime = System.nanoTime();

        System.out.println("Target: " + target + ", Result Index: " + result + ", Time Taken: " + (endTime - startTime) + " ns");
    }
    private static void testSortingAlgorithm(int[] array, String algorithmName, java.util.function.Consumer<int[]> sortFunction) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        sortFunction.accept(arrayCopy);
        long endTime = System.nanoTime();

        System.out.println(algorithmName + " Time Taken: " + (endTime - startTime) + " ns");
    }


}
