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

	static Experiment[] experiments = { new Experiment("Cloud Patterns", 36, 5), new Experiment("Solar Flares", 264, 9),
			new Experiment("Solar Power", 188, 6), new Experiment("Binary Stars", 203, 8),
			new Experiment("Relativity", 104, 8), new Experiment("Seed Viability", 7, 4),
			new Experiment("Sun Spots", 90, 2), new Experiment("Mice Tumors", 65, 8),
			new Experiment("Microgravity Plant Growth", 75, 5), new Experiment("Micrometeorites", 170, 9),
			new Experiment("Cosmic Rays", 80, 7), new Experiment("Yeast Fermentation", 27, 4) };

}
