package shyama;

public class Product {
    String name;
    String productid;
    int price;
    int dicount_normal;
    int dicount_prime;
    int dicount_elite;
    int category_id;
    int quantity;

    public Product() {

    }

    public Product(String name, String productid, int price, int category_id, int quantity) {
        this.name = name;
        this.productid = productid;
        this.price = price;
        this.dicount_normal = 0;
        this.dicount_prime = 0;
        this.dicount_elite = 0;
        this.category_id = category_id;
        this.quantity = quantity;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDicount_normal() {
        return dicount_normal;
    }

    public void setDicount_normal(int dicount_normal) {
        this.dicount_normal = dicount_normal;
    }

    public int getDicount_prime() {
        return dicount_prime;
    }

    public void setDicount_prime(int dicount_prime) {
        this.dicount_prime = dicount_prime;
    }

    public int getDicount_elite() {
        return dicount_elite;
    }

    public void setDicount_elite(int dicount_elite) {
        this.dicount_elite = dicount_elite;
    }

    public void display() {
        System.out.println("Product id : " + productid);
        System.out.println("Product Name : " + name);
        System.out.println("Price : " + price);
        System.out.println(" Discount : ");
        System.out.println("\n For Normal : " + dicount_normal);
        System.out.println("\n For Prime : " + dicount_prime);
        System.out.println("\n For Elite : " + dicount_elite);
        System.out.println("\n");
    }

}