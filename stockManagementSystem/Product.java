package stockManagementSystem;
public class Product {
	private String productId;
	private String name;
	private String category;
	private int quantity;
	private String supplier;
	private double price;
	Product(String id, String n, String c, int q, double price2, String supplier2){
		productId = id;
		name = n;
		category = c;
		quantity = q;
		price = price2;
		supplier = supplier2;
	}
	public double getPrice() {
		return price;
	}
	public String getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getSupplier() {
		return supplier;
	}
	public void addQuantity(int q) {
		quantity+=q;
	}
	public void removeQuantity(int q) {
		quantity -= q;
	}
}
