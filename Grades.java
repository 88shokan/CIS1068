//Billy Phan
//CIS1068
//Grade Calculator
package grades;
import java.util.Scanner;
public class Grades {

	public static void main(String[] args) {
		
		System.out.println("The following program accepts your homework and two exam scores\nas input and computes your grade in the course.");
		
		// Create Scanner to prompt inputs
		Scanner input= new Scanner(System.in);
		
		// Prompt inputs
		System.out.print("\nHomework weight? ");
		final int hw_weight = input.nextInt();
		
		System.out.print("Exam 1 weight? ");
		final int exam1_weight = input.nextInt();
		
		final int exam2_weight = 100 - hw_weight - exam1_weight;
		
		System.out.println("Using weights of: " + hw_weight + " "+ exam1_weight + " " + exam2_weight);
		
		
		// Homework
		System.out.println("\nHomework: ");
	
		System.out.print("Number of assignments? ");
		int num_asm = input.nextInt();
		
		System.out.print("Average Homework grade? ");
		double avg_hw_grade = input.nextDouble();
		
		System.out.print("Number of late days used? ");
		int late = input.nextInt();
		
		System.out.print("Labs attended? ");
		int labs = input.nextInt();
		
		// Calculate
		int max_hw_score = num_asm * (10 + 4); 
		
		double hw_total = homework(avg_hw_grade, labs, late, num_asm); 
		
		System.out.printf("Total points = %.2f / %d %n", hw_total, max_hw_score);
		
		double hw_weighted = hw_weight * hw_total / max_hw_score;
		
		System.out.printf("Weighted score = %.2f %n", hw_weighted);
		
		
		// Exam 1
		System.out.println("\nExam 1: ");
		
		System.out.print("Score? ");
		double exam1_score = input.nextDouble();
		
		System.out.print("Curve? ");
		final double curve1 = input.nextDouble();
		

		double[] score1 = exam1(exam1_score, curve1, exam1_weight);
		
		System.out.printf("Total points = %.2f / 100 %n", score1[0]);
		
		System.out.printf("Weighted score = %.2f %n", score1[1]);
		
		
		// Exam 2
		System.out.println("\nExam 2: ");
		System.out.print("Score? ");
		double exam2_score = input.nextDouble();
		
		System.out.print("Curve? ");
		final double curve2 = input.nextDouble();
		
		double[] score2 = exam2(exam2_score, curve2, exam2_weight); 
		
		System.out.printf("Total points = %.2f / 100 %n", score2[0]);
		
		System.out.printf("Weighted score = %.2f %n", score2[1]);
		
		// Final average point
		double course_grade = hw_weighted + score1[1] + score2[1];
		
		System.out.printf("%nCourse grade = %.2f", course_grade);
	}
	
	// Function 1
	private static double homework(double avg_hw_grade, int labs_attended, int late_days, int number_of_asm) {
		
		double hw_total = number_of_asm * avg_hw_grade + labs_attended * 4;
		
		int max_hw_score = number_of_asm * (10 + 4); 

		if (avg_hw_grade <= 0) {
			avg_hw_grade = 0;
		} else if (avg_hw_grade > 10) {
			avg_hw_grade = 10;
		}
		
		if (labs_attended <= 0) {
			labs_attended = 0;
		} else if (labs_attended > number_of_asm) {
			labs_attended = number_of_asm;
		}
		
		if (late_days > number_of_asm / 2) {
			hw_total *= 0.9;  // Reduce by 10% of total hw_total
		} else if (late_days == 0) {
			hw_total += 5;
			if (hw_total > max_hw_score) {
				hw_total = max_hw_score;
			}
		}
				
		return hw_total;
	}
	
	// Function 2
	private static double[] exam1(double exam1_score, double curve1, int exam1_weight) {
		
		double exam1_curved = exam1_score + curve1;
		
		if (exam1_score <= 0) {
			exam1_score = 0;
		} else if (exam1_score > 100) {
			exam1_score = 100;
		}
		
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
		
		if (exam2_score <= 0) {
			exam2_score = 0; 
		} else if (exam2_score > 100) {
			exam2_score = 100;
		}

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
