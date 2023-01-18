package shyama;

public class Deal {

    String Product_id1;
    String Product_id2;
    int price;

    public Deal(String product_id1, String product_id2, int price) {
        Product_id1 = product_id1;
        Product_id2 = product_id2;
        this.price = price;
    }

    public void display() {
        System.out.println("Product id 1 : " + Product_id1);
        System.out.println("Product id 2 : " + Product_id2);
        System.out.println("Giveaway Price : " + price);
    }
}
