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
