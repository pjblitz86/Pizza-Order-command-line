import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UI {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void printMenu() {
		List<Preke> prekes = Asortimentas.getInstance().getAll();
		prekes.forEach(item -> System.out.println(
				String.format("%d - %-20s kaina: %.2f",
						item.id(),
						item.name(),
						BL.getSalePrice(item.price())
						))
				);
	}
	
	public static Order createNewOrder(Long orderId) {
		Order order = new Order(orderId);

	    System.out.println("Ar norite pasirinkti patys ar atsitiktinai sugeneruoti užsakymą?");
	    System.out.println("1 - Pasirinkti patiems");
	    System.out.println("2 - Nustebink mane random užsakymas");
	    String choice = sc.nextLine();

	    if (choice.equals("2")) {
	        randomizeOrder(order);
	    } else {
	        do {
	            Preke preke = getItemFromUser();
	            int quantity = getQuantityForPreke(preke);
	            Long id = order.getNextFreeId();
	            order.addItem(new OrderItem(id, preke, quantity, preke.price()));
	        } while (arTesti());
	    }
	    return order;
	}
	
	private static boolean arTesti() {
		System.out.println("Ar norite dar ko nors? TAIP/NE");
		String atsakymas = sc.nextLine();
		return atsakymas.startsWith("T") || atsakymas.startsWith("t");
	}

	private static int getQuantityForPreke(Preke preke) {
		System.out.println("Jus pasirinkote " + preke.name());
		while(true) {
			System.out.println("Iveskite kieki");
			try {
				int skaicius = Integer.parseInt(sc.nextLine());
				if(skaicius >= 0)
					return skaicius;
				
				System.out.println("Pasirinkite nuo 0 ar daugiau!");
			} catch(Exception e) {
				System.out.println("Turi buti teigiamas skaicius arba 0");
			}
			
		}
		
	}

	public static Preke getItemFromUser() {
		System.out.println("Sveiki atvyke i PJ-Pizza");
		System.out.println("=".repeat(40));
		printMenu();
		
		while(true) {
			System.out.println("Iveskite pasirinkto patiekalo numeri:");
			String line = sc.nextLine();
			
			try {
				Long id = Long.parseLong(line);
				Optional<Preke> preke = Asortimentas
						.getInstance()
						.getById(id);
				
				if(preke.isPresent())
					return preke.get();
				
			} catch(Exception e) {
				System.out.println("Tokios prekes nera, pasirinkite is naujo");
			}
			
		}
		
	}
	
	private static void randomizeOrder(Order order) {
	    List<Preke> prekes = Asortimentas.getInstance().getAll();

	    System.out.println("Kiek atsitiktinių prekių norite įtraukti į užsakymą?");
	    int itemCount = Integer.parseInt(sc.nextLine());

	    for (int i = 0; i < itemCount; i++) {
	        Preke randomPreke = prekes.get((int) (Math.random() * prekes.size()));
	        int randomQuantity = (int) (Math.random() * 10) + 1;
	        Long id = order.getNextFreeId();
	        order.addItem(new OrderItem(id, randomPreke, randomQuantity, randomPreke.price()));
	    }

	    System.out.println("Atsitiktinis užsakymas sukurtas!");
	}
}
