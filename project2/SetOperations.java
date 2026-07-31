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
}
