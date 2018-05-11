import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* ================= TODO LIST ======================
 * 	- Make values and lists all public in top of main class for easier config
 * 	- Automate the current stock of my own currency
 * 	- Use currency.poe.trade to manage my shop and post good trades
 *  - Take avg of each pair both ways
 *  - Currently taking 2nd 'best' trade, should change to an avg of top 1-3 or something 
 *
 *	- Keywords - DEBUG, TODO, TEST REMOVE
 *
 *	--- To add new currency:  - Add to Currency.setName()
 *							  - CurrencyFlipper.Main (create curr variable, add to CurrMap, add to list)
 */

public class CurrencyFlipper {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Creating Each Type of Currency
		//FUTURE: Try and make this configurable through an input file
		Currency alt 	= new Currency(1,"test",0,840);
		Currency fuse 	= new Currency(2,"test",0,300);
		Currency alch 	= new Currency(3,"test",0,290);
		Currency chaos 	= new Currency(4,"test",0,40);
		Currency gcp 	= new Currency(5,"test",0,60);
		Currency ex 	= new Currency(6,"test",0,8);
		Currency chro 	= new Currency(7,"test",0,460);
		Currency jew 	= new Currency(8,"test",0,460);
		Currency chisel = new Currency(10,"test",0,240);
		Currency scour 	= new Currency(11,"test",0,70);
		Currency regret = new Currency(13,"test",0,60);
		Currency vaal 	= new Currency(16,"test",0,65);
		Currency silver = new Currency(35,"test",0,110);
		Currency asex	= new Currency(45,"test",0,35);
		Currency jsex	= new Currency(46,"test",0,14);
		
		//Adding Each Currency Type to an ArrayList For Iterations
		List <Currency>currencies = new ArrayList();
		currencies.add(alt);
		currencies.add(fuse);
		currencies.add(alch);
		currencies.add(chaos);
		currencies.add(gcp);
		currencies.add(ex);
		currencies.add(chro);
		currencies.add(jew);
		currencies.add(chisel);
		currencies.add(scour);
		currencies.add(regret);
		currencies.add(vaal);
		currencies.add(silver);
		currencies.add(asex);
		currencies.add(jsex);
		
		int[] list = {1,2,3,4,5,6,7,8,10,11,13,16,35,45,46}; //If List Changes, this must too
		int i = 0;
		
		Map<Integer, Currency> CurrMap =  new HashMap<>();
		   CurrMap.put(1,alt);
		   CurrMap.put(2,fuse);
		   CurrMap.put(3,alch);
		   CurrMap.put(4,chaos);
		   CurrMap.put(5,gcp);
		   CurrMap.put(6,ex);
		   CurrMap.put(7,chro);
		   CurrMap.put(8,jew);
		   CurrMap.put(10,chisel);
		   CurrMap.put(11,scour);
		   CurrMap.put(13,regret);
		   CurrMap.put(16,vaal);
		   CurrMap.put(35,silver);
		   CurrMap.put(45,asex);
		   CurrMap.put(46,jsex);
		
		System.out.print("Beginning to get chaos value for all currency..");
		//Filling Out Chaos Value For Currency in List
		for (Currency str : currencies) {
			str.setName(list[i]);
			str.setChValue(list[i]);
			i++;
			System.out.print("..");
			//DEBUG//System.out.println(str.toString());
			
		}
		System.out.println("Completed!");
		
		//MarketData Class Finds Pairs and Collects All Transaction Data
		System.out.print("Beginning to write data file");
		Writer.writerawdata(list);

		//Read Data method finds good trades and stores into lists
		TradeCalc.readdata("C:\\Users\\Pete\\eclipse-workspace\\PoeScraper\\src\\data.csv\\",CurrMap,list);
		
		//List Output of readdata method
	/*	for (i=0; i<TradeCalc.currsell.size();i++) {
			System.out.print(TradeCalc.currsell.get(i)+"(");
			System.out.print(CurrMap.get(TradeCalc.currsell.get(i)).getName()+")x");
			System.out.print(TradeCalc.sellamt.get(i)+" === ");
			System.out.print(TradeCalc.currbuy.get(i)+"(");
			System.out.print(CurrMap.get(TradeCalc.currbuy.get(i)).getName()+")x");
			System.out.print(TradeCalc.buyamt.get(i)+" --- profit= ");
			System.out.println(TradeCalc.profit.get(i));
		   }
	*/
		Trader.tradewriter(CurrMap);
		System.out.println("Posting Trades Completed!");
		
		
		
		
	}
	
	
}
