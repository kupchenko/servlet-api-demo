package me.kupchenko.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Order {

    private Date date;
    private double price;
    private HashMap<String, Integer> goods;

    public Order(Date date, double price, HashMap<String, Integer> goods) {
        this.date = date;
        this.price = price;
        this.goods = new HashMap<>(goods);
    }

    public Date getDate() {
        return date;
    }

    public HashMap<String, Integer> getGoods() {
        return goods;
    }


    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Order{" +
                "date=" + simpleDateFormat.format(date) +
                ", price=" + price +
                ", goods=" + goods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.price, price) != 0) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return goods != null ? goods.equals(order.goods) : order.goods == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }
}
