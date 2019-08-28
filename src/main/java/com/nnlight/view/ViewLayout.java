package com.nnlight.view;

import com.nnlight.listeners.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

import static com.nnlight.Client.*;


/**
 * 功能描述: 页面布局
 *
 * @auther: pikaqiu
 * @date: 2019/8/27 8:34 PM
 */
public class ViewLayout {

    private static ViewLayout viewLayout = null;

    public static ViewLayout getViewLayout () {
        if (viewLayout == null) {
            viewLayout = new ViewLayout();
        }
        return viewLayout;
    }

    // 字体
    private Font font = new Font("微软雅黑", Font.PLAIN, 15);

    // 端口选择
    private JLabel serialPortSelectLabel = new JLabel("串口选择 : ");

    // 监听面板
    private JPanel listenPanel = new JPanel();
    // 设置面板
    private JPanel settingPanel = new JPanel();
    // 测试面板
    private JPanel testPanel = new JPanel();
    // 功能面板
    private JPanel optPanel = new JPanel();

    // IMEI label
    private JLabel imeiLabel = new JLabel("IMEI");
    // areaid label
    private JLabel areaLabel = new JLabel("AREAID");
    // boxid label
    private JLabel boxLabel = new JLabel("BOXID");
    // 动作间隔 label
    private JLabel spaceLabel = new JLabel("动作间隔");


//    private Color backgroupColor = Color.

    private void add(Component component, String layout){
        getClient().add(component, layout);
    }

    private void add(Component component) {
        getClient().add(component);
    }

    private void setLayout(LayoutManager layout) {
        getClient().setLayout(layout);
    }

    public void setPanelLayout() {

        // 整体采用边框布局
        setLayout(new BorderLayout());

        // 设置监听面板
        add(listenPanel, BorderLayout.NORTH);

        listenPanel.add(serialPortSelectLabel);
        commChoice.setPreferredSize(new Dimension(150, 30));
        listenPanel.add(commChoice);

        // 监听
        listenButton.addActionListener(new ListenButtonListener());
        listenPanel.add(listenButton);

        // 取消监听
        cancelListenButton.addActionListener(new CancelListenButtonListener());
        listenPanel.add(cancelListenButton);

        // 设置设置面板
        settingPanel.setPreferredSize(new Dimension());
        settingPanel.setBorder(BorderFactory.createTitledBorder("设置区"));
        settingPanel.setPreferredSize(new Dimension(400, 400));

        // 设置面板使用网格布局
        settingPanel.setLayout(null);
        // 设置IMEI
        int settingY = 50;
        imeiLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(imeiLabel);
        imeiText.setBounds(90, settingY, 150, 30);
        settingPanel.add(imeiText);
        imeiSetButton.setBounds(250, settingY, 60, 30);
        imeiSetButton.addActionListener(new IMEISetButtonListener());
        settingPanel.add(imeiSetButton);
        imeiReadButton.setBounds(320, settingY, 70, 30);
        imeiReadButton.addActionListener(new IMEIReadButtonListener());
        settingPanel.add(imeiReadButton);
        imeiWaringLabel.setBounds(30, settingY + 30, 250, 30);
        imeiWaringLabel.setFont(font);
        imeiWaringLabel.setForeground(Color.RED);
        settingPanel.add(imeiWaringLabel);

        // areaid
        settingY += 70;
        areaLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(areaLabel);
        areaText.setBounds(90, settingY, 150, 30);
        settingPanel.add(areaText);
        areaSetButton.setBounds(250, settingY, 60, 30);
        areaSetButton.addActionListener(new AREASetButtonListener());
        settingPanel.add(areaSetButton);
        areaReadButton.setBounds(320, settingY, 70, 30);
        areaReadButton.addActionListener(new AREAReadButtonListener());
        settingPanel.add(areaReadButton);
        areaWaringLabel.setBounds(30, settingY + 30, 250, 30);
        areaWaringLabel.setFont(font);
        areaWaringLabel.setForeground(Color.RED);
        settingPanel.add(areaWaringLabel);

        // boxid
        settingY += 70;
        boxLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(boxLabel);
        boxText.setBounds(90, settingY, 150, 30);
        settingPanel.add(boxText);
        boxSetButton.setBounds(250, settingY, 60, 30);
        boxSetButton.addActionListener(new BOXSetButtonListener());
        settingPanel.add(boxSetButton);
        boxReadButton.setBounds(320, settingY, 70, 30);
        boxReadButton.addActionListener(new BOXReadButtonListener());
        settingPanel.add(boxReadButton);
        boxWaringLabel.setBounds(30, settingY + 30, 250, 30);
        boxWaringLabel.setFont(font);
        boxWaringLabel.setForeground(Color.RED);
        settingPanel.add(boxWaringLabel);

        // 动作间隔
        settingY += 70;
        spaceLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(spaceLabel);
        spaceText.setBounds(90, settingY, 150, 30);
        settingPanel.add(spaceText);
        spaceSetButton.setBounds(250, settingY, 60, 30);
        spaceSetButton.addActionListener(new SpaceSetButtonListener());
        settingPanel.add(spaceSetButton);
        spaceReadButton.setBounds(320, settingY, 70, 30);
        spaceReadButton.addActionListener(new SpaceReadButtonListener());
        settingPanel.add(spaceReadButton);
        spaceWaringLabel.setBounds(30, settingY + 30, 250, 30);
        spaceWaringLabel.setFont(font);
        spaceWaringLabel.setForeground(Color.RED);
        settingPanel.add(spaceWaringLabel);

        add(settingPanel,BorderLayout.WEST);

        // 设置测试面板
        testPanel.setBorder(BorderFactory.createTitledBorder("测试区"));
        // 滚动面板
        JScrollPane jsp = new JScrollPane(testArea);
        jsp.setPreferredSize(new Dimension(550, 320));
//        testArea.setPreferredSize(new Dimension(550, 250));
//        testArea.setMaximumSize(new Dimension(550, 250));
        testArea.setFont(font);
        // 显示最后一行
//        testArea.setCaretPosition(testArea.getText().length());
        //设置自己主动换行，之后则不须要设置水平滚动栏
        testArea.setLineWrap(true);
        // 设置滚动条
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // 监听
        testArea.getDocument().addDocumentListener(new testAreaListener());

        ((DefaultCaret)testArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jsp.setViewportView(testArea);
        testPanel.add(jsp);
        add(testPanel, BorderLayout.CENTER);


        // 功能区面板
        optPanel.setBorder(BorderFactory.createTitledBorder("操作区"));
        optPanel.setPreferredSize(new Dimension(800, 150));

        // 全开
        openButton.addActionListener(new OpenAllButtonListener());
        optPanel.add(openButton);
        // 全关
        closeButton.addActionListener(new CloseAllButtonListener());
        optPanel.add(closeButton);

        // 读数据
        readButton.addActionListener(new ReadButtonListener());
        optPanel.add(readButton);

        // 清空测试区
        clearButton.addActionListener(new ClearButtonListener());
        optPanel.add(clearButton);

        add(optPanel, BorderLayout.SOUTH);
    }

}
