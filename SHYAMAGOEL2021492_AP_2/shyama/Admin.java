package shyama;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Admin {

    static void deletecategory(Scanner sc, ArrayList<Category> categories, Map<String, Product> products) {
        System.out.println("Enter Category id : ");
        int cid = sc.nextInt();

        for (Category category : categories) {
            if (category.category_id == cid) {
                // delete all product in that category
                for (String pid : category.product_list) {
                    products.remove(pid);
                }

                categories.remove(category);
                System.out.println("Category Deleted Successfully\n\n");
                return;
            }
        }

    }

    static void addcategory(Scanner sc, ArrayList<Category> categories, Map<String, Product> products) {
        System.out.println("Enter Category Id :");
        int t = sc.nextInt();

        for (Category category : categories) {
            if (category.category_id == t) {
                System.out.println(
                        "Dear Admin, the category ID is already used!!! Please set a different and a unique category ID\n\b");
                return;
            }
        }

        System.out.println("Enter Category Name : ");
        sc = new Scanner(System.in);
        String cname = sc.nextLine();

        Category c = new Category(t, cname, new ArrayList<String>());
        categories.add(c);

        addproduct2(sc, categories, products, t);
    }

    static void addproduct2(Scanner sc, ArrayList<Category> categories, Map<String, Product> products,
            int category_id) {
        String name, productid;
        int price;
        System.out.println("Enter Product name : ");
        sc = new Scanner(System.in);
        name = sc.nextLine();
        System.out.println("Enter Product Id : ");
        productid = sc.next();
        System.out.println("Enter price of Product :");
        price = sc.nextInt();

        System.out.println("Enter Stock Quantity : ");
        int quantity = sc.nextInt();

        // check product already exist or not
        if (check(products, productid)) {
            System.out.println("Product ID Already Exist. Please set differnt and unique product Id\n\n");
            return;
        }

        Product p = new Product(name, productid, price, category_id, quantity);

        for (Category category : categories) {
            if (category.category_id == category_id) {
                category.product_list.add(p.productid);
            }
        }

        products.put(productid, p);
        System.out.println("Product Added Successfully\n\n");
    }

    static void addproduct(Scanner sc, Map<String, Product> products, ArrayList<Category> categorylist) {
        System.out.println("Enter Category Id : ");
        int cid = sc.nextInt();

        for (Category category : categorylist) {
            if (category.category_id == cid) {
                String name, productid;
                int price, quantity;
                System.out.println("Enter Product name : ");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                System.out.println("Enter Product Id : ");
                productid = sc.next();
                System.out.println("Enter price of Product :");
                price = sc.nextInt();
                System.out.println("Enter Stock Quantity : ");
                quantity = sc.nextInt();

                // check product already exist or not
                if (check(products, productid)) {
                    System.out.println("Product ID Already Exist. Please set differnt and unique product Id");
                    return;
                }

                Product p = new Product(name, productid, price, cid, quantity);

                category.product_list.add(productid);
                products.put(productid, p);
                System.out.println("Product Added Successfully\n\n");
                return;
            }
        }

        System.out.println("Category does not exist !!");
        return;

    }

    public static boolean check(Map<String, Product> products, String pid) {
        for (Entry<String, Product> entry : products.entrySet()) {
            if (entry.getKey() == pid) {
                return true;
            }
        }
        return false;
    }

    static void deleteproduct(Scanner sc, Map<String, Product> products, ArrayList<Category> categorylist) {
        System.out.println("Enter Product Id : ");
        String pid = sc.next();

        for (Entry<String, Product> entry : products.entrySet()) {
            if (pid == entry.getKey()) {
                Product p = entry.getValue();
                int cid = p.category_id;

                for (Category category : categorylist) {
                    if (cid == category.category_id) {
                        category.product_list.remove(pid);
                        if (category.product_list.isEmpty()) {
                            categorylist.remove(category);
                        }
                    }
                }
                products.remove(pid, p);

                // product is removed from both category-product list and products map
                System.out.println("Product is deleted.");
            }

        }

        System.out.println("Product Not Found.");

        return;
    }

    static void setdiscount(Scanner sc, Map<String, Product> products) {
        System.out.println("Enter the Product id :");
        String pid = sc.next();
        pid = pid.trim();
        for (Entry<String, Product> entry : products.entrySet()) {
            if (entry.getKey().equals(pid)) {
                System.out.println("Enter the Discount for Normal, Prime, Elite Customers in % : ");
                int d1 = sc.nextInt();
                int d2 = sc.nextInt();
                int d3 = sc.nextInt();

                Product prod = entry.getValue();
                prod.dicount_normal = d1;
                prod.dicount_prime = d2;
                prod.dicount_elite = d3;

                System.out.println("Discounts are Setted");

                return;

            }
        }
    }

    static void adddeal(Scanner sc, ArrayList<Deal> deals, Map<String, Product> products) {
        System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for");
        System.out.println("Enter the first Product Id ");
        String p1 = sc.next();

        System.out.println("Enter the second Product Id ");
        String p2 = sc.next();

        System.out.println("Enter the combined price(Should be less than their combined price):");
        int price = sc.nextInt();

        Deal deal = new Deal(p1, p2, price);
        deals.add(deal);

    }

}
