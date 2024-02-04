package by.mark.potato.service;

import by.mark.potato.annotation.Potato;
import by.mark.potato.dao.ProductDao;
import by.mark.potato.model.Product;

@SuppressWarnings("unused")
@Potato(name = "productService")
public class ProductService {

    private UserService userService;
    private ProductDao productDao;

    public Product createUser(Product product) {
        return productDao.createProduct(product);
    }

    // for the test sake
    public UserService getUserService() {
        return userService;
    }

    // for the test sake
    public ProductDao getProductDao() {
        return productDao;
    }
}
