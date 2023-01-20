package org.example;

public class StockMarketUpdate implements Runnable{
    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from web...");
    }
}
