package programming_project_2;


public class SetOperations {

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
}
