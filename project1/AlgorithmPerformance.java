package programming_project_1;

/*
 * Elisabeth Gondolo
 * CS2430, Section 501
 * Programming Project - Summer 2026
 * Algorithm Performance
 */


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

// Part 2
//Merge sort
//Quick sort
//Heap sort
//Shaker sort / cocktail sort
  
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



