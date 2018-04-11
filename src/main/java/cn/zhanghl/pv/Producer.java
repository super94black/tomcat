package cn.zhanghl.pv;

/**
 * @Author zhang
 * @Date 2018/4/11 12:56
 * @Content生产者
 */
public class Producer {
    private String lock;
    public Producer(String lock){
        super();
        this.lock = lock;
    }

    public void makeGoods(){
        try {
            synchronized (lock){
                while(!Goods.value.equals("")){
                    System.out.println(Thread.currentThread().getName() + " WATING");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + "Runnable");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                Goods.value = value;
                lock.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
