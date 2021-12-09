package com.asdc.payroll_management.appraisal.final_rating;

public class FinalRatingConstants {

	public static final int LARGE_SCALE_TO_MEDIUM_SCALE_RATIO = 6;
	public static final int MEDIUM_SCALE_TO_SMALL_SCALE_RATIO = 6;

	public enum ProjectSize {
		SMALL_SCALE("Small scale"), MEDIUM_SCALE("Medium scale"), LARGE_SCALE("Large scale");

		private String value;

		private ProjectSize(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	public enum ContributionToRating {
		ONE(0d, 3d), TWO(4d, 9d), THREE(10d, 19d), FOUR(20d, 35d), FIVE(35d, 100000d);

		private double min;
		private double max;

		private ContributionToRating(double min, double max) {
			this.min = min;
			this.max = max;
		}

		public double getMin() {
			return this.min;
		}

		public double getMax() {
			return this.max;
		}
	}

	public enum TechnologiesToRating {
		ONE(-1d, 0d), TWO(0d, 1d), THREE(1d, 2d), FOUR(3d, 6d), FIVE(6d, 100000d);

		private double min;
		private double max;

		private TechnologiesToRating(double min, double max) {
			this.min = min;
			this.max = max;
		}

		public double getMin() {
			return this.min;
		}

		public double getMax() {
			return this.max;
		}
	}
}
