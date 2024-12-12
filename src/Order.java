import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Order {
	Long id;
	List<OrderItem> items = new ArrayList<>();	
	LocalDateTime created;
	
	public Order(Long id) {
		this.id = id;
		this.created = LocalDateTime.now();
	}
	
	// CRUD methods
	
	public Long getNextFreeId() {
		return items.stream()
			.mapToLong(item -> item.getId())
					.max()
					.orElse(0) + 1;
			
	}
	
	public boolean addItem(OrderItem item) {
		return items.add(item);
	}
	
	public List<OrderItem> getAllItems() {
		return items;
	}
	
	public Optional<OrderItem> getItemById(Long id) {
		return items.stream()
				.filter(i -> i.getId() == id)
				.findFirst();
	}
	
	public Double getSum() {
		return items.stream()
				.mapToDouble(i -> i.getSum())
				.sum();
	}
	
	public boolean updateItem(OrderItem item) {
		int index = items.indexOf(item);
		if (index < 0)
			return false;
		
		items.remove(index);
		items.add(index,item);
		return true;
	}
	
	// impro by chat
	public boolean deleteItemById(Long id) {
	    return items.removeIf(item -> item.getId().equals(id));
	}
	
	public boolean deleteItem(OrderItem item) {
		return items.remove(item);
	}

	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");;
		String title = "Uzsakymas " + id + " " + created.format(formatter) + "\n";
		title += "-".repeat(40) + "\n";
		
		String content = items.stream()
			.map(item -> item.toOderLine())
			.collect(Collectors.joining("\n")) + "\n";
		
		content += "-".repeat(40) + "\n";
		String footer = String.format("Suma: %.2f EUR",getSum()); 
		
		return title + content + footer;
	
	}
	
}
