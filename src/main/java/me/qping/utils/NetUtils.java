package me.qping.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @ClassName NetUtils
 * @Description 网络工具
 * @Author qping
 * @Date 2021/5/12 11:44
 * @Version 1.0
 **/
public class NetUtils {

    public boolean testPort(String host, int port) throws Exception {
        if (port < 0 || port > 65535) {
            throw new Exception("端口范围错误,必须在 [ 0 - 65535 ] 之间");
        }
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port), 5000);
        } catch (UnknownHostException e) {
            throw new Exception("主机名无效: " + e.getMessage());
        } catch (SocketTimeoutException e) {
            throw new Exception("连接超时: " + e.getMessage());
        } catch (java.net.ConnectException e) {
            e.printStackTrace();
            throw new Exception("连接失败，端口不通: " + e.getMessage());
        } catch (java.lang.IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("端口或者主机名错误: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    
}
