import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Trader {

	public static void tradewriter( Map<Integer,Currency> CurrMap) throws IOException {
	
	//
		//Connection loginForm = Jsoup.connect("http://currency.poe.trade/shop?league=Bestiary");
		//loginForm.method(Connection.Method.GET);
		
		String url 	= "http://currency.poe.trade/shop?league=Bestiary";
	//	Document doc = (Document) Jsoup.connect(url);
		
		Connection doc = Jsoup.connect("http://currency.poe.trade/shop?league=Bestiary")
				.data("league","Bestiary")
				.data("apikey","zidutaseteyaho");
		/*		.data("sell_currency",sellcurr)
				.data("sell_value",sellamt)
				.data("buy_currency",buycurr)
				.data("buy_value",buyamt);
				//.post();
		
		doc.data("sell_currency","Chaos Orb");
		doc.data("sell_value","2");
		doc.data("buy_currency","Exalted Orb");
		doc.data("buy_value","1");
	*/
		String test = "Jsoup.connect(http://currency.poe.trade/shop?league=Bestiary)";
		int count = 0;	
		
			for (int i=0; i<TradeCalc.currsell.size();i++) 
			{	
				doc.data("sell_currency",CurrMap.get(TradeCalc.currsell.get(i)).getName());	
			//	test =  test + ".data(sell_currency , " + CurrMap.get(TradeCalc.currsell.get(i)).getName() + ")";
			//	System.out.println(".data(sell_currency , " + CurrMap.get(TradeCalc.currsell.get(i)).getName() + ")");

			}
			for (int i=0; i<TradeCalc.currsell.size();i++) 
			{
				doc.data("sell_value",String.valueOf(TradeCalc.sellamt.get(i)));				
			//	test =  test + ".data(sell_value , " + String.valueOf(Math.round(TradeCalc.sellamt.get(i))) + ")";
			//	System.out.println(".data(sell_value , " + String.valueOf(Math.round(TradeCalc.sellamt.get(i))) + ")");

			}
			for (int i=0; i<TradeCalc.currsell.size();i++) 
			{
				doc.data("buy_currency",CurrMap.get(TradeCalc.currbuy.get(i)).getName());
			//	test =  test + ".data(buy_currency , " + CurrMap.get(TradeCalc.currbuy.get(i)).getName() + ")";
			//	System.out.println(".data(buy_currency , " + CurrMap.get(TradeCalc.currbuy.get(i)).getName() + ")");

			}	
			for (int i=0; i<TradeCalc.currsell.size();i++) 
			{
				doc.data("buy_value",String.valueOf(TradeCalc.buyamt.get(i)));
			//	test =  test + ".data(buy_value , " + String.valueOf(Math.round(TradeCalc.buyamt.get(i))) + ")";
			//	System.out.println(".data(buy_value , " + String.valueOf(Math.round(TradeCalc.buyamt.get(i))) + ")");

				count++;
			}	
				
				
			
			
			System.out.println("");
			System.out.println("# of Trades Posted: " + count + " at " + new Date());
			//System.out.println(test);
			//System.out.println(String.valueOf(String.valueOf(doc)));
			Document post = doc.post();
		
	}
}
