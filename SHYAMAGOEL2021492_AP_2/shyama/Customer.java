package shyama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Customer {
    String name;
    private String phonenumber;
    private String emailid;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    int age;
    int type; // 0 normal , 1- prime, 2- Elite
    float balance;
    public Cart cart;
    ArrayList<Integer> coupons_list;

    public Customer() {

    }

    public Customer(String name, String phonenumber, String emailid, String password, int age) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.emailid = emailid;
        this.password = password;
        this.age = age;
        this.type = 0;
        this.balance = 1000;
        this.cart = new Cart(new HashMap<Product, Integer>());
        this.coupons_list = new ArrayList<>();

    }

    void displayAllProduct(Map<String, Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products are Available");
            return;
        }

        for (Entry<String, Product> entry : products.entrySet()) {
            System.out.println("Product Number: " + entry.getKey());
            Product product = entry.getValue();
            product.display();
        }

    }

    void addtocart(Product p, int q) {
        cart.addProduct(p, q);
    }

    void displaycart() {
        for (java.util.Map.Entry<Product, Integer> entry : cart.cartitems.entrySet()) {
            entry.getKey().display();
        }

        if (cart.cart_deals.size() == 0) {
            return;
        }
        System.out.println("Cart Item in Deal : ");
        for (Deal d : cart.cart_deals) {
            System.out.println("productID 1 : " + d.Product_id1);
            System.out.println("productID 2 : " + d.Product_id2);
            System.out.println("Combined Price : " + d.price);
        }
    }

    void checkbalance() {
        System.out.println("Balance is : " + this.balance);
    }

    void viewcart() {

        if (cart.cartitems.isEmpty()) {
            System.out.println("cart is Empty");
            return;
        }

        System.out.println("Cart Items : ");

        for (Entry<Product, Integer> entry : cart.cartitems.entrySet()) {
            entry.getKey().display();
            System.out.println("Quatity : " + entry.getValue());

        }
    }

    void emptycart() {
        cart.cartitems.clear();
        System.out.println("Cart is Emptied");
    }

    void checkoutcart(Map<String, Product> product_list) {
        int total = 0;

        System.out.println("Cart Item :");
        for (Entry<Product, Integer> entry : cart.cartitems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            int price = product.price;
            int discount = 0;
            int max_coupon_discount = max_coupon(this.coupons_list);
            int user_type_discount = getusertype_discount();
            int product_specific_discount = get_product_discount(product);

            if (product.quantity < entry.getValue()) {
                System.out.println("Product " + product.name + " is out of stock.. !!");
                return;
            }

            discount = Math.max(max_coupon_discount, Math.max(user_type_discount, product_specific_discount));

            if (discount == max_coupon_discount && max_coupon_discount != 0)
                this.coupons_list.remove(discount);

            total += (price - (discount / 100) * price) * quantity;
        }

        for (Entry<Product, Integer> entry : cart.cartitems.entrySet()) {
            Product product = entry.getKey();
            product_list.get(product.productid).quantity -= entry.getValue();
        }

        if (total >= 5000) {
            generatecoupon(this.coupons_list);
        }

        for (Entry<Product, Integer> entry : cart.cartitems.entrySet()) {
            System.out.println("Product Name : " + entry.getKey().name + "  Quantity : " + entry.getValue());

        }

        System.out.println("Total : " + total);
        cart.cartitems.clear();

        if (type == 0) {
            System.out.println("Your Order will be delivered in 7-10 days");
        } else if (type == 1) {
            System.out.println("Your Order will be delivered in 3-6 days");

        } else {
            System.out.println("Your Order will be delivered in 2 days");
        }

        System.out.println("Your Order is placed Successfully ");
    }

    int get_product_discount(Product p) {
        if (type == 0)
            return p.dicount_normal;
        else if (type == 1)
            return p.dicount_prime;
        else
            return p.dicount_elite;
    }

    void view_coupon() {
        if (coupons_list.size() == 0) {
            System.out.println("No Coupons Available for you !!");
            return;
        }

        System.out.println("Available Coupons are : ");
        for (int c : coupons_list) {
            System.out.print(c + "%   ");
        }
        System.out.println("");

    }

    int getusertype_discount() {
        if (type == 0)
            return 0;
        if (type == 1)
            return 2;

        return 5;

    }

    int max_coupon(ArrayList<Integer> coupon_list) {
        int maxi = 0;
        for (Integer c : coupon_list) {
            maxi = Math.max(maxi, c);
        }
        return maxi;
    }

    void generatecoupon(ArrayList<Integer> coupons_list) {
        int min = 5;
        int max = 15;
        int discount = (int) (Math.random() * (max - min + 1) + min);
        System.out.println("You have won coupons of " + discount + "% discount. Congratulations!!");
        coupons_list.add(discount);
    }

    void add_deals_in_cart(Scanner sc, ArrayList<Deal> deals) {
        System.out.println("Enter Product id 1(Which is in deal):");
        String pid1 = sc.next();
        pid1 = pid1.trim();

        System.out.println("Enter Product id 2(Which is in deal):");
        String pid2 = sc.next();
        pid2 = pid2.trim();

        for (Deal d : deals) {
            if ((d.Product_id1.equals(pid1) && d.Product_id2.equals(pid2))
                    || (d.Product_id1.equals(pid2) && d.Product_id2.equals(pid1))) {
                cart.cart_deals.add(d);
                System.out.println("Deal Added To Cart");
                return;
            }

        }
        System.out.println("Product are Not in Deal");
    }

    void upgradestatus(Scanner sc) {
        String status;
        if (type == 0)
            status = "NORMAL";
        else if (type == 1)
            status = "PRIME";
        else
            status = "ELITE";
        System.out.println("Current status : " + status);

        System.out.println("Choose New status : ");
        String newstatus = sc.next();
        newstatus = newstatus.trim();

        if (newstatus.equals("ELITE")) {
            if (balance >= 300) {
                System.out.println("Status Updated to ELITE");
                this.balance -= 300;
            } else {
                System.out.println("Insufficient Balance");
            }
        } else if (newstatus.equals("PRIME")) {
            if (balance >= 200) {
                System.out.println("Status updated to PRIME");
                this.balance -= 200;

            } else {
                System.out.println("Insufficient Balance");
            }
        } else {
            System.out.println("Invalid Input");
        }

    }

    void addamount(Scanner sc) {
        System.out.println("Enter Amount to be added to Wallet : ");
        int t = sc.nextInt();
        balance += t;

        System.out.println("Amount added to Wallet");
    }

}
