package shyama;

import java.util.ArrayList;

public class Category {
    int category_id;
    String category_name;
    ArrayList<String> product_list; // product id

    public Category() {

    }

    public Category(int id, String categoryName, ArrayList<String> list) {
        this.category_name = categoryName;
        category_id = id;
        product_list = list;
    }

}
