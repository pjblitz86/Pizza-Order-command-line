
public class BL {

	static final Double MARGIN = 100d;
	public static double getSalePrice(Double price) {
		return price + price * MARGIN / 100;
	}
	
}
