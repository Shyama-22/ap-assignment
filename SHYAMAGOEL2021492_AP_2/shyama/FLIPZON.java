package shyama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class FLIPZON {

    static ArrayList<Category> categories = new ArrayList<Category>();
    static Map<String, Product> products = new HashMap<String, Product>();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Deal> deals = new ArrayList<>();
    static ArrayList<Customer> customerlist = new ArrayList<Customer>();

    public static void main(String[] args) {

        // adding three categories
        Category c1 = new Category(1, "Home Appliances", new ArrayList<>());
        Product p1 = new Product("Washing Machine", "1", 12000, 1, 2);
        c1.product_list.add(p1.productid);

        Category c2 = new Category(2, "Watch", new ArrayList<>());
        Product p2 = new Product("Samrt Watch", "2", 13000, 2, 5);
        c2.product_list.add(p2.productid);

        Category c3 = new Category(3, "Cometics", new ArrayList<>());
        Product p3 = new Product("Washing Machine", "123", 100, 3, 7);
        c3.product_list.add(p3.productid);

        products.put(p1.productid, p1);
        products.put(p2.productid, p2);
        products.put(p3.productid, p3);

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);

        System.out.println("\n Welcome  to  FLIPZON... ");

        while (true) {
            System.out.println("1 . Enter as Admin");
            System.out.println("2 . Explore the Product Catalog");
            System.out.println("3 . Show Available Deals");
            System.out.println("4 . Enter as Customer");
            System.out.println("5 . Exit the Application");

            int t;
            t = sc.nextInt();
            switch (t) {
                case 1:
                    adminwork();
                    break;
                case 2:
                    showproducts();
                    break;
                case 3:
                    showdeals();
                    break;
                case 4:
                    customerwork();
                    break;
                case 5:
                    System.out.println("Exiting.....");
                    return;
                default:
                    System.out.println("Invalid input!!");
            }

        }

    }

    static void adminwork() {
        System.out.println("Dear Admin,\n Please enter your username and password");
        String id = sc.next();
        sc.nextLine();
        String pass = sc.next();
        sc.nextLine();
        System.out.println(id + " " + pass);
        id.trim();
        pass.trim();
        if (id.equals("admin") && pass.equals("admin")) {
            adminpanel();
        } else {
            System.out.println("INVALID CREDENTIALS!!");
            return;
        }
    }

    static void customerwork() {
        while (true) {
            System.out.println("1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Back");

            int t = sc.nextInt();
            switch (t) {
                case 1:
                    signup();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Input!!");
            }
        }

    }

    static void signup() {
        System.out.println("Enter Name : ");
        String name = sc.next();
        System.out.println("Enter Phone Number : ");
        String phone = sc.next();

        System.out.println("Enter email :");
        String email = sc.next();
        System.out.println("Enter Password : ");
        String pass = sc.next();

        System.out.println("Enter Age : ");
        int age = sc.nextInt();

        Customer cust = new Customer(name, phone, email, pass, age);

        customerlist.add(cust);

        System.out.println("Customer Successfully Registered!!");
    }

    static void login() {
        System.out.println("Enter name : ");
        String name = sc.next();
        System.out.println("Enter Password : ");
        String pass = sc.next();

        name.trim();
        pass.trim();

        for (Customer c : customerlist) {
            if (c.name.equals(name) && c.getPassword().equals(pass)) {
                System.out.println("Welcome " + name);
                customerpanel(c);
            }
        }
        System.out.println("Invalid Credentials!!");
        return;

    }

    static void showdeals() {
        if (deals.size() == 0) {
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
            return;
        }

        for (Deal deal : deals) {
            deal.display();
        }

    }

    static void showproducts() {
        if (products.isEmpty()) {
            System.out.println("No products are available. Please come back later. \n\n");
            return;
        }

        int i = 1;

        for (Entry<String, Product> entry : products.entrySet()) {
            System.out.println("Product Number: " + i++);
            Product product = entry.getValue();
            product.display();
        }
    }

    static void adminpanel() {
        System.out.println("Welcome Dear Admin");

        System.out.println("Please choose any one of the following actions:");
        while (true) {
            System.out.println("1. Add Category");
            System.out.println("2. Delete Category");
            System.out.println("3. Add Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Set Discount on Product");
            System.out.println("6. Add Giveaway Deal");
            System.out.println("7. Exit");

            int t = sc.nextInt();

            switch (t) {
                case 1:
                    Admin.addcategory(sc, categories, products);
                    break;
                case 2:
                    Admin.deletecategory(sc, categories, products);
                    break;
                case 3:
                    Admin.addproduct(sc, products, categories);
                    break;
                case 4:
                    Admin.deleteproduct(sc, products, categories);
                    break;
                case 5:
                    Admin.setdiscount(sc, products);
                    break;
                case 6:
                    Admin.adddeal(sc, deals, products);
                    break;
                case 7:
                    System.out.println("Logging off as Admin");
                    return;
            }

        }
    }

    static void customerpanel(Customer c) {
        while (true) {
            System.out.println("1. Browse Products");
            System.out.println("2. Browse Deals");
            System.out.println("3. Add Product To cart");
            System.out.println("4. add products in deal to cart");
            System.out.println("5. view coupons");
            System.out.println("6. check account balance");
            System.out.println("7. view cart");
            System.out.println("8. empty cart");
            System.out.println("9.checkout cart");
            System.out.println("10. Upgrade customer status");
            System.out.println("11. Add amount to wallet");
            System.out.println("12. back");

            int t = sc.nextInt();
            switch (t) {
                case 1:
                    showproducts();
                    break;
                case 2:
                    showdeals();
                    break;
                case 3:
                    addtocart(c);
                    break;
                case 4:
                    c.add_deals_in_cart(sc, deals);
                    break;
                case 5:
                    c.view_coupon();
                case 6:
                    c.checkbalance();
                    break;
                case 7:
                    c.viewcart();
                    break;
                case 8:
                    c.emptycart();
                    break;
                case 9:
                    c.checkoutcart(products);
                    break;
                case 10:
                    c.upgradestatus(sc);
                    break;
                case 11:
                    c.addamount(sc);
                    break;
                case 12:
                    return;

            }

        }

    }

    static void addtocart(Customer c) {
        System.out.println("Enter the id product : ");

        String pid = sc.next();
        pid = pid.trim();
        System.out.println("Enter Quantity : ");
        int quantity = sc.nextInt();
        for (Entry<String, Product> entry : products.entrySet()) {
            if (entry.getKey().equals(pid)) {
                Product p = entry.getValue();
                c.addtocart(p, quantity);
                System.out.println("Added to Cart");
                return;
            }
        }

        System.out.println("Product Not Found");

    }
}