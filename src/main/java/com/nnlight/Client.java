package com.nnlight;

import com.nnlight.exception.ExceptionWriter;
import com.nnlight.utils.SerialTool;
import com.nnlight.view.ViewLayout;
import gnu.io.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @Auther: maopch
 * @Date: 2019/8/27
 * @Description:
 */
public class Client extends JFrame {

    private static volatile Client client = null;

    private Client() throws HeadlessException {
    }

    public static Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    private ViewLayout viewLayout = ViewLayout.getViewLayout();

    public static volatile boolean actiongFlag = false;

    // 是否在写数据中
    public static volatile boolean isWriting = false;

    private List<String> commList = null;    //保存可用端口号
    public static SerialPort serialPort = null;    //保存串口对象

    // 波特率
    public static final int bps = 9600;

    // 串口
    public static JComboBox commChoice = new JComboBox();
    // 监听
    public static JButton listenButton = new JButton("监听");
    // 取消监听
    public static JButton cancelListenButton = new JButton("取消监听");
    // 是否监听串口按钮可用的标志
    public static boolean listenFlag = true;

    // IMEI
    public static JTextField imeiText = new JTextField();
    public static JButton imeiSetButton = new JButton("SET");
    public static JButton imeiReadButton = new JButton("READ");

    public static JLabel imeiWaringLabel = new JLabel();

    // AREAID
    public static JTextField areaText = new JTextField();
    public static JButton areaSetButton = new JButton("SET");
    public static JButton areaReadButton = new JButton("READ");

    public static JLabel areaWaringLabel = new JLabel();

    // BOXID
    public static JTextField boxText = new JTextField();
    public static JButton boxSetButton = new JButton("SET");
    public static JButton boxReadButton = new JButton("READ");

    public static JLabel boxWaringLabel = new JLabel();

    // 动作间隔
    public static JTextField spaceText = new JTextField();
    public static JButton spaceSetButton = new JButton("SET");
    public static JButton spaceReadButton = new JButton("READ");

    // 测试区的文本域
    public static JTextArea testArea = new JTextArea();

    public static JLabel spaceWaringLabel = new JLabel();

    // 全开
    public static JButton openButton = new JButton("全开");

    // 全关
    public static JButton closeButton = new JButton("全关");

    // 读数据
    public static JButton readButton = new JButton("读数据");

    // 清空测试区书记
    public static JButton clearButton = new JButton("清空测试区数据");

    public void openClient() {
        this.setTitle("十二路继电器模块配置终端");
        this.setSize(1000, 600);
        this.setBackground(Color.WHITE);
        // 设置图标
        this.setIconImage(new ImageIcon("computer.png").getImage());
        // 设置居中
        this.setLocationRelativeTo(null);

        viewLayout.setPanelLayout();

        //启动扫描串口线程
        new Thread(new RepaintThread()).start();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /*
     * 每隔30毫秒重扫描一次串口
     */
    private class RepaintThread implements Runnable {
        public void run() {
            while (true) {

                //扫描可用串口
                commList = SerialTool.findPort();
                if (commList != null && commList.size() > 0) {

                    //添加新扫描到的可用串口
                    for (String s : commList) {

                        //该串口名是否已存在，初始默认为不存在（在commList里存在但在commChoice里不存在，则新添加）
                        boolean commExist = false;

                        for (int i = 0; i < commChoice.getItemCount(); i++) {
                            if (s.equals(commChoice.getItemAt(i))) {
                                //当前扫描到的串口名已经在初始扫描时存在
                                commExist = true;
                                break;
                            }
                        }

                        if (commExist) {
                            //当前扫描到的串口名已经在初始扫描时存在，直接进入下一次循环
                            continue;
                        } else {
                            //若不存在则添加新串口名至可用串口下拉列表
                            commChoice.addItem(s);
                        }
                    }

                    //移除已经不可用的串口
                    for (int i = 0; i < commChoice.getItemCount(); i++) {

                        //该串口是否已失效，初始默认为已经失效（在commChoice里存在但在commList里不存在，则已经失效）
                        boolean commNotExist = true;

                        for (String s : commList) {
                            if (s.equals(commChoice.getItemAt(i))) {
                                commNotExist = false;
                                break;
                            }
                        }

                        if (commNotExist) {
                            //System.out.println("remove" + commChoice.getItem(i));
                            commChoice.remove(i);
                        } else {
                            continue;
                        }
                    }

                } else {
                    //如果扫描到的commList为空，则移除所有已有串口
                    commChoice.removeAll();
                }

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    String err = ExceptionWriter.getErrorInfoFromException(e);
                    JOptionPane.showMessageDialog(null, err, "错误", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }

    }

    public static void main(String[] args) {
        getClient().openClient();
    }
}
