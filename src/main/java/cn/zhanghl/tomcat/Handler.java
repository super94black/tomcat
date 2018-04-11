package cn.zhanghl.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * @Author zhang
 * @Date 2018/4/11 12:17
 * @Content 处理浏览器发送的请求
 */
public class Handler implements Runnable{


    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;
    private PrintWriter printWriter;
    private static String webRoot = "E:\\";


    public Handler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            //获取服务端输入流
            inputStream = socket.getInputStream();
            //获取服务端输出流
            outputStream = socket.getOutputStream();
            //用缓冲区包装
            reader = new BufferedReader(new InputStreamReader(inputStream));
            printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

            String message;//客户端请求字符串
            StringBuffer request = new StringBuffer();
            while((message = reader.readLine()) != null && message.length() > 0){
                request.append(message);
                //HHTP协议格式
                request.append("\n");
            }

            //HTTP协议以空格作为分隔符
            String[] messages = request.toString().split(" ");
            //将服务器下的文件读入程序
            FileInputStream fileInputStream = new FileInputStream(new File(webRoot + messages[1]));
            //可能是二进制文件，所以用byte数组做缓冲
            byte[] fileBuffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(fileBuffer)) != -1){
                //向客户端发送文件
                outputStream.write(fileBuffer,0,len);
            }
            outputStream.close();
            inputStream.close();
            reader.close();
            fileInputStream.close();

        } catch (Exception e) {
            //如果抛出异常，在这里基本上是未找到被请求文件，所以返回404
            printWriter.write("HTTP/1.1 404 ERROR:FILE_UNFOUND");
            printWriter.close();
            e.printStackTrace();
        }
    }
}
