import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String []args){
        double total=0,marks;
        Scanner obj=new Scanner(System.in);
        System.out.print("enter the number of subject!:");
        int num=obj.nextInt();

        for(int i=1;i<= num;i++){
            System.out.println("enter marks of "+ i+ "subject");
            marks=obj.nextInt();
            total= total + marks;
        }
        System.out.println("total marks ="+total);

        System.out.println("average marks is="+ total/num);
        double averageGrade =total/num;

        String letterGrade =getLetterGrade(averageGrade);
        double gpa = calculateGPA(averageGrade);

        System.out.println("Letter Grade: " + letterGrade);
        System.out.println("GPA: " + gpa);

        obj.close(); 
    }
    public static String getLetterGrade(double averageGrade) {
        if (averageGrade >= 90) {
            return "A";
        } else if (averageGrade >= 80) {
            return "B";
        } else if (averageGrade >= 70) {
            return "C";
        } else if (averageGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    public static double calculateGPA(double averageGrade) {
        if (averageGrade >= 90) {
            return 4.0;
        } else if (averageGrade >= 80) {
            return 3.0;
        } else if (averageGrade >= 70) {
            return 2.0;
        } else if (averageGrade >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
