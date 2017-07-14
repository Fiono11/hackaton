package server;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Cyrille on 14/07/17.
 */
public class TaskManager implements Runnable {

    private ExecutorService deadpool;
    private ConcurrentLinkedQueue<Task> taskQueue;

    public TaskManager(ConcurrentLinkedQueue<Task> queue){
        deadpool = Executors.newFixedThreadPool(10);
        taskQueue = queue;
    }

    @Override
    public void run() {

        while (true){

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!taskQueue.isEmpty()){
                //TODO create shutdown task to end
                deadpool.submit(taskQueue.poll());
            }

        }

    }
}
