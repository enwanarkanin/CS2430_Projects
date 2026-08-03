// Elisabeth Gondolo
// CS 2430, section 501
// Project name: Programming Project 3 – Spring 2026
// Code for optimal selection

package programming_project_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Highest Rating First
// - Select experiments based on highest rating without exceeding 700 kg.
//Lightest First
// - Select experiments based on lightest weight without exceeding 700 kg.
//Best Rating-to-Weight Ratio First
// - Compute score = rating / weight for each experiment.
// - Select experiments based on highest ratio while total weight ≤ 700 kg.

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

	static Experiment[] experiments = { new Experiment("Cloud Patterns", 36, 5), new Experiment("Solar Flares", 264, 9),
			new Experiment("Solar Power", 188, 6), new Experiment("Binary Stars", 203, 8),
			new Experiment("Relativity", 104, 8), new Experiment("Seed Viability", 7, 4),
			new Experiment("Sun Spots", 90, 2), new Experiment("Mice Tumors", 65, 8),
			new Experiment("Microgravity Plant Growth", 75, 5), new Experiment("Micrometeorites", 170, 9),
			new Experiment("Cosmic Rays", 80, 7), new Experiment("Yeast Fermentation", 27, 4) };

	static int totalWeight(ArrayList<Experiment> list) { // calculates the total weight
		int total = 0;
		for (Experiment e : list)
			total += e.weight;
		return total;
	}

	static int totalRating(ArrayList<Experiment> list) { // calculates the total weight
		int total = 0;
		for (Experiment e : list)
			total += e.rating;
		return total;
	}

	static void printResult(String title, ArrayList<Experiment> payload) {

		System.out.println(title + ":");
		System.out.println();

		for (Experiment e : payload) {
			System.out.println(e.name + " (Weight: " + e.weight + " kg with Rating: " + e.rating + ")");
		}

		System.out.println();
		System.out.println("Total Weight: " + totalWeight(payload) + " kg");
		System.out.println("Total Rating: " + totalRating(payload));
		System.out.println();
	}

	static ArrayList<Experiment> highestRatingFirst() { // Greedy Strategy; sort by highest rating first

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> b.rating - a.rating);

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> lightestFirst() { // Greedy strategy; sort by lightest weight first

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> a.weight - b.weight);

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> ratioFirst() { // Greedy strategy; sort by best rating-to-weight ratio

		ArrayList<Experiment> sorted = new ArrayList<>(Arrays.asList(experiments));

		sorted.sort((a, b) -> Double.compare((double) b.rating / b.weight, (double) a.rating / a.weight));

		return fillPayload(sorted);
	}

	static ArrayList<Experiment> fillPayload(ArrayList<Experiment> items) { // build payload by adding experiments under 700 kg limit

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

	static class Solution { // store solution found
		ArrayList<Experiment> payload;
		int rating;
		int weight;

		Solution(ArrayList<Experiment> payload) {
			this.payload = payload;
			this.rating = totalRating(payload);
			this.weight = totalWeight(payload);
		}
	}

//	Generates all 4096 possible subsets of experiments.
//	For each subset, computes total weight and total rating.
//	Identifies and displays the three highest-rated valid subsets (total weight ≤ 700 kg), clearly indicating which one is optimal.

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

	static ArrayList<Experiment> dynamicProgramming() {

		int n = experiments.length;

		int[][] dp = new int[n + 1][MAX_WEIGHT + 1];

		for (int i = 1; i <= n; i++) {

			Experiment e = experiments[i - 1];

			for (int w = 0; w <= MAX_WEIGHT; w++) {

				dp[i][w] = dp[i - 1][w];

				if (e.weight <= w) { // choose best option

					dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - e.weight] + e.rating);
				}
			}
		}

		// Go back through DP table to determine optimal solution

		ArrayList<Experiment> chosen = new ArrayList<>();

		int w = MAX_WEIGHT;

		for (int i = n; i > 0; i--) {

			if (dp[i][w] != dp[i - 1][w]) {

				Experiment e = experiments[i - 1];

				chosen.add(e);

				w -= e.weight;
			}
		}

		Collections.reverse(chosen); // re-do original order

		return chosen;
	}

	public static void main(String[] args) {

		ArrayList<Experiment> greedy1 = highestRatingFirst();
		ArrayList<Experiment> greedy2 = lightestFirst();
		ArrayList<Experiment> greedy3 = ratioFirst();

		printResult("Greedy Strategy: Highest Rating First", greedy1);

		printResult("Greedy Strategy: Lightest First", greedy2);

		printResult("Greedy Strategy: Best Rating-to-Weight Ratio", greedy3);

		System.out.println("Brute Force Top Three:");
		System.out.println();

		ArrayList<Solution> best = bruteForce();

		printResult("Optimal Solution", best.get(0).payload);

		printResult("Second Best Optimal Solution", best.get(1).payload);

		printResult("Third Best Optimal Solution", best.get(2).payload);

		int optimalRating = best.get(0).rating;

//		Results of all 3 greedy strategies (Part 1)
//		Result of the brute-force optimal (Part 2)
//		A brief printed summary identifying which strategies matched the optimal and which did not

		System.out.println("Comparison Summary:");

		compare("Highest Rating First", greedy1, optimalRating);
		compare("Lightest First", greedy2, optimalRating);
		compare("Best Ratio First", greedy3, optimalRating);
		System.out.println();

//		The DP table or final result, as appropriate
//		The chosen subset
//		A short comparison of runtime and code complexity vs. brute force

		ArrayList<Experiment> dp = dynamicProgramming();

		printResult("Dynamic Programming Optimal Solution", dp);

		System.out.println("For comparison between BF and DP:");
		System.out.println("Brute Force checks each subset (4096) and runtime is Big O(2^n).");
		System.out.println("Dynamic Programming uses table of experiments (8400) and runtime is big O(n * capacity).");
		System.out.println("DP is more efficient as numbers of experiments increase.");

	}

	// determine which greedy strategy matches optimal solution
	
	static void compare(String name, ArrayList<Experiment> result, int optimalRating) {

		if (totalRating(result) == optimalRating)
			System.out.println(name + ": Matches optimal");

		else
			System.out.println(name + ": Doesn't match optimal");
	}
}
