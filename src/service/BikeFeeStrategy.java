package service;

public class BikeFeeStrategy implements FeeStrategy {
    public double calculateFee(long hours) {
        return hours * 10;
    }
}