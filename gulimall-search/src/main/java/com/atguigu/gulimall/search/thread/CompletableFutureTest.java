package com.atguigu.gulimall.search.thread;

import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.*;

/**
 * @Description： 异步编排使用
 * @Author: nkc
 * @Date: 2021/12/17 13:57
 */
public class CompletableFutureTest {
    static ExecutorService threadPool = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "当前线程开始执行");
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//        }, threadPool);
//        System.out.println("main...end...");

//        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "当前线程开始执行");
//            int i = 10 / 0;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, threadPool).whenComplete((res, exception) -> {
//            System.out.println("异步任务完成了...结果是：" + res + ";异常是：" + exception);
//        }).exceptionally(throwable -> {
//            return 10;
//        });
        /**
         * 线程串行化
         *  1）thenRun：不能获取到上一步结果，无返回值
         *      .thenRunAsync：不能获取到上一步结果，无返回值（开启一个新的线程）
         *  2）thenAcceptAsync：能接受上一步结果，但无返回值
         *  3）thenApplyAsync：能接收到上一步结果，有返回值结果
         */
        try {
//            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//                System.out.println(Thread.currentThread().getName() + "当前线程执行");
//                int i = 10 / 2;
//                return i;
//            }, threadPool).handle((res, exception) -> {
//                if (res != null) {
//                    return res * 2;
//                }
//                if (exception != null) {
//                    return 0;
//                }
//                return 0;
//            }).thenApplyAsync(res -> {
//                System.out.println("任务2启动。。。" + Thread.currentThread().getName());
//                return "hello" + res;
//            },threadPool);
            CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "当前线程执行");
                int i = 10 / 2;
                return i;
            }, threadPool);
            CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "任务2开始");
                return "Hello";
            }, threadPool);
            /**
             * 两个都完成*************************************************************************************
             */
            /**
             * runAfterBothAsync：无法感知上面任务的返回值结果
             */
//            future01.runAfterBothAsync(future02,()->{
//                System.out.println("任务3开始");
//            },threadPool);
            /**
             * runAfterBothAsync：感知上面任务的结果
             */
//            future01.thenAcceptBothAsync(future02,(f1,f2)->{
//                System.out.println("之前结果组合：" + f1 + f2);
//            },threadPool);
            /**
             * thenCombineAsync:感知上面任务的结果并有返回值
             */
//            CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
//                return (f1 + f2);
//            }, threadPool);
            /**
             * 两个任务只要完成一个*********************************************************************************
             */
            /**
             * runAfterEitherAsync:不感知结果，无返回值
             */
//            CompletableFuture<Void> future = future01.runAfterEitherAsync(future02, () -> {
//                System.out.println("任务3开始执行");
//            }, threadPool);
            /**
             * acceptEitherAsync：感知解决，无返回值
             */
//            future01.acceptEitherAsync(future02, (res -> {
//                System.out.println("任务3开始执行，res：" + res);
//            }), threadPool);
            /**
             * applyToEitherAsync:感知上面的结果并有返回值
             */
//            CompletableFuture<String> future = future01.applyToEitherAsync(future02, res -> {
//                System.out.println("任务3开始执行，res：" + res);
//                return res.toString() + "=>哈哈";
//            }, threadPool);
            /**
             * 多任务组合：******************************************************************************************
             *  allOf：等待所有任务完成
             *  anyOf：只要有一个任务完成
             */
            CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
                System.out.println("返回商品图片信息");
                return "hw.jpg";
            }, threadPool);
            CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
                System.out.println("返回商品详细信息");
                return "华为手机";
            }, threadPool);
            CompletableFuture<String> futureCategory = CompletableFuture.supplyAsync(() -> {
                System.out.println("返回商品类别信息");
                return "手机类别";
            }, threadPool);
//            CompletableFuture.allOf(futureImg,futureDesc,futureCategory);
            CompletableFuture<Object> future = CompletableFuture.anyOf(futureImg, futureDesc, futureCategory);
            System.out.println(future.get());
            //阻塞方法
//            String str = future.get();
//            System.out.println(str);
        } finally {
            threadPool.shutdown();
        }
    }
}
