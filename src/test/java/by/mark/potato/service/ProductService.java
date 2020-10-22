package by.mark.potato.service;

import by.mark.potato.annotation.Potato;
import by.mark.potato.dao.ProductDao;
import by.mark.potato.model.Product;
import lombok.Getter;

@Getter
@Potato(name = "productService")
@SuppressWarnings("unused")
public class ProductService {

    private UserService userService;
    private ProductDao productDao;

    public Product createUser(Product product) {
        return productDao.createProduct(product);
    }
}
