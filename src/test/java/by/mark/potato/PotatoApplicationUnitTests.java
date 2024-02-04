package by.mark.potato;

import by.mark.potato.context.PotatoApplication;
import by.mark.potato.dao.ProductDao;
import by.mark.potato.dao.UserDao;
import by.mark.potato.service.ContractService;
import by.mark.potato.service.ProductService;
import by.mark.potato.service.UserService;
import org.junit.jupiter.api.Test;

import static by.mark.potato.context.PotatoApplication.findPotato;
import static by.mark.potato.context.PotatoApplication.getPotato;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PotatoApplicationUnitTests {

    @Test
    void run_withPotatoesClasses_shouldLoadDiContainerAsExpected() {
        PotatoApplication.run(PotatoApplicationUnitTests.class);
        String contextNotLoaded = "Potato context hasn't loaded";
        String plantingFailure = "Planting potato wasn't successful";

        UserDao userDao = getPotato("userDao");
        ProductDao productDao = getPotato("productDao");
        UserService userService = getPotato("userService");
        ProductService productService = getPotato("productService");
        ContractService contractService = findPotato("contractService");

        assertNotNull(userDao, contextNotLoaded);
        assertNotNull(productDao, contextNotLoaded);
        assertNotNull(userService, contextNotLoaded);
        assertNotNull(productService, contextNotLoaded);
        assertNull(contractService, "Contract service should be null");

        assertNotNull(productDao.getUserDao(), plantingFailure);
        assertNotNull(userService.getUserDao(), plantingFailure);
        assertNotNull(productService.getProductDao(), plantingFailure);
        assertNotNull(productService.getUserService(), plantingFailure);
    }
}
