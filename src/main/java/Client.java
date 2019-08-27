import javax.swing.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/27
 * @Description:
 */
public class Client extends JFrame {
    // 端口选择
    public JLabel serialPortSelectLabel = new JLabel("串口选择 : ");
    // 串口
    public JComboBox comboBox1;
    // 监听
    public JButton listenButton = new JButton("监听");
    // 取消监听
    public JButton cancelListenButton = new JButton("取消监听");
    // 设置区域
    public JPanel settingArea = new JPanel();
    // 测试区域
    public JPanel testArea = new JPanel();

    public void  openClient() {
        this.setTitle("十二路继电器模块配置终端");
        this.setSize(1000, 700);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Client().openClient();
    }
}
