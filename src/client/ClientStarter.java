package client;

import client.Client;
import client.LoginPanel;

public class ClientStarter {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.setContentPanel(new LoginPanel(client)); // 初始化为登录界面
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
