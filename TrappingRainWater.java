package HashedinTraining;

public class TrappingRainWater {
	public static int maxWtr(int[] arr, int n)
	{
	    int totWater = 0;
	    for(int i = 1; i < n - 1; i++)
	    {
	    	int left = arr[i];
	        for(int j = 0; j < i; j++)
	        {
	            left = Math.max(left, arr[j]);
	        }
	        int right = arr[i];
	        for(int j = i + 1; j < n; j++)
	        {
	            right = Math.max(right, arr[j]);
	        }
	        totWater += Math.min(left, right) - arr[i];
	    }
	    return totWater;
	}
	
	public static void main(String[] args)
	{
	    int[] arr = { 7,4,0,9 };
	    int n = arr.length;
	 
	    System.out.print(maxWtr(arr,n));
	}
}
