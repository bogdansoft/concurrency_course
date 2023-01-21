package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizationClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        var synchronizedList = Collections.synchronizedList(list);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        System.out.println(synchronizedList.size());
    }
}
