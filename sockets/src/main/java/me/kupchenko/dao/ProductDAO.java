package me.kupchenko.dao;


import me.kupchenko.entity.Transport;

import java.util.HashMap;

public interface ProductDAO {
    HashMap<String, Transport> getAll();

    Transport getProductByArticle(String article);
}
