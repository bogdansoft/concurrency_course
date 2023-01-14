package org.example;

import java.math.BigInteger;

public class ComplexCalculation {
    public static void main(String[] args) throws InterruptedException {
        var res = new ComplexCalculation().calculateResult(new BigInteger("2")
                , new BigInteger("3")
                , new BigInteger("4")
                , new BigInteger("2"));

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
