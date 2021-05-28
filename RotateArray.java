package HashedinTraining;

public class RotateArray {
	 public static void main(String[] args) {    
	        //Initialize array     
	        int [] arr = new int [] {1, 2, 3, 4, 5};  
	        int n = 3; 
	        int rotation=arr.length-n;
	        for(int i = 0; i < rotation; i++){    
	            int j, last; 
	            last = arr[arr.length-1];
	            for(j = arr.length-1; j > 0; j--){     
	                arr[j] = arr[j-1];    
	            }     
	            arr[0] = last;    
	        }   
	        System.out.println("Array after rotation: ");    
	        for(int i = 0; i< arr.length; i++){    
	            System.out.print(arr[i] + " ");    
	        }    
	    }  
}
