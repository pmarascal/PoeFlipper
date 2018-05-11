import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/*
 * writerawdata method scrapes currency.poe.trade and grabs the 4th 'best' offers
 * and then writes all trade pairs (with more than 5 offers) to an output .csv file
 */

public class Writer{
	
	private static final String Comma_Delimiter = ",";
	private static final String New_Line = "\n";
	private static final String File_Header = "index_i,index_j,SellCurr, SellAmt, BuyCurr, BuyAmt, Rate";
	
	public static void writerawdata(int[] list) {
		
		FileWriter fileWriter = null;
		
		try{
		
			fileWriter = new FileWriter("C:\\Users\\Pete\\eclipse-workspace\\PoeScraper\\src\\data.csv\\");
			
			//Writing CSV Header and new line after the header
			fileWriter.append(File_Header.toString());
			fileWriter.append(New_Line);
			
			for (int i=0; i<list.length-1;i++){
				for (int j=i+1; j<list.length;j++) {

					//Find Transactions From j to i
					String url 	= "http://currency.poe.trade/search?league=Bestiary&online=x&want="+list[i]+"&have="+list[j];
					Document doc = Jsoup.connect(url).get();
					
					//Find Number of Offers to limit less popular trades
					Elements ntmDivs = doc.getElementsByClass("displayoffer");
					int transAmount = ntmDivs.size();
					
					if (transAmount >= 5) {
						String index_i 	= String.valueOf(i);
						String index_j  = String.valueOf(j);
						String sellcurr	= doc.select("div.displayoffer").get(1).attr("data-sellcurrency");
						String sellval  = doc.select("div.displayoffer").get(1).attr("data-sellvalue"); 
						String buycurr	= doc.select("div.displayoffer").get(1).attr("data-buycurrency");
						String buyval 	= doc.select("div.displayoffer").get(1).attr("data-buyvalue");
						Float slope	= Float.valueOf(sellval)/Float.valueOf(buyval);
						
						fileWriter.append(index_i + "," + index_j + "," + sellcurr + "," + sellval + "," 
										+ buycurr + "," + buyval + "," + slope );
						fileWriter.append(New_Line);
					}
					
					//Find Transactions from i to j
					url 	= "http://currency.poe.trade/search?league=Bestiary&online=x&want="+list[j]+"&have="+list[i];
					doc = Jsoup.connect(url).get();
					
					//Find Number of Offers to limit less popular trades
					ntmDivs = doc.getElementsByClass("displayoffer");
					transAmount = ntmDivs.size();
					
					if (transAmount >= 5) {	
						String index_i 	= String.valueOf(i);
						String index_j  = String.valueOf(j);
						String sellcurr	= doc.select("div.displayoffer").get(1).attr("data-sellcurrency");
						String sellval  = doc.select("div.displayoffer").get(1).attr("data-sellvalue"); 
						String buycurr	= doc.select("div.displayoffer").get(1).attr("data-buycurrency");
						String buyval 	= doc.select("div.displayoffer").get(1).attr("data-buyvalue");
						Float slope	= Float.valueOf(sellval)/Float.valueOf(buyval);
					
						fileWriter.append(index_i + "," + index_j + "," + sellcurr + "," + sellval + "," 
												+ buycurr + "," + buyval + "," + slope );
						fileWriter.append(New_Line);
					}	
				}
				System.out.print("..");
			}
			System.out.println("Completed!");
			System.out.println("CSV File (/data.csv) was created successfully!");
		}
		catch (Exception e) {
			System.out.println("Error in CSV FileWriter");
			e.printStackTrace();
		}
		finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter!");
			}
		}
	}
	
}
