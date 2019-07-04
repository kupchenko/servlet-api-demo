package me.kupchenko.storage;


import me.kupchenko.entity.Order;
import me.kupchenko.entity.Transport;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Storage {

    private final static int HISTORY_SIZE = 5;

    private LinkedHashMap<String, Transport> goods = new LinkedHashMap<>();

    private LinkedHashMap<String, Integer> shoppingCart = new LinkedHashMap<>();

    private TreeMap<Date, Order> orderList = new TreeMap<>();

    private Map<String, Integer> historyGoods = new LinkedHashMap<String, Integer>(HISTORY_SIZE + 1, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
            return size() > HISTORY_SIZE;
        }
    };

    public Storage(LinkedHashMap<String, Transport> goods,
                   LinkedHashMap<String, Integer> history,
                   LinkedHashMap<String, Integer> shoppingCart,
                   TreeMap<Date, Order> orders) {
        this.goods.putAll(goods);
        historyGoods.putAll(history);
        orderList.putAll(orders);
        shoppingCart.putAll(shoppingCart);
    }

    public LinkedHashMap<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(LinkedHashMap<String, Integer> map) {
        shoppingCart = map;
    }

    public LinkedHashMap<String, Transport> getGoods() {
        return goods;
    }

    public void setGoods(LinkedHashMap<String, Transport> goods) {
        this.goods = goods;
    }

    public TreeMap<Date, Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(TreeMap<Date, Order> orderList) {
        this.orderList = orderList;
    }

    public Map<String, Integer> getHistoryGoods() {
        return historyGoods;
    }

    public void setHistoryGoods(LinkedHashMap<String, Integer> historyGoods) {
        this.historyGoods = historyGoods;
    }
}
