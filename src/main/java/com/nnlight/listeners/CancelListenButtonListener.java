package com.nnlight.listeners;

import com.nnlight.utils.SerialTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.nnlight.Client.*;

/**
 * @Description [取消监听串口]
 * @Author maopch
 * @Date 2019/7/26 14:09
 * @Param
 * @Return
 */
public class CancelListenButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            //获取指定端口名及波特率的串口对象
            SerialTool.closePort(serialPort);
            serialPort = null;
            //监听成功进行提示
            JOptionPane.showMessageDialog(null, "取消成功 ！", "提示", JOptionPane.INFORMATION_MESSAGE);

            // 设置监听按钮可用
            listenFlag = true;
        } catch (Exception ex) {
            listenFlag = false;
            ex.printStackTrace();
        } finally {
            listenButton.setEnabled(listenFlag);
            cancelListenButton.setEnabled(!listenFlag);
        }

    }
}