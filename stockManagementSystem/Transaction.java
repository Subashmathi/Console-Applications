package stockManagementSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Transaction {
	private String transactionId;
	private String productId;
	private String type;
	private int quantity;
	private Date date;
	Transaction(String id, String pid, String t, int q, Date d) throws Exception{
		transactionId = id;
		productId = pid;
		type = t;
		quantity = q;
		date = d;
		
	}
	public String getTransactionId() {
		return transactionId;
	}
	public String getProductId() {
		return productId;
	}
	public String getType() {
		return type;
	}
	public int getQuantity() {
		return quantity;
	}
	public Date getDate() {
		return date;
	}
}
