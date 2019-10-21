package sqlinesrt;



public class ThreadText extends Thread{

    public void ThreadText(){

    }

    public void run(){
        System.out.println(Thread.currentThread().getName()+"111");
    }

    public static void main(String[] args){
        ThreadText Text = new ThreadText();
        ThreadText Text1 = new ThreadText();
        ThreadText Text2 = new ThreadText();
        Text.start();
        Text2.start();
        Text1.start();
//        Thread thread1 = new Thread(Text,"线程1");
//        Thread thread2 = new Thread(Text,"线程2");
//        Thread thread3 = new Thread(Text,"线程3");
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}
