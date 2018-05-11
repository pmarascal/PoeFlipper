import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class test {

	public static void main(String[] args) throws IOException {
        // declaring and initializing 2D array
		
   /*     int arr[][] = { {2,7,9},{3,6,1},{7,4,2},{3,6,1},{7,4,2},{3,6,1},{7,4,2} };
 
        // printing 2D array
        for (int i=0; i< 7 ; i++)
        {
            for (int j=0; j < 3 ; j++)
                System.out.print(arr[i][j] + " ");
 
            System.out.println();
        }
        */
		int[] inputs = {1,2,3};
		int currentFocus = 0;
		   

	   int[] inputs2 = {1,2,3,4,5,6,7,8,10,11,13,16,35};	
	   
	   //permute.permute(java.util.Arrays.asList(3,4,6,2,1), 3);
	   System.out.println();
	   

	   
	 
	   
	   List <Integer> currsell = new LinkedList<>();
	   currsell.add(1);
	   currsell.add(2);
	   
	   List <Float> curramt = new LinkedList<>();
	   curramt.add((float) 111);
	   curramt.add((float) 222);
	   
	   for (int i=0; i<currsell.size();i++) {
		   System.out.println(currsell.get(i) + " ---- " + curramt.get(i));
	   }
	   
	   
	   Timer timer = new Timer();
	 //  timer.schedule(new CurrencyFlipper.main(args),300*1000);
	   
	 //  CurrencyFlipper.main(args);

	}
}
