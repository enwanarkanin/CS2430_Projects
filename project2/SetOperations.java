// Elisabeth Gondolo
// CS 2430, section 501
// Project name: Programming Project 2 – Spring 2026
// Code for set operations

package programming_project_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetOperations {

	// Universal set size: Use a universal set with n ≥ 10 elements for Part 1.
	// representations: For Part 1 results, display:
		//	the bit string / Boolean array representation, and
		//	an element-name listing, recommended for readability.
	// Representative runs: In your Results section, include at least 2–3 representative test cases.
	// Multiset non-triviality: For Part 2, ensure both A and B include at least two elements with multiplicity > 1.
	// Labeling: Label outputs clearly so ordinary set operations are not confused with multiset operations.
	
	// Given subsets A and B of a set with n elements, use bit strings or an array of Booleans to find:
	//	NOT(A) – complement of A with respect to the universal set
	//	A ∪ B – union
	//	A ∩ B – intersection
	//	A − B – set difference
	//	A ⊕ B – symmetric difference, defined as (A − B) ∪ (B − A)
	
	// The universal set contains 10 elements as required.
	static String[] Universal = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	
	public static boolean[] setToBooleanArray(Set<String> set) { // boolean array

		boolean[] bits = new boolean[Universal.length];

		for (int i = 0; i < Universal.length; i++) {
			bits[i] = set.contains(Universal[i]);
		}

		return bits;
	}

	public static void display(String title, boolean[] bits) { // display boolean array

		System.out.println(title);

		System.out.print("Boolean Array: ");

		for (boolean b : bits) {
			System.out.print((b ? 1 : 0) + " ");
		}

		System.out.println();

		System.out.print("Elements: ");

		for (int i = 0; i < bits.length; i++) {
			if (bits[i]) {
				System.out.print(Universal[i] + " ");
			}
		}

		System.out.println("\n");
	}

	public static boolean[] complement(boolean[] A) { //NOT(A) – complement of A with respect to the universal set

		boolean[] result = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			result[i] = !A[i];
		}

		return result;
	}

	public static boolean[] union(boolean[] A, boolean[] B) { // A ∪ B – union

		boolean[] result = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			result[i] = A[i] || B[i];
		}

		return result;
	}

	public static boolean[] intersection(boolean[] A, boolean[] B) { //	A ∩ B – intersection

		boolean[] result = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			result[i] = A[i] && B[i];
		}

		return result;
	}

	public static boolean[] difference(boolean[] A, boolean[] B) { //	A − B – set difference

		boolean[] result = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			result[i] = A[i] && !B[i];
		}

		return result;
	}

	public static boolean[] symmetricDifference(boolean[] A, boolean[] B) { //	A ⊕ B – symmetric difference, defined as (A − B) ∪ (B − A)

		boolean[] result = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			result[i] = A[i] ^ B[i];
		}

		return result;
	}

	public static HashMap<String, Integer> multisetUnion(HashMap<String, Integer> A, HashMap<String, Integer> B) { // Multiset Union

		HashMap<String, Integer> result = new HashMap<>();

		HashSet<String> all = new HashSet<>();
		all.addAll(A.keySet());
		all.addAll(B.keySet());

		for (String key : all) {
			result.put(key, Math.max(A.getOrDefault(key, 0), B.getOrDefault(key, 0)));
		}

		return result;
	}

	public static HashMap<String, Integer> multisetIntersection(HashMap<String, Integer> A,
			HashMap<String, Integer> B) { // Multiset Intersection

		HashMap<String, Integer> result = new HashMap<>();

		HashSet<String> all = new HashSet<>();
		all.addAll(A.keySet());
		all.addAll(B.keySet());

		for (String key : all) {

			int count = Math.min(A.getOrDefault(key, 0), B.getOrDefault(key, 0));

			if (count > 0)
				result.put(key, count);
		}

		return result;
	}
	
	public static HashMap<String, Integer> multisetDifference(HashMap<String, Integer> A, HashMap<String, Integer> B) {// Multiset Difference

		HashMap<String, Integer> result = new HashMap<>();

		HashSet<String> all = new HashSet<>();
		all.addAll(A.keySet());
		all.addAll(B.keySet());

		for (String key : all) {

			int value = A.getOrDefault(key, 0) - B.getOrDefault(key, 0);

			if (value > 0)
				result.put(key, value);
		}

		return result;
	}

	public static HashMap<String, Integer> multisetSum(HashMap<String, Integer> A, HashMap<String, Integer> B) { // Multiset Sum

		HashMap<String, Integer> result = new HashMap<>();

		HashSet<String> all = new HashSet<>();
		all.addAll(A.keySet());
		all.addAll(B.keySet());

		for (String key : all) {

			result.put(key, A.getOrDefault(key, 0) + B.getOrDefault(key, 0));
		}

		return result;
	}

	public static void displayMultiset(String title, HashMap<String, Integer> set) { // Display multiset

		System.out.println(title);

		TreeSet<String> sorted = new TreeSet<>(set.keySet());

		for (String key : sorted) {
			System.out.println(key + " : " + set.get(key));
		}

		System.out.println();
	}

	public static void main(String[] args) {

		System.out.println("Ordinary Set Operations");
		
		System.out.println();

		Set<String>[] testA = new HashSet[] { new HashSet<>(Arrays.asList("A", "C", "E", "G", "I")),
				new HashSet<>(Arrays.asList("A", "B", "D", "F", "H")),
				new HashSet<>(Arrays.asList("B", "C", "D", "I")) };

		Set<String>[] testB = new HashSet[] { new HashSet<>(Arrays.asList("B", "C", "F", "G", "J")),
				new HashSet<>(Arrays.asList("A", "D", "E", "H", "I")),
				new HashSet<>(Arrays.asList("C", "E", "F", "G", "I", "J")) };

		for (int i = 0; i < testA.length; i++) {

			System.out.println("Test Case " + (i + 1));

			boolean[] A = setToBooleanArray(testA[i]);
			boolean[] B = setToBooleanArray(testB[i]);

			display("Set A", A);
			display("Set B", B);

			display("NOT(A)", complement(A));
			display("A ∪ B", union(A, B));
			display("A ∩ B", intersection(A, B));
			display("A − B", difference(A, B));
			display("A ⊕ B", symmetricDifference(A, B));

		}

		System.out.println("Multiset Operations");
		
		System.out.println();

		HashMap<String, Integer> multisetA = new HashMap<>();
		multisetA.put("A", 2);
		multisetA.put("B", 4);
		multisetA.put("C", 1);
		multisetA.put("D", 3);
		multisetA.put("E", 1);
		multisetA.put("F", 2);

		HashMap<String, Integer> multisetB = new HashMap<>();
		multisetB.put("A", 3);
		multisetB.put("B", 1);
		multisetB.put("C", 2);
		multisetB.put("D", 5);
		multisetB.put("E", 2);
		multisetB.put("G", 4);

		displayMultiset("Multiset A", multisetA);
		displayMultiset("Multiset B", multisetB);

		displayMultiset("Multiset union (A ∪ B)", multisetUnion(multisetA, multisetB));

		displayMultiset("Multiset intersection (A ∩ B)", multisetIntersection(multisetA, multisetB));

		displayMultiset("Multiset difference (A − B)", multisetDifference(multisetA, multisetB));

		displayMultiset("Multiset sum (A + B)", multisetSum(multisetA, multisetB));
	}
}
