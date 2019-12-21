package com.nal.multithreading.examples;

import com.nal.multithreading.classes.CallableTask;
import com.nal.multithreading.classes.RunnableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.*;

/**
 * Created by nishant on 15/12/19.
 */
public class ExecutorServiceWithReturnDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        singleThreadExecution();
        fixedThreadExecutionInvokeAll();
        fixedThreadExecutionInvokeAny();
        cachedThreadPoolExecution();
        scheduleThreadPoolExecution();
     }

     //single thread uses LinkedBlockingQueue as the no of tasks in the queue can be unlimited
     // no of threads can be fixed (one)
    private static void singleThreadExecution() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //submit is used whe we have a return value (with callable), execute is used when no return value is expected (with runnable or thread)
        Future<String> futureValues = executorService.submit(new CallableTask(1));

        System.out.println("Task Submitted");
        System.out.println(futureValues.get());
        System.out.println("Task Completed");
        executorService.shutdown();
    }

    //fixed thread uses LinkedBlockingQueue as the no of tasks in the queue can be unlimited
    // no of threads can be fixed
    public static void fixedThreadExecutionInvokeAll() throws InterruptedException, ExecutionException {
        
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<CallableTask> callableTasks = List.of(new CallableTask(1),
                new CallableTask(2), new CallableTask(3), new CallableTask(4), new CallableTask(5));

        //invokes all the tasks and waits for all of them to complete
        List<Future<String>> futures = executorService.invokeAll(callableTasks);

        ListIterator<Future<String>> futureListIterator = futures.listIterator();
        while(futureListIterator.hasNext()) {
            System.out.println(futureListIterator.next().get());
        }
        executorService.shutdown();
    }

    public static void fixedThreadExecutionInvokeAny() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<CallableTask> callableTasks = List.of(new CallableTask(1),
                new CallableTask(2), new CallableTask(3), new CallableTask(4), new CallableTask(5));

        //returns the result of any one task which completes first
        //useful for you are using multiple services for same task and want to return the result to customer asap
        String result = executorService.invokeAny(callableTasks);
        System.out.println(result);

        executorService.shutdown();
    }

    //cached thread uses Synchronous Queue as there will be only one task in the queue at any given time
    // no of threads can be unlimited
    public static void cachedThreadPoolExecution() throws InterruptedException, ExecutionException {

        //spawn a new thread if all the threads in the pool are in use and adds the new thread in the pool
        //kills the thread in the pool if ideal for more than 1 min
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<String>> futures = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            futures.add(executorService.submit(new CallableTask(i)));
        }

        ListIterator<Future<String>> futureListIterator = futures.listIterator();
        while(futureListIterator.hasNext()) {
            System.out.println(futureListIterator.next().get());
        }
        executorService.shutdown();
    }

    //schedule thread uses Delayed Work Queue (special queue which deals with schedule\time delays)
    public static void scheduleThreadPoolExecution() throws InterruptedException, ExecutionException {

        //for scheduling tasks, works only with Runnable, and not with Callable
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        System.out.println("Job Started");
        //Execute tasks after a fixed delay of 10 secs
        ScheduledFuture<String> schedule = executorService.schedule(new CallableTask(1), 2, TimeUnit.SECONDS);
        System.out.println(schedule.get());

        //1st task runs after 5 sec and then runs repeatedly every 2 sec
        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(new RunnableTask(2), 5, 2, TimeUnit.SECONDS);
        stopScheduler(scheduledFuture);

        RunnableTask.setCount(0);
        //1st task runs after 5 sec and then runs repeatedly after every 2 sec of finishing the previous task
        ScheduledFuture<?> scheduledFuture2 = executorService.scheduleWithFixedDelay(new RunnableTask(3), 5, 2, TimeUnit.SECONDS);

        stopScheduler(scheduledFuture2);
        executorService.shutdown();
    }

    private static void stopScheduler(ScheduledFuture<?> scheduledFuture) throws InterruptedException {
        while (true) {
            System.out.println("Count :" + RunnableTask.getCount());
            Thread.sleep(1000);
            if (RunnableTask.getCount() == 5) {
                //to stop the scheduled job from running for infinite times
                scheduledFuture.cancel(true);
                break;
            }
        }
    }
}
