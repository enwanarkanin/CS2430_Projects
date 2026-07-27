package programming_project_1;

import java.util.Arrays;
import java.util.Random;

// Part 1
//Create a method/function that generates arrays or lists of integers for testing.
//Your generator must support at least the required input sizes: n = 4, 6, and 8.
//Your implementation may generate random arrays, fixed sample arrays, or all permutations, depending on your design. If you generate random arrays, include enough runs to support meaningful comparison.
//Clearly describe in the report what data you generated and why.

public class AlgorithmPerformance {

	static Random random = new Random(); // for generating random numbers for testing

	public static int[] generateArray(int n) { // creates an array with random integers
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = random.nextInt(100) + 1;
		}

		return arr;
	}

	static class SortResult { // stores the sorting results and number of comparisons
		int[] sortedArray;
		int comparisons;

		SortResult(int[] sortedArray, int comparisons) {
			this.sortedArray = sortedArray;
			this.comparisons = comparisons;
		}
	}

	static int mergeComparisons; // used for tracking comparisons

	// Part 2
	// Merge sort
	// Quick sort
	// Heap sort
	// Shaker sort / cocktail sort

	public static SortResult mergeSort(int[] arr) { // for merge sort

		mergeComparisons = 0; // default is zero

		int[] copy = arr.clone(); // copy the array so original can be reused

		mergeSortRecursive(copy); // merge sort

		return new SortResult(copy, mergeComparisons); // return array and count
	}

	private static void mergeSortRecursive(int[] arr) { // merge sort method

		if (arr.length <= 1) // base case
			return;

		int mid = arr.length / 2; // find middle of array for splitting

		int[] left = Arrays.copyOfRange(arr, 0, mid); // grab the left half of the array
		int[] right = Arrays.copyOfRange(arr, mid, arr.length); // grab the right half of the array

		mergeSortRecursive(left); // sort the left
		mergeSortRecursive(right); // sort the right

		merge(arr, left, right); // merge the sorted
	}

	private static void merge(int[] arr, int[] left, int[] right) { // combine the sorted into one

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) { // compare the elements from each half

			mergeComparisons++; // count each comparison

			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < left.length) // copy remaining values from left
			arr[k++] = left[i++];

		while (j < right.length) // copy remaining values from right
			arr[k++] = right[j++];
	}

	static int quickComparisons; // used for tracking comparisons

	public static SortResult quickSort(int[] arr) {

		quickComparisons = 0; // default is zero

		int[] copy = arr.clone(); // copy the array so original can be reused

		quickSortRecursive(copy, 0, copy.length - 1); // quick sort

		return new SortResult(copy, quickComparisons); // return array and count
	}

	private static void quickSortRecursive(int[] arr, int low, int high) { // merge sort method

		if (low < high) {

			int pivotIndex = partition(arr, low, high); // find pivot

			quickSortRecursive(arr, low, pivotIndex - 1); // sort left of pivot

			quickSortRecursive(arr, pivotIndex + 1, high); // sort right of pivot
		}
	}

	private static int partition(int[] arr, int low, int high) { // rearrange around pivot

		int pivot = arr[high]; // use the last element as the pivot

		int i = low - 1;

		for (int j = low; j < high; j++) { // compare value to pivot

			quickComparisons++;

			if (arr[j] <= pivot) {

				i++;

				swap(arr, i, j); // move smaller value to left side
			}
		}

		swap(arr, i + 1, high); // put pivot in correct place

		return i + 1;
	}

	static int heapComparisons; // used for tracking comparisons

	public static SortResult heapSort(int[] arr) { // for heap sort

		heapComparisons = 0;// default is zero

		int[] copy = arr.clone(); // copy the array so original can be reused

		int n = copy.length;

		for (int i = n / 2 - 1; i >= 0; i--) { // build the max heap
			heapify(copy, n, i);
		}

		for (int i = n - 1; i > 0; i--) { // remove largest element

			swap(copy, 0, i); // move largest to end

			heapify(copy, i, 0); // restore heap
		}

		return new SortResult(copy, heapComparisons);
	}

	private static void heapify(int[] arr, int n, int i) { // converts to max heap

		int largest = i;

		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n) { // compares left

			heapComparisons++;

			if (arr[left] > arr[largest]) {
				largest = left;
			}
		}

		if (right < n) { // compares right

			heapComparisons++;

			if (arr[right] > arr[largest]) {
				largest = right;
			}
		}

		if (largest != i) { // swap and continue if root isn't largest

			swap(arr, i, largest);

			heapify(arr, n, largest);
		}
	}

	public static SortResult shakerSort(int[] arr) { // for shaker/cocktail sort

		int comparisons = 0; // default is zero

		int[] copy = arr.clone(); // copy the array so original can be reused

		boolean swapped = true;

		int start = 0;
		int end = copy.length - 1;

		while (swapped) { // continue until no swaps

			swapped = false;

			for (int i = start; i < end; i++) { // forward

				comparisons++;

				if (copy[i] > copy[i + 1]) {

					swap(copy, i, i + 1);

					swapped = true;
				}
			}

			if (!swapped) // sorted array
				break;

			swapped = false;

			end--;

			for (int i = end; i > start; i--) { // backward

				comparisons++;

				if (copy[i] < copy[i - 1]) {

					swap(copy, i, i - 1);

					swapped = true;
				}
			}

			start++;
		}

		return new SortResult(copy, comparisons);
	}

	public static void swap(int[] arr, int i, int j) { // swap elements in array

		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	// Part 3
	//Write a test driver that runs all four algorithms on the required input sizes.
	//For each run, display the input, sorted output, algorithm name, n value, and comparison count.
	//Include enough output in the report to verify that all four algorithms ran successfully.
	//Clearly label your output so the reader can distinguish algorithm results.
	
	// Part 4
	//Create a results table comparing the four algorithms for each required n value.
	//At minimum, report comparison counts for n = 4, 6, and 8.
	//If you run multiple trials or all permutations, include minimum, maximum, and average comparison counts where appropriate.
	//The report should explain what the table shows; do not include raw numbers without interpretation.
	
	public static void main(String[] args) { // for testing

		int[] sizes = { 4, 6, 8 };

		System.out.println("Sorting algorithm comparison:");

		for (int n : sizes) { // run the tests

			int[] data = generateArray(n); // generate random array

			System.out.println("\nInput Size: " + n);
			System.out.println("Original Array: " + Arrays.toString(data));

			SortResult result;

			result = mergeSort(data);
			printResult("Merge Sort", n, result);

			result = quickSort(data);
			printResult("Quick Sort", n, result);

			result = heapSort(data);
			printResult("Heap Sort", n, result);

			result = shakerSort(data);
			printResult("Shaker Sort", n, result);
		}
	}
	
	public static void printResult(String algorithm, int n, SortResult result) {

		System.out.println("\n" + algorithm);
		System.out.println("Sorted Array : " + Arrays.toString(result.sortedArray));
		System.out.println("Comparisons  : " + result.comparisons);

	}
}
