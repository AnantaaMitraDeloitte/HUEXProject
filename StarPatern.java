package HashedinTraining;

public class StarPatern
{
	static void squarePrint(int n)
    {
		 int rows=n;
				 int j,i = 1;
				    while(i <= rows)
				    {
				        j = 1;
				        while(j <= rows)
				        {
				            if(i == 1 || i == rows || i == j || j == 1 || j == rows || j == rows - i + 1)
				            {
				                
								System.out.print("* ");
				            }
				            else
				            {
				            	System.out.print("  ");
				            } 
				            j++;        
				        }
				        System.out.print("\n");  
				        i++; 
				    }
    }
	public static void main(String args[]) 
	  {
		 int n = 9;
		 squarePrint(n);
	     
	  }
}



