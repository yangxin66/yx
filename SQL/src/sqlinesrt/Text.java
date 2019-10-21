package sqlinesrt;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Text {
    public static void  main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++) {
                        System.out.println(Thread.currentThread().getName()+j);
                    }
                    System.out.println("....................................");
                }
            });
        executorService.shutdown();
    }
}
