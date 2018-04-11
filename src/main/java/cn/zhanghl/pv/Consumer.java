package cn.zhanghl.pv;

/**
 * @Author zhang
 * @Date 2018/4/11 13:06
 * @Content 消费者
 */
public class Consumer {
    private String lock;
    public Consumer(String lock){
        super();
        this.lock = lock;
    }

    public void consumGoods(){
        try {
            synchronized (lock){
                while(Goods.value.equals("")){
                    System.out.println(Thread.currentThread().getName() + " WATING");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + "Runnable");
                Goods.value = "";
                lock.notify();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
