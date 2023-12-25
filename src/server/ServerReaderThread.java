package server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {
                    String msg = dis.readUTF();
                    System.out.println("Client "+socket.getRemoteSocketAddress()+" > "+msg);
                } catch (Exception e) {
                    System.out.println("\033[32mClient logOut >> IP: "+socket.getRemoteSocketAddress()
                            + " >> Time: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            +"\033[0m");
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
