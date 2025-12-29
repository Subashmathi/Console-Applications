package stockManagementSystem;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== Stock Management System =====");
            System.out.println("1. Add New Product");
            System.out.println("2. Add Stock");
            System.out.println("3. Remove Stock / Sell Product");
            System.out.println("4. Search Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Reports");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Supplier Name: ");
                    String supplier = sc.nextLine();
                    Product p = new Product(id, name, category, quantity, price, supplier);
                    inventory.addProduct(p);
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Quantity to add: ");
                    quantity = sc.nextInt();
                    inventory.addStock(id, quantity);
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Quantity to remove: ");
                    quantity = sc.nextInt();
                    inventory.removeStock(id, quantity);
                    break;
                case 4:
                    System.out.println("Search by: 1.ID 2.Name 3.Category");
                    int searchChoice = sc.nextInt();
                    sc.nextLine();
                    switch(searchChoice) {
                        case 1:
                            System.out.print("Enter Product ID: ");
                            id = sc.nextLine();
                            p = inventory.searchById(id);
                            if(p != null) printProduct(p);
                            else System.out.println("Product not found");
                            break;
                        case 2:
                            System.out.print("Enter Name: ");
                            name = sc.nextLine();
                            List<Product> list = inventory.searchByName(name);
                            if(list.isEmpty()) System.out.println("No products found");
                            else list.forEach(Main::printProduct);
                            break;
                        case 3:
                            System.out.print("Enter Category: ");
                            category = sc.nextLine();
                            list = inventory.searchByCategory(category);
                            if(list.isEmpty()) System.out.println("No products found");
                            else list.forEach(Main::printProduct);
                            break;
                    }
                    break;
                case 5:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextLine();
                    p = inventory.searchById(id);
                    if(p != null) inventory.removeProduct(p);
                    else System.out.println("Product not found");
                    break;
                case 6:
                    System.out.println("1. Low Stock 2. Sales Report 3. Purchase Report");
                    int reportChoice = sc.nextInt();
                    switch(reportChoice) {
                        case 1:
                            System.out.print("Enter stock threshold: ");
                            int threshold = sc.nextInt();
                            List<Product> lowStock = inventory.getLowStockProducts(threshold);
                            if(lowStock.isEmpty()) System.out.println("No low stock products");
                            else lowStock.forEach(Main::printProduct);
                            break;
                        case 2:
                            Map<String,Integer> sales = inventory.getSalesReport();
                            if(sales.isEmpty()) System.out.println("No sales yet");
                            else sales.forEach((k,v) -> System.out.println("Product ID: " + k + " Sold: " + v));
                            break;
                        case 3:
                            Map<String,Integer> purchases = inventory.getPurchaseReport();
                            if(purchases.isEmpty()) System.out.println("No purchases yet");
                            else purchases.forEach((k,v) -> System.out.println("Product ID: " + k + " Purchased: " + v));
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }

        sc.close();
    }
    private static void printProduct(Product p) {
        System.out.println("----- Product Details -----");
        System.out.println("ID: " + p.getProductId());
        System.out.println("Name: " + p.getName());
        System.out.println("Category: " + p.getCategory());
        System.out.println("Quantity: " + p.getQuantity());
        System.out.println("Price: " + p.getPrice());
        System.out.println("Supplier: " + p.getSupplier());
        System.out.println("---------------------------");
    }
}

