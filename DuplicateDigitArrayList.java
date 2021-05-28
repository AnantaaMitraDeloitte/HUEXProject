package HashedinTraining;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class DuplicateDigitArrayList {
	 public static void main(String[] args) { 
		 
		 
		 HashMap<Integer, Integer> charCountMap = new HashMap<Integer, Integer>();
		 List<Integer> li1=new ArrayList<Integer>();
		 List<Integer> li2=new ArrayList<Integer>();
		 int[][] arr= {{1,1,3,2},{9,8,8,1},{0,4,5,0,0,1,4}};
		 
		 ArrayList<Integer>[] al=new ArrayList[arr.length];
		 for (int i=0;i<arr.length;i++){
			 al[i] = new ArrayList<Integer>();
			 for (int j=0;j<arr[i].length;j++){
					al[i].add(arr[i][j]);						 
			 }
		 }
		 
		 for (int i=0;i<arr.length;i++){
			 for (int j = 0; j < al[i].size(); j++) {
				 int c=al[i].get(j);
				 if(charCountMap.containsKey(c))
				 	{
						 charCountMap.put(c, charCountMap.get(c)+1);
					 }
					 else
					 {
						 charCountMap.put(c, 1);
					 }
		 			}
			 Set<Entry<Integer, Integer>> entryset=charCountMap.entrySet();
			 for (Entry<Integer, Integer> entry: entryset )
			        {
			        	if (entry.getValue()>1) {
			        		li2.add(entry.getValue());
			        		li1.add(entry.getKey());
			        		
			        	}
					}
			 charCountMap.clear();
			 }
		 
		 for (int i = 0; i < li1.size(); i++) {
			 System.out.println(li1.get(i)+" = "+li2.get(i));
		 }
			 
	 }
}
