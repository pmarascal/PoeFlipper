import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TradeCalc {
	
	private static final String Comma_Delimiter = ",";

	//	CSV Incoming File Header = "SellCurr, SellAmt, BuyCurr, BuyAmt, Rate";
	private static final int i_IDX	  		= 0;
	private static final int j_IDX			= 1;
	private static final int SellCurr_IDX 	= 2;
	private static final int SellAmt_IDX 	= 3;
	private static final int BuyCurr_IDX 	= 4;
	private static final int BuyAmt_IDX 	= 5;
	private static final int Rate_IDX 		= 6;
	
	public static List <Integer> currsell = new LinkedList<>();
	public static List <Double> sellamt = new LinkedList<>();
	public static List <Integer> currbuy = new LinkedList<>();
	public static List <Double> buyamt = new LinkedList<>();
	public static List <Double> profit = new LinkedList<>();
	

	public static void readdata(String filename, Map<Integer,Currency> CurrMap, int[] list) {
		
		BufferedReader fileReader = null;
		
		try{
		
			List currency = new ArrayList();
			String line = "";
			
			
			fileReader = new BufferedReader(new FileReader(filename));
			
			//Read CSV Header to skip it
			fileReader.readLine();
			
			
			
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(Comma_Delimiter);
				if (tokens.length>0) {
					
					//Get Sell Value in Chaos
					int currentsell = Integer.parseInt(tokens[SellCurr_IDX]);
					double currentsellamt = Double.parseDouble(tokens[SellAmt_IDX]);
					double sellch = currentsellamt*(CurrMap.get(currentsell).getchvalue());
					
					//Get Buy Value in Chaos
					int currentbuy = Integer.parseInt(tokens[BuyCurr_IDX]);
					double currentbuyamt = Double.parseDouble(tokens[BuyAmt_IDX]);
					double buych = currentbuyamt*(CurrMap.get(currentbuy).getchvalue());
					
					double profits = buych - sellch;
					
					
					//Finding # of trades to reach desired profit
					//TODO Change the min_profit to be a public variable defined in main class
					double mintrades = (5 /profits); //could use Math.ceil
					
					if (profits>=0){
						/* DEBUG
						System.out.println("=====GOOD TRADE: Sell + " + Double.parseDouble(tokens[SellAmt_IDX]) + " of " 
								+ Integer.parseInt(tokens[SellCurr_IDX])+ " at " + CurrMap.get(currentsell).getchvalue() + 
								"ch For Buying " + Double.parseDouble(tokens[BuyAmt_IDX]) + " of " 
								+ Integer.parseInt(tokens[BuyCurr_IDX])+ " at " + CurrMap.get(currentbuy).getchvalue() + 
								"ch; ..... Profit="+profit);
								*/
						
						if (currentsell ==6 && profits < 10)
						{
							//Do nothing; Don't trade exalts unless large profit
						}
						else if (profits>2.5) 
						{
							if ((CurrMap.get(currentsell).getStock()/2) > currentsellamt) 
							{
								currsell.add(currentsell);
								sellamt.add((double) Math.round(currentsellamt));
								currbuy.add(currentbuy);
								buyamt.add((double) Math.round(currentbuyamt));
								profit.add(profits);
									
								/*	//debug for duplicate trades
									if ((CurrMap.get(currentsell).getId() == 2) && (CurrMap.get(currentbuy).getId() == 10)) {
										System.out.println("i= " + Integer.parseInt(tokens[i_IDX]) + ",j= " + Integer.parseInt(tokens[j_IDX])
													 + " --- sell 2 and get 10 in FIRST LOOP");
									}
								*/
							}
						}
						else if (mintrades <=5 && profits <=2.5)
						{
							//Now Making sure each currency involved is in stock
							if ((CurrMap.get(currentsell).getStock()/2) > (Math.round(currentsellamt*mintrades)))
									{
									profits = (buych*mintrades)-(sellch*mintrades);
									
								currsell.add(currentsell);
								sellamt.add((double) Math.round(currentsellamt*mintrades));
								currbuy.add(currentbuy);
								buyamt.add((double) Math.round(currentbuyamt*mintrades));
								profit.add(profits);
									
								/*	//debug for duplicate trades
									if ((CurrMap.get(currentsell).getId() == 2) && (CurrMap.get(currentbuy).getId() == 10)) {
										System.out.println("i= " + Integer.parseInt(tokens[i_IDX]) + ",j= " + Integer.parseInt(tokens[j_IDX])
												+ " --- sell 2 and get 10 in SECOND LOOP");
									}
								*/
							}
						}
					}
					
				}
				
			}
			
			
			
			//fileWriter = new FileWriter("C:\\Users\\Pete\\eclipse-workspace\\PoeScraper\\src\\data.csv\
			} catch (Exception e) {
				System.out.println("Error in CSVFileReader!");
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
				} catch (IOException e) {
					System.out.println("Error while closing fileReader!");
					e.printStackTrace();
				}
			}
		}
			

	
	}

