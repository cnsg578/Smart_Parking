package service;

public class CarFeeStrategy implements FeeStrategy {
    public double calculateFee(long hours) {
        return hours * 20;
    }
}