package org.example;

import java.math.BigInteger;

/**
 * Multithreaded Calculation
 * In this coding exercise you will use all the knowledge from the previous lectures.
 * Before taking the exercise make sure you review the following topics in particular:
 * 1. Thread Creation - how to create and start a thread using the Thread class and the start() method.
 * <p>
 * 2. Thread Join - how to wait for another thread using the Thread.join() method.
 * <p>
 * In this exercise we will efficiently calculate the following result = base1 ^ power1 + base2 ^ power2
 * <p>
 * Where a^b means: a raised to the power of b.
 * <p>
 * For example 10^2 = 100
 * <p>
 * We know that raising a number to a power is a complex computation, so we we like to execute:
 * <p>
 * result1 = x1 ^ y1
 * <p>
 * result2 = x2 ^ y2
 * <p>
 * In parallel.
 * <p>
 * and combine the result in the end : result = result1 + result2
 * <p>
 * This way we can speed up the entire calculation.
 * <p>
 * Note :
 * <p>
 * base1 >= 0, base2 >= 0, power1 >= 0, power2 >= 0
 */
public class ComplexCalculation {
    public static void main(String[] args) throws InterruptedException {
        var res = new ComplexCalculation().calculateResult(
                new BigInteger("2"),
                new BigInteger("3"),
                new BigInteger("4"),
                new BigInteger("2")
        );

        System.out.println(res);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread firstThread = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread secondThread = new PowerCalculatingThread(base2, power2);

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        result = firstThread.getResult().add(secondThread.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            if (base.longValue() >= 0 && power.longValue() >= 0) {
                result = base.pow(power.intValue());
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
