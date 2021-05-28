package HashedinTraining;

import java.util.HashMap;
import java.util.Set;

public class DigitCount {
	public static void main(String[] args)
	{
		int inputNumber = 1211136543;
		HashMap<Integer, Integer> digitCountMap = new HashMap<Integer, Integer>();
         
        while (inputNumber != 0)
        {  
            int lastDigit = inputNumber % 10;
             
            if(digitCountMap.containsKey(lastDigit))
            {
            	digitCountMap.put(lastDigit, digitCountMap.get(lastDigit)+1);
            }
            else
            {
               digitCountMap.put(lastDigit, 1);
            }
             
            inputNumber = inputNumber / 10;
        }
                
        System.out.println("Digits : Frequency");
         
        Set<Integer> keys = digitCountMap.keySet();
         
        for (Integer key : keys) 
        {
            System.out.println("   "+key+"   :   "+digitCountMap.get(key));
        }
	}
}
