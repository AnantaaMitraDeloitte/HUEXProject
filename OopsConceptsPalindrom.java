package HashedinTraining;

public class OopsConceptsPalindrom {
	public static void main(String[] args)
	{
		String input="121";
		int len=input.length();
		String temp1="";
		int i=0;
		for(i=len-1;i>=0;i--) {
			temp1=temp1+input.charAt(i);
		}
		if(input.equals(temp1)) {
			System.out.println("Entered string is a palindrome. Swap required : "+len);
		}
		
	}
}
