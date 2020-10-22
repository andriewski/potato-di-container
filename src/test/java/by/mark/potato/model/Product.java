package by.mark.potato.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class Product {

    private String name;

    private User user;
}
