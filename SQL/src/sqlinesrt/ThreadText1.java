package sqlinesrt;



public class ThreadText1 implements Runnable {

    public void run(){
        System.out.println(Thread.currentThread().getName()+"\t111");
    }

    public static void main(String[] args){
        ThreadText1 threadText1 = new ThreadText1();
        Thread thread1 = new Thread(threadText1,"线程1");
        Thread thread2 = new Thread(threadText1,"线程2");
        Thread thread3 = new Thread(threadText1,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
