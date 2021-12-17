package com.atguigu.gulimall.search.thread;

import java.util.concurrent.*;

/**
 * @Description：4种线程创建方式
 * @Author: nkc
 * @Date: 2021/12/17 12:54
 */
public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main线程启动");
        /**
         * 1.通过继承thread类方式
         */
        new MyThread().start();

        /**
         * 2.通过runnable方式
         */
        new Thread(new MyRunnable()).start();
        System.out.println("main线程结束");

        /**
         * 3.通过callable方式
         */
        FutureTask futureTask = new FutureTask(new MyCallAble());
        new Thread(futureTask).start();
        Object o = futureTask.get();
        System.out.println(o);


        /**
         * 4.通过线程池方式
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            threadPool.execute(new MyRunnable());
            threadPool.execute(new MyThread());
        } finally {
            threadPool.shutdown();
        }

        /**
         * 自定义线程池
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                10,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            poolExecutor.execute(new MyRunnable());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        setName("aa");
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        int i = 10 / 2;
        System.out.println("result：" + i);
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        int i = 10 / 2;
        System.out.println("result：" + i);
    }
}

class MyCallAble implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        int i = 10 / 2;
        System.out.println("result：" + i);
        return i;
    }
}