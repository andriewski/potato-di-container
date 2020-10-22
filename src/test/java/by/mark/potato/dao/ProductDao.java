package by.mark.potato.dao;

import by.mark.potato.annotation.Potato;
import by.mark.potato.model.Product;
import lombok.Getter;

@Potato(name = "productDao")
@SuppressWarnings("unused")
public class ProductDao {

    @Getter
    private UserDao userDao;

    public Product createProduct(Product product) {
        return product;
    }
}
