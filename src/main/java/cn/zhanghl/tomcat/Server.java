package cn.zhanghl.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zhang
 * @Date 2018/4/11 12:14
 * @Content 服务端
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    public void startServer(){
        try {
            //监听8080端口
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器启动");
            //循环代表不断监听
            while(true){
                Socket client = serverSocket.accept();
                //创建一个子线程去处理
                Thread thread = new Thread(new Handler(client));
                System.out.println("收到新的请求");
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
