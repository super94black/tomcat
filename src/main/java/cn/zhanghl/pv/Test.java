package cn.zhanghl.pv;

/**
 * @Author zhang
 * @Date 2018/4/11 13:11
 * @Content
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Producer producer = new Producer(lock);
        Consumer consumer = new Consumer(lock);
        ThreadProducer[] pThread = new ThreadProducer[2];
        ThreadConsumer[] cThread = new ThreadConsumer[2];
        for (int i = 0; i < 2; i++){
            pThread[i] = new ThreadProducer(producer);
            pThread[i].setName("生产者" + (i+1) +"号");
            cThread[i] = new ThreadConsumer(consumer);
            cThread[i].setName("消费者" + (i+1) + "号");
            pThread[i].start();
            cThread[i].start();
        }


    }
}
class ThreadProducer extends Thread{

    private Producer producer;
    public ThreadProducer(Producer producer){
        super();
        this.producer = producer;
    }
    @Override
    public void run() {
        while (true){
            producer.makeGoods();
        }
    }
}

class ThreadConsumer extends Thread{
    private Consumer consumer;
    public ThreadConsumer(Consumer consumer){
        super();
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true){
            consumer.consumGoods();
        }
    }
}
