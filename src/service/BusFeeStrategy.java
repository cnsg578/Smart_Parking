package service;

public class BusFeeStrategy implements FeeStrategy {
    public double calculateFee(long hours) {
        return hours * 50;
    }
}