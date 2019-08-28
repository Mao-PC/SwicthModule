package com.nnlight.utils;

import com.nnlight.Client;
import com.nnlight.entity.TextType;
import com.nnlight.exception.SendDataToSerialPortFailure;
import com.nnlight.exception.SerialPortOutputStreamCloseFailure;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.nnlight.Client.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 公用方法
 */
public class CommonUtil {

    /**
     * 发送写指令
     * @param message
     * @return
     */
//    public static boolean sendWriteMsg(String message) {
//        isWriting = true;
//        boolean flag =  sendMsg(message);
//        isWriting = false;
//        return flag;
//    }

    /**
     * 发送读指令
     * @param message
     */
    public static boolean sendMsg(String message) {
        return sendMsg1(message);
    }

    private static boolean sendMsg1(String message) {
        if (listenFlag == true) {
            JOptionPane.showMessageDialog(null, "请先选择监听的串口！", "提示", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        // 补充换行
        message += System.lineSeparator();

        //获取串口名称
        String commName = (String) commChoice.getSelectedItem();
        //检查串口名称是否获取正确
        if (commName == null || commName.equals("")) {
            JOptionPane.showMessageDialog(null, "没有搜索到有效串口！", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                StringBuilder msg = new StringBuilder(message);
                // 将状态置为 已有动作进行
                actiongFlag = true;
                // 消息发送
                SerialTool.sendToPort(serialPort, msg.toString().getBytes(Charset.forName("UTF-8")));
                return true;
            } catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
                sendDataToSerialPortFailure.printStackTrace();
            } catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
                serialPortOutputStreamCloseFailure.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 刷新设置区的某个属性
     * @param type
     */
    public static void refreshPanel(TextType type) {
        if (!actiongFlag) {
            // 需要刷新的值
            String res = "";
            // 获取到最后一个数字
            String text = testArea.getText();
            String[] strings = text.split(System.lineSeparator());
            for (int length = strings.length-1; length >= 0; length--) {
                String rowStr = strings[length].trim();
                // 是否包含数字
                if (Pattern.matches(".*\\d+.*", rowStr)) {
                    for (int i = 0; i < rowStr.length(); i++) {
                        // 第一个数字的下标
                        if(rowStr.charAt(i)>=48 && rowStr.charAt(i)<=57){
                            res = rowStr.substring(i);
                            System.out.println(res);

                            switch (type) {
                                case IMEI:
                                    imeiText.setText(res);
                                    break;
                                case AREA:
                                    areaText.setText(res);
                                    break;
                                case BOX:
                                    boxText.setText(res);
                                    break;
                                case SPACE:
                                    spaceText.setText(res);
                                    break;
                            }

                            return;
                        }
                    }
                }
            }
        } else {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshPanel(type);
        }
    }
}
