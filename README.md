# CalculatorApp

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

