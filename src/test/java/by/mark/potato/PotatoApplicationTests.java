package by.mark.potato;

import by.mark.potato.context.PotatoApplication;
import by.mark.potato.context.PotatoUtils;
import by.mark.potato.dao.ProductDao;
import by.mark.potato.dao.UserDao;
import by.mark.potato.service.ContractService;
import by.mark.potato.service.ProductService;
import by.mark.potato.service.UserService;
import org.junit.jupiter.api.Test;

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.notNull;

class PotatoApplicationTests {

    @Test
    void contextLoads() {
        PotatoApplication.run(PotatoApplicationTests.class);
        String contextNotLoaded = "Potato context hasn't loaded";
        String plantingFailure = "Planting potato wasn't successful";

        UserDao userDao = PotatoUtils.getPotato("userDao");
        ProductDao productDao = PotatoUtils.getPotato("productDao");
        UserService userService = PotatoUtils.getPotato("userService");
        ProductService productService = PotatoUtils.getPotato("productService");
        ContractService contractService = PotatoUtils.findPotato("contractService");

        notNull(userDao, contextNotLoaded);
        notNull(productDao, contextNotLoaded);
        notNull(userService, contextNotLoaded);
        notNull(productService, contextNotLoaded);
        isNull(contractService, "Contract service should be null");

        notNull(productDao.getUserDao(), plantingFailure);
        notNull(userService.getUserDao(), plantingFailure);
        notNull(productService.getProductDao(), plantingFailure);
        notNull(productService.getUserService(), plantingFailure);
    }
}
