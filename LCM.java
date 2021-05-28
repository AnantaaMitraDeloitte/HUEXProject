package HashedinTraining;

public class LCM {
	public static void main(String[] args) {

	    int num1 = 30, num2 = 45, gcd = 1;

	    for(int i = 1; i <= num1 && i <= num2; ++i) {
	      if(num1 % i == 0 && num2 % i == 0)
	        gcd = i;
	    }

	    int lcm = (num1 * num2) / gcd;
	    System.out.printf("The LCM of %d and %d is %d.", num1, num2, lcm);
	  }
}
