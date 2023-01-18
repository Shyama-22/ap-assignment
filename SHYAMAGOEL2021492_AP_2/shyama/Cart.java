package shyama;

import java.util.ArrayList;
import java.util.Map;

public class Cart {

    Map<Product, Integer> cartitems;
    ArrayList<Deal> cart_deals;

    public Cart() {
    }

    public Cart(Map<Product, Integer> prods) {
        cartitems = prods;
        cart_deals = new ArrayList<>();
    }

    public void addProduct(Product product, int q) {
        this.cartitems.put(product, q);

    }

    public Map<Product, Integer> getCartItems() {
        return cartitems;
    }

}
