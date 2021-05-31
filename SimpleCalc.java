package HashedinTraining;


	import java.util.Scanner;

	public class SimpleCalc {

	    public static void run() {
	        Scanner userInput = new Scanner(System.in);
	        System.out.print("Enter operation to perform (+,*,/,-,sqrt):");
	        String operation = userInput.next();
	        int op1=0,op2 = 0;
	        if(operation.equals("sqrt")){
	        System.out.println("Enter first number: ");
	        op1=askForNumber();
	        }
	        else{
	        	System.out.println("Enter first number: ");
		        op1 = askForNumber();
	       
		        System.out.println("Enter second number: ");
		        op2 = askForNumber();
	        }

	        if (operation.equals("+")) {
	            add(op1, op2);
	        } else if (operation.equals("x")){
	           mult(op1, op2);
	        } else if (operation.equals("-")){
	            sub(op1, op2);
	        } else if (operation.equals("/")){
	            divide(op1, op2);
	        }else if (operation.equals("sqrt")){
	            squareRt(op1);
	        } else {
	            System.out.println("The operation is not valid.");
	        }
	    }
	    
	    public static int askForNumber() {
	        try {
	            Scanner s = new Scanner(System.in);
	            String userInput = s.nextLine();
	            return Integer.parseInt(userInput);
	        } catch (NumberFormatException e) {
	            System.out.println("Please input a valid number!");
	            return askForNumber();
	        }
	    }

	    private static void add(int op1, int op2) {
	    	System.out.println("The result is : "+(op1 + op2));
	    }

	    private static void mult(int op1, int op2) {
	    	System.out.println("The result is : "+(op1 * op2));
	    }

	    private static void sub(int op1, int op2) {
	        
	        int result = 1;
	        result = op1 - op2;
	        System.out.println("The result is : "+(op1 - op2));
	    }
	    private static void divide(int op1, int op2) {
	    	double result = 1;
	    	try{
	            result = op1/op2;
	            System.out.println("The result is" +result);
	        } 
	        catch (ArithmeticException e) {
	           System.out.println ("Can't be divided by Zero " + e);
	        }
	    }
	    private static void squareRt(int op1) {
	    	double result = 1;
	    	result=Math.sqrt(op1);        
	    	System.out.println("The result is" +result);
	    }

	    public static void main(String[] args) {
	        SimpleCalc.run();
	    }
	}
