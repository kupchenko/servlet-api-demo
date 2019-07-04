package me.kupchenko.entity;

import me.kupchenko.annotation.FieldToRequest;

public class LandTransport extends Transport {

    @FieldToRequest(key = "WHEEL_NUMBER")
    private int wheelNumber;

    @FieldToRequest(key = "HORSE_POWER")
    private int horsePower;

    public LandTransport() {
    }

    public LandTransport(double weight, int price, double maxSpeed, int wheelNumber, int horsePower) {
        super(weight, price, maxSpeed);
        this.wheelNumber = wheelNumber;
        this.horsePower = horsePower;
    }

    public int getWheelNumber() {
        return wheelNumber;
    }

    public void setWheelNumber(int wheelNumber) {
        this.wheelNumber = wheelNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + horsePower;
        result = prime * result + wheelNumber;
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
        LandTransport other = (LandTransport) obj;
        if (horsePower != other.horsePower)
            return false;
        return wheelNumber == other.wheelNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " LandTransport [wheelNumber=" + wheelNumber + ", horsePower=" + horsePower + "]";
    }
}
