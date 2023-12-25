package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("\033[32mServer Start"+ "\033[0m");
        //创建ServerSocket对象，服务端注册端口
        ServerSocket serverSocket = new ServerSocket(8888);
        //主线程负责接收连接
        while (true) {
            //接收Client连接请求
            Socket socket = serverSocket.accept();
            System.out.println("\033[32mClient logIn >> IP: "+socket.getRemoteSocketAddress()
                    + " >> Time: "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    +"\033[0m");
            //分配Client线程
            new ServerReaderThread(socket).start();
        }
    }
}
