package com.nnlight.listeners;

import com.nnlight.exception.*;
import com.nnlight.utils.SerialTool;
import com.nnlight.view.ViewLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import static com.nnlight.Client.*;

/**
 * @Description [监听串口]
 * @Author maopch
 * @Date 2019/7/26 14:09
 * @Param
 * @Return
 */
public class ListenButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {

        //获取串口名称
        String commName = (String) commChoice.getSelectedItem();

//        if (!PubMethod.isEmpty(serialPort) && PubMethod.handSting(serialPort.getName()).equals(PubMethod.handSting(commName))) {
//            JOptionPane.showMessageDialog(null, "已监听此端口！", "错误", JOptionPane.ERROR_MESSAGE);
//        } else {
        try {
            //获取指定端口名及波特率的串口对象
            serialPort = SerialTool.openPort(commName, bps);
            //在该串口对象上添加监听器
            SerialTool.addListener(serialPort, new SerialListener());
            //监听成功进行提示
            JOptionPane.showMessageDialog(null, "监听成功 ！", "提示", JOptionPane.INFORMATION_MESSAGE);

            // 设置不可用
            listenFlag = false;

            // 每秒检查确认按钮是否可用, 如果不可用就设置为可用
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    if (!okbutton.isEnabled() && new Date().getTime() - okLock > 1000 * 20) {
//                        okbutton.setEnabled(true);
//                        JOptionPane.showMessageDialog(null, "无返回数据, 请检查仪器 ！", "错误", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            }, 0, 1000);

        } catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse | TooManyListeners e) {
            JOptionPane.showMessageDialog(null, e.toString(), "警告", JOptionPane.WARNING_MESSAGE);
            listenFlag = true;
            e.printStackTrace();
        } finally {
            listenButton.setEnabled(listenFlag);
            ViewLayout.getViewLayout().setComponentEnabled(!listenFlag);
            cancelListenButton.setEnabled(!listenFlag);
        }
//        }
    }
}