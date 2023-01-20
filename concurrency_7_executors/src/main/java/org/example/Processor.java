package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Processor implements Callable<String> {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futuresList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Future<String> future = executorService.submit(new Processor(i));
            futuresList.add(future);
        }

        futuresList.forEach(v -> {
            try {
                System.out.println(v.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }
}
