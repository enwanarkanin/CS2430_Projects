package programming_project_3;

public class OptimalSelection {
static final int MAX_WEIGHT = 700;

	static class Experiment {
		String name;
		int weight;
		int rating;

		Experiment(String name, int weight, int rating) {
			this.name = name;
			this.weight = weight;
			this.rating = rating;
		}
	}

	static int totalWeight(ArrayList<Experiment> list) {
		int total = 0;
		for (Experiment e : list)
			total += e.weight;
		return total;
	}

	static int totalRating(ArrayList<Experiment> list) {
		int total = 0;
		for (Experiment e : list)
			total += e.rating;
		return total;
	}

	static ArrayList<Experiment> highestRatingFirst() {

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> b.rating - a.rating);

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> lightestFirst() {

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> a.weight - b.weight);

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> ratioFirst() {

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> Double.compare((double) b.rating / b.weight, (double) a.rating / a.weight));

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> fillPayload(ArrayList<Experiment> items) {

		ArrayList<Experiment> payload = new ArrayList<>();

		int weight = 0;

		for (Experiment e : items) {

			if (weight + e.weight <= MAX_WEIGHT) {
				payload.add(e);
				weight += e.weight;
			}
		}

		return payload;
	}

	static Experiment[] experiments = { new Experiment("Cloud Patterns", 36, 5), new Experiment("Solar Flares", 264, 9),
			new Experiment("Solar Power", 188, 6), new Experiment("Binary Stars", 203, 8),
			new Experiment("Relativity", 104, 8), new Experiment("Seed Viability", 7, 4),
			new Experiment("Sun Spots", 90, 2), new Experiment("Mice Tumors", 65, 8),
			new Experiment("Microgravity Plant Growth", 75, 5), new Experiment("Micrometeorites", 170, 9),
			new Experiment("Cosmic Rays", 80, 7), new Experiment("Yeast Fermentation", 27, 4) };

}

	static class Solution {
		ArrayList<Experiment> payload;
		int rating;
		int weight;

		Solution(ArrayList<Experiment> payload) {
			this.payload = payload;
			this.rating = totalRating(payload);
			this.weight = totalWeight(payload);
		}
	}

	static ArrayList<Solution> bruteForce() {

		ArrayList<Solution> solutions = new ArrayList<>();

		int totalSets = 1 << experiments.length;

		for (int mask = 0; mask < totalSets; mask++) { // 4096 subsets

			ArrayList<Experiment> subset = new ArrayList<>();

			for (int i = 0; i < experiments.length; i++) { // compute total weight & rating

				if ((mask & (1 << i)) != 0) {
					subset.add(experiments[i]);
				}
			}

			int weight = totalWeight(subset);

			if (weight <= MAX_WEIGHT) { // checks weight
				solutions.add(new Solution(subset));
			}
		}

		solutions.sort((a, b) -> b.rating - a.rating); // sorts by rating

		return new ArrayList<>(solutions.subList(0, 3));
	}
