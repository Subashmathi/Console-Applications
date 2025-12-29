package stockManagementSystem;
import java.util.*;
public class Inventory {
	private Map<String, Product> products;
	private List<Transaction> transactions;
	Inventory(){
		products = new LinkedHashMap<>();
		transactions = new ArrayList<>();
	}
	public void addProduct(Product p) throws Exception {
		if(p.getQuantity() > 0 && p.getPrice() > 0) {
			if(products.containsKey(p.getProductId())) {
				System.out.println("Product already exists");
			}else {
				products.put(p.getProductId(), p);
				recordTransaction(p.getProductId(),"Purchase",new Date(), p.getQuantity());
			}
		}else {
			System.out.println("Quantity and price cannot be 0 or less");
		}
	}
	public void addStock(String id, int q) {
		if(q > 0) {
			if(products.containsKey(id)) {
				Product pro = products.get(id);
				pro.addQuantity(q);
			}else {
				System.out.println("Product not available in inventory");
			}
		}else {
			System.out.println("Quantity should not be 0 or less");
		}
	}
	public void removeStock(String id, int q) {
		if(q > 0) {
			if(products.containsKey(id)) {
				if(products.get(id).getQuantity() - q >= 0) {
					Product p = products.get(id);
					p.removeQuantity(q);
				}else {
					System.out.println("Stock below 0");
				}
			}else {
				System.out.println("Product not available in inventory");
			}
		}else {
			System.out.println("Invalid quantity");
		}
	}
	public void removeProduct(Product p) {
		if(products.containsKey(p.getProductId())) {
			products.remove(p.getProductId());
		}else {
			System.out.println("Product not available in inventory");
		}
	}
	public void recordTransaction(String productId, String type, Date date, int quantity) throws Exception {
		String tansactionId = UUID.randomUUID().toString();
		Transaction t = new Transaction(tansactionId, productId, type, quantity, date);
		transactions.add(t);
		System.out.println("Transaction saved");
	}
	public Product searchById(String id) {
		return products.containsKey(id) ? products.get(id) : null; 
	}
	public List<Product> searchByCategory(String category){
		List<Product> pros = new ArrayList<>();
		for(Product p : products.values()) {
			if(p.getCategory().equalsIgnoreCase(category)) {
				pros.add(p);
			}
		}
		return pros;
	}
	public List<Product> searchByName(String name){
		List<Product> pros = new ArrayList<>();
		for(Product p : products.values()) {
			if(p.getName().equalsIgnoreCase(name)) {
				pros.add(p);
			}
		}
		return pros;
	}
	public List<Product> getLowStockProducts(int threshold) {
	    List<Product> lowStock = new ArrayList<>();
	    for(Product p : products.values()) {
	        if(p.getQuantity() < threshold) {
	            lowStock.add(p);
	        }
	    }
	    return lowStock;
	}
	public Map<String, Integer> getSalesReport() {
	    Map<String, Integer> sales = new HashMap<>();
	    for(Transaction t : transactions) {
	        if(t.getType().equalsIgnoreCase("Sale")) {
	            sales.put(t.getProductId(), sales.getOrDefault(t.getProductId(), 0) + t.getQuantity());
	        }
	    }
	    return sales;
	}
	public Map<String, Integer> getPurchaseReport() {
	    Map<String, Integer> purchases = new HashMap<>();
	    for(Transaction t : transactions) {
	        if(t.getType().equalsIgnoreCase("Purchase")) {
	            purchases.put(t.getProductId(), purchases.getOrDefault(t.getProductId(), 0) + t.getQuantity());
	        }
	    }
	    return purchases;
	}
}
