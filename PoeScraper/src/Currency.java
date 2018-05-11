import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



public class Currency {

	private int id;
	private String name;
	private float chvalue;
	private int stock;
	
	
	public Currency(int id, String name, float chvalue, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.chvalue = chvalue;
		this.stock = stock;
	}
	
	// Return functions
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getchvalue() {
		return this.chvalue;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	//Set functions
	public void setId(int id) {
		this.id = id;
	}
	
	//Auto Set Currency Name Based on ID
	public void setName(int id) {
		
		if (id == 1) {
			this.name = "Orb of Alteration";
		}else if (id == 2) {
			this.name = "Orb of Fusing";
		}else if (id == 3) {
			this.name = "Orb of Alchemy";
		}else if (id == 4) {
			this.name = "Chaos Orb";
		}else if (id == 5) {
			this.name = "Gemcutter's Prism";
		}else if (id == 6) {
			this.name = "Exalted Orb";
		}else if (id == 7) {
			this.name = "Chromatic Orb";
		}else if (id == 8) {
			this.name = "Jeweller's Orb";
		}else if (id == 10) {
			this.name = "Cartographer's Chisel";
		}else if (id == 11) {
			this.name = "Orb of Scouring";
		}else if (id == 13) {
			this.name = "Orb of Regret";
		}else if (id == 16) {
			this.name = "Vaal Orb";
		}else if (id == 21) {
			this.name = "Glassblower's Bauble";
		}else if (id == 35) {
			this.name = "Silver Coin";
		}else if (id == 45) {
			this.name = "Apprentice Cartographer's Sextant";
		}else if (id == 46) {
			this.name = "Journeyman Cartographer's Sextant";
		}else {
			this.name = "null";
		}
	}
	
	public void setChValue(int id) throws IOException {
		
		String url 	= "http://currency.poe.trade/search?league=Bestiary&online=x&want=4&have=" + id;
		
		Document doc = Jsoup.connect(url).get();
		
		if (id==4)this.chvalue = 1; //Hardcode Chaos = Chaos Because Weird Listings
		else {
		String sellval  = doc.select("div.displayoffer").get(3).attr("data-sellvalue"); 
		String buyval 	= doc.select("div.displayoffer").get(3).attr("data-buyvalue");
		Float slope	= Float.valueOf(sellval)/Float.valueOf(buyval);
		
		this.chvalue = slope;
		}
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	//A Return for Testing
	//@Override
	public String toString() {
		return "Currency [id=" + id + " , name=" + name + " , ChaosValue=" + chvalue + "]";
	}
	
	public static float chCalc(int id) {
		
		float chValue = 10;
		
		
		
		return chValue;
		
	}
	
}



// {'id','name','chaos value'}