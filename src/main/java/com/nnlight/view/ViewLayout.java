package com.nnlight.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
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


    private Color backgroupColor = Color.WHITE;

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
        listenPanel.setBackground(backgroupColor);
//        listenPanel.setPreferredSize(new Dimension(700, 70));
        add(listenPanel, BorderLayout.NORTH);

        listenPanel.add(serialPortSelectLabel);
        portComboBox.setPreferredSize(new Dimension(100, 30));
        listenPanel.add(portComboBox);
        listenPanel.add(listenButton);
        listenPanel.add(cancelListenButton);

        // 设置设置面板
        settingPanel.setBackground(backgroupColor);
        settingPanel.setPreferredSize(new Dimension());
        settingPanel.setBorder(BorderFactory.createTitledBorder("设置区"));
        settingPanel.setPreferredSize(new Dimension(350, 400));

        // 设置面板使用网格布局
        settingPanel.setLayout(null);
        // 设置IMEI
        int settingY = 30;
        imeiLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(imeiLabel);
        imeiText.setBounds(90, settingY, 100, 30);
        settingPanel.add(imeiText);
        imeiSetButton.setBounds(200, settingY, 50, 30);
        settingPanel.add(imeiSetButton);
        imeiReadButton.setBounds(260, settingY, 70, 30);
        settingPanel.add(imeiReadButton);

        // areaid
        settingY += 40;
        areaLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(areaLabel);
        areaText.setBounds(90, settingY, 100, 30);
        settingPanel.add(areaText);
        areaSetButton.setBounds(200, settingY, 50, 30);
        settingPanel.add(areaSetButton);
        areaReadButton.setBounds(260, settingY, 70, 30);
        settingPanel.add(areaReadButton);

        // boxid
        settingY += 40;
        boxLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(boxLabel);
        boxText.setBounds(90, settingY, 100, 30);
        settingPanel.add(boxText);
        boxSetButton.setBounds(200, settingY, 50, 30);
        settingPanel.add(boxSetButton);
        boxReadButton.setBounds(260, settingY, 70, 30);
        settingPanel.add(boxReadButton);

        // 动作间隔
        settingY += 40;
        spaceLabel.setBounds(30, settingY, 60, 30);
        settingPanel.add(spaceLabel);
        spaceText.setBounds(90, settingY, 100, 30);
        settingPanel.add(spaceText);
        spaceSetButton.setBounds(200, settingY, 50, 30);
        settingPanel.add(spaceSetButton);
        spaceReadButton.setBounds(260, settingY, 70, 30);
        settingPanel.add(spaceReadButton);

        add(settingPanel,BorderLayout.WEST);

        // 设置测试面板
        testPanel.setBackground(backgroupColor);
        testPanel.setBorder(BorderFactory.createTitledBorder("测试区"));
        add(testPanel, BorderLayout.CENTER);

        // 功能区面板
        optPanel.setBackground(backgroupColor);
        optPanel.setBorder(BorderFactory.createTitledBorder("操作区"));
        optPanel.setPreferredSize(new Dimension(800, 100));

        // 全开
        optPanel.add(openButton);
        // 全关
        optPanel.add(closeButton);
        // 读数据
        optPanel.add(readButton);

        add(optPanel, BorderLayout.SOUTH);
    }

}
