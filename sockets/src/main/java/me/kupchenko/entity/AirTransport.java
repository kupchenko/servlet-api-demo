package me.kupchenko.entity;

import me.kupchenko.annotation.FieldToRequest;

public class AirTransport extends Transport {

    @FieldToRequest(key = "CAPACITY")
    private double capacity;

    public AirTransport() {

    }

    public AirTransport(double weight, int price, double maxSpeed, double capacity) {
        super(weight, price, maxSpeed);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(capacity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AirTransport other = (AirTransport) obj;
        return Double.doubleToLongBits(capacity) == Double.doubleToLongBits(other.capacity);
    }

    @Override
    public String toString() {
        return "AirTransport [capacity=" + capacity + "]";
    }
}
