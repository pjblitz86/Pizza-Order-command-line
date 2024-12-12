import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Asortimentas {

	private List<Preke> prekes = new ArrayList<>();
	
	// SINGLETON PATTERN - vienas ir nepakartojamas
	private static Asortimentas instance;
	
	private Asortimentas() {
		seedDummyData();
	}
	
	public static Asortimentas getInstance() {
		if(instance == null)
			instance = new Asortimentas();
		
		return instance;
	}
	
	public void seedDummyData() {
		Long nextIndex = 1L;
		prekes.add(new Preke(nextIndex++, "Margarita", "Mocarela ir pomidorai", 5.4d ));
		prekes.add(new Preke(nextIndex++, "Studentu", "Desra ir agurkai", 4.2d ));
		prekes.add(new Preke(nextIndex++, "Astrioji", "Astrus padazas", 3.4d ));
		prekes.add(new Preke(nextIndex++, "Havaju", "Persikai", 6.4d ));
		prekes.add(new Preke(nextIndex++, "Cezario", "su salotom", 2.4d ));
		prekes.add(new Preke(nextIndex++, "Svieziu darzoviu", "Astrus padazas", 2.5d ));
		prekes.add(new Preke(nextIndex++, "Graikiskos", "Astrus padazas", 4.7d ));
		prekes.add(new Preke(nextIndex++, "Coca-cola", "Astrus padazas", 1.5d ));
		prekes.add(new Preke(nextIndex++, "Koldunai", "Kepti koldunai", 2.4d ));
	}
	
	public List<Preke> getAll() {
		return prekes;
	}
	
	public Optional<Preke> getById(Long productId) {
		return prekes.stream()
				.filter(item -> item.id() == productId )
				.findFirst();	}
	
}
