package me.kupchenko.service;


import me.kupchenko.dao.ProductDAOImpl;
import me.kupchenko.entity.Transport;

public class ProductService {
    private ProductDAOImpl productDAO;

    public ProductService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    public String getAllProductsAsString() {
        final StringBuilder result = new StringBuilder();
        result.append("Products:\n");
        productDAO.getAll().forEach((key, val) -> {
            result.append("[article = ")
                    .append(key)
                    .append(", readers = ")
                    .append(val)
                    .append("]\n");

        });
        return result.toString();
    }

    public Transport getTransport(String string) {
        return productDAO.getProductByArticle(string);
    }

    public int getCount() {
        return productDAO.getAll().size();
    }

    public void add(String string, Transport transport) {
        productDAO.add(string, transport);
    }
}
