package me.kupchenko.dao;

import me.kupchenko.entity.Transport;

import java.util.HashMap;

public class ProductDAOImpl implements ProductDAO {
    private HashMap<String, Transport> products;

    public ProductDAOImpl(HashMap<String, Transport> products) {
        this.products = products;
    }


    public HashMap<String, Transport> getAll() {
        return products;
    }


    public Transport getProductByArticle(String article) {
        return products.get(article);
    }

    public void add(String article, Transport transport) {
        products.put(article, transport);
    }

}
