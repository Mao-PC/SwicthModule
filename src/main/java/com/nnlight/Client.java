package com.nnlight;

import com.nnlight.view.ViewLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/27
 * @Description:
 */
public class Client extends JFrame {

    private static volatile Client client = null;

    private Client() throws HeadlessException {}

    public static Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    private ViewLayout viewLayout = ViewLayout.getViewLayout();

    // 串口
    public static JComboBox portComboBox = new JComboBox();
    // 监听
    public static JButton listenButton = new JButton("监听");
    // 取消监听
    public static JButton cancelListenButton = new JButton("取消监听");

    // IMEI
    public static JTextField imeiText = new JTextField();
    public static JButton imeiSetButton = new JButton("SET");
    public static JButton imeiReadButton = new JButton("READ");

    // AREAID
    public static JTextField areaText = new JTextField();
    public static JButton areaSetButton = new JButton("SET");
    public static JButton areaReadButton = new JButton("READ");

    // BOXID
    public static JTextField boxText = new JTextField();
    public static JButton boxSetButton = new JButton("SET");
    public static JButton boxReadButton = new JButton("READ");

    // 动作间隔
    public static JTextField spaceText = new JTextField();
    public static JButton spaceSetButton = new JButton("SET");
    public static JButton spaceReadButton = new JButton("READ");

    // 全开
    public static JButton openButton = new JButton("全开");

    // 全关
    public static JButton closeButton = new JButton("全关");

    // 读数据
    public static JButton readButton = new JButton("读数据");

    public void  openClient() {
        this.setTitle("十二路继电器模块配置终端");
        this.setSize(800, 500);
        this.setBackground(Color.WHITE);
        // 设置居中
        this.setLocationRelativeTo(null);

        viewLayout.setPanelLayout();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        getClient().openClient();
    }
}
