package sqlinesrt;



public class ThreadText1 implements Runnable {

    public void run(){
        System.out.println(Thread.currentThread().getName()+"\t111");
    }

    public static void main(String[] args){
        ThreadText1 threadText1 = new ThreadText1();
        Thread thread1 = new Thread(threadText1,"�߳�1");
        Thread thread2 = new Thread(threadText1,"�߳�2");
        Thread thread3 = new Thread(threadText1,"�߳�3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
