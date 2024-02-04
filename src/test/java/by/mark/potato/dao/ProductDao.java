package by.mark.potato.dao;

import by.mark.potato.annotation.Potato;
import by.mark.potato.model.Product;

@SuppressWarnings("unused")
@Potato(name = "productDao")
public class ProductDao {

    private UserDao userDao;

    public Product createProduct(Product product) {
        return product;
    }

    // for the test sake
    public UserDao getUserDao() {
        return userDao;
    }
}
