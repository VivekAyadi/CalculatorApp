import java.util.Scanner;

class calculatorApp
{
    public static void main(String []args)
    {
        double sum,sub,mul,div;

        Scanner sc=new Scanner(System.in);
        System.out.println("enter first number!");
        int num1=sc.nextInt();

        System.out.println("enter second number!");
        int num2=sc.nextInt();

        System.out.println("enter your choice:(+,-,*,/)");
        char ch=sc.next().charAt(0);
        if(ch=='+')
        {
           sum=num1+num2;
           System.out.println("Addition of two numbers=" + sum);
        }
        else if(ch=='-'){
           sub=num1-num2;
           System.out.println("subtraction of two numbers=" + sub);
        }
        else if(ch=='*'){
            mul=num1*num2;
            System.out.println("multiplication of two numbers=" + mul);
        }
        else if(ch=='/'){
        if(num2!=0){
        div=num1/(double)num2;
        System.out.println("division of two numbers=" + div);
       }else{
        System.out.println("cannot divide by 0");
       }
        }
        else{
            System.out.println("Invalid operator!!!");
        }
    }
}
/*
import java.util.Scanner;

class BasicCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = sc.nextDouble();

        System.out.println("Choose an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.print("Enter the number corresponding to the operation: ");
        int operation = sc.nextInt();

        double result;
        // Performing the selected operation
        switch (operation) {
            case 1:
                result = num1 + num2;
                System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                break;
            case 2:
                result = num1 - num2;
                System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                break;
            case 3:
                result = num1 * num2;
                System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                }
                break;
            default:
                System.out.println("Invalid operation selected.");
                break;
        }
        sc.close();
    }
}
*/

