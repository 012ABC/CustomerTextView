package com.example.customertextview;

import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/14 18:48
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

    }

    private static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                count++;
                return new Thread(r, "Thread-" + count);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "started");
            }
        };
        Thread thread = threadFactory.newThread(runnable);
        thread.start();
    }


}