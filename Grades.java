//Billy Phan
//CIS1068
//Grade Calculator
//Takes user input for homework and exam 1 grades to get exam 2 grades to calculate course average.

package grades;
import java.util.*;
public class Grades {

	public static void main(String[] args) {
		
		System.out.println("The following program accepts your homework and two exam scores\nas input and computes your grade in the course.");
		
		// Create Scanner to prompt inputs
		Scanner input= new Scanner(System.in);
		
		// Prompt inputs
		System.out.print("\nHomework weight?: ");
		final int hw_weight = input.nextInt();
		
		System.out.print("Exam 1 weight?: ");
		final int exam1_weight = input.nextInt();
		//Subtracting homework weight and exam 1 weight by 100 to get weight for exam 2
		final int exam2_weight = 100 - hw_weight - exam1_weight;
		
		System.out.println("Using weights of: " + "Homework: "+ hw_weight + "%, "+ "Exam 1: "+ exam1_weight + "%, " + "Exam 2: " + exam2_weight+"%:");
		
		
		// Homework Section
		System.out.println("\nHomework: ");
	
		System.out.print("Number of assignments?: ");
		int num_asm = input.nextInt();
		
		System.out.print("Average Homework grade? ");
		double hw_avg = input.nextDouble();
		
		System.out.print("Late days used?:");
		int late = input.nextInt();
		
		System.out.print("Labs attended?: ");
		int labs = input.nextInt();
		
		// Calculate
		int max_hw_score = num_asm * (10 + 4); 
		
		double hw_total = hwk(hw_avg, labs, late, num_asm); 
		
		System.out.printf("Total Points: %.2f / %d %n", hw_total, max_hw_score);
		
		double hw_weighted = hw_weight * hw_total / max_hw_score;
		
		System.out.printf("Weighted Score: %.2f %n", hw_weighted);
		
		
		// Exam 1 Section
		System.out.println("\nExam 1: ");
		
		System.out.print("Score? ");
		double exam1_score = input.nextDouble();
		
		System.out.print("Curve? ");
		final double curve1 = input.nextDouble();
		
		//This array returns the total points and weighted score for exam 1
		double[] score1 = exam1(exam1_score, curve1, exam1_weight);
		//Total points for exam 1
		System.out.printf("Total points:  %.2f / 100 %n", score1[0]);
		//Weighted score for exam 1
		System.out.printf("Weighted score: %.2f %n", score1[1]);
		
		
		// Exam 2 Section
		System.out.println("\nExam 2: ");
		System.out.print("Score? ");
		double exam2_score = input.nextDouble();
		
		System.out.print("Curve? ");
		final double curve2 = input.nextDouble();
		//Array for exam 2, returns total points and weighted score
		double[] score2 = exam2(exam2_score, curve2, exam2_weight); 
		//Exam 2 total points
		System.out.printf("Total points: %.2f / 100 %n", score2[0]);
		//Weighted score for exam 2
		System.out.printf("Weighted score: %.2f %n", score2[1]);
		
		//Uses the everything calculated to get the final course grade
		double course_grade = hw_weighted + score1[1] + score2[1];
		
		System.out.printf("%nOverall Course Grade: %.2f", course_grade);
	}
	
	// Function 1 (Calculates homework grade after late days used and lab attendance)
	private static double hwk(double hw_avg, int labs, int late, int num_asm) {
		
		double hw_total = num_asm * hw_avg + labs * 4;
		
		int max_hw_score = num_asm * (10 + 4); 


		
		if (labs <= 0) {
			labs = 0;
		} else if (labs > num_asm) {
			labs = num_asm;
		}
		
		if (hw_avg <= 0) {
			hw_avg= 0;
		} else if (hw_avg > 10) {
			hw_avg = 10;
		}
		//If late days exceed the number of assignments
		if (late > num_asm / 2) {
			hw_total *= 0.9;  
		} else if (late  == 0) {
			hw_total += 5;
			if (hw_total > max_hw_score) {
				hw_total = max_hw_score;
			}
		}
				
		return hw_total;
	}
	
	// Function 2 (Calculates scores after curve)
	private static double[] exam1(double exam1_score, double curve1, int exam1_weight) {
		
		double exam1_curved = exam1_score + curve1;
		//Makes sure that user doesn't input negative numbers
		if (exam1_score <= 0) {
			exam1_score = 0;
			//Makes sure that scores don't exceed 100
		} else if (exam1_score > 100) {
			exam1_score = 100;
		}
		//Makes sure that curved points don't make score exceed 100
		if (exam1_curved > 100) {
			exam1_curved = 100;
		} else if (exam1_curved <= 0) {
			exam1_curved = 0;
		}
		
		double exam1_weighted = exam1_weight * exam1_curved / 100;
		
		double score1[] = new double[]{exam1_curved, exam1_weighted};
		
		return score1;
	}
	
	// Function 3
	private static double[] exam2(double exam2_score, double curve2, int exam2_weight) {
		
		double exam2_curved = exam2_score + curve2;
		//Insures that no negatives are typed in, and if so input is capped at 0
		if (exam2_score <= 0) {
			exam2_score = 0; 
			//Insures the max score for exam 2 is 100 and doesn't exceed 100
		} else if (exam2_score > 100) {
			exam2_score = 100;
		}
		//Makes sure that curve points don't exceed 100
		if (exam2_curved > 100) {
			exam2_curved = 100;
		} else if (exam2_curved <= 0) {
			exam2_curved = 0;
		}
		
		double exam2_weighted = exam2_weight * exam2_curved / 100;
		
		double score2[] = new double[]{exam2_curved, exam2_weighted};
		
		return score2;
	}
}
