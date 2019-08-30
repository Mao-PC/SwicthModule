package com.nnlight.listeners;

import com.alibaba.fastjson.JSONObject;
import com.nnlight.entity.MsgEntity;
import com.nnlight.exception.ReadDataFromSerialPortFailure;
import com.nnlight.exception.SerialPortInputStreamCloseFailure;
import com.nnlight.utils.FormatUtil;
import com.nnlight.utils.SerialTool;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.nnlight.Client.*;

/**
 * 以内部类形式创建一个串口监听类
 *
 * @author zhong
 */
public class SerialListener implements SerialPortEventListener {


    private List<String> dataBytes = new ArrayList<>();

    /**
     * 处理监控到的串口事件
     */
    public void serialEvent(SerialPortEvent serialPortEvent) {
        synchronized (SerialListener.class) {
            try {
                switch (serialPortEvent.getEventType()) {

                    case SerialPortEvent.BI: // 10 通讯中断
                        JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
                        // 设置监听按钮可用
                        listenFlag = true;
                        break;

                    case SerialPortEvent.OE: // 7 溢位（溢出）错误

                    case SerialPortEvent.FE: // 9 帧错误

                    case SerialPortEvent.PE: // 8 奇偶校验错误

                    case SerialPortEvent.CD: // 6 载波检测

                    case SerialPortEvent.CTS: // 3 清除待发送数据

                    case SerialPortEvent.DSR: // 4 待发送数据准备好了

                    case SerialPortEvent.RI: // 5 振铃指示

                    case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                        // 设置监听按钮不可用
                        listenFlag = false;
                        break;

                    case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                        byte[] data = null;

                        if (serialPort == null) {
                            JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
                            // 设置监听按钮可用
                            listenFlag = true;
                        } else {
                            data = SerialTool.readFromPort(serialPort);    //读取数据，存入字节数组
                            if (data == null || data.length < 1) {    //检查数据是否读取正确
                                JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.WARNING_MESSAGE);
                                // 设置监听按钮可用
                                listenFlag = true;
                                //System.exit(0);
                            } else {
                                if (!isReadStatus) {
                                    String dataOriginal = new String(data);    //将字节数组数据转换位为保存了原始数据的字符串
                                    System.out.println("接收到数据 ： " + dataOriginal);

                                    testArea.append(dataOriginal);
                                } else {
                                    String dataOriginal = FormatUtil.bytesToHexString(data);

                                    System.out.println("接收到数据 ： " + dataOriginal);

                                    testArea.append(dataOriginal);

                                    for (String s : dataOriginal.split("\\s+")) {
                                        dataBytes.add(s);
                                    }

                                    // 检查是否结束
                                    boolean flag = true;
                                    String[] endFlagArr = MsgEntity.ENDFLAG.split(" ");
                                    int length = endFlagArr.length;
                                    for (int i = 0; i < length; i++) {
                                        if (!endFlagArr[length - 1 - i].equals(dataBytes.get(dataBytes.size() - 1 - i))) {
                                            flag = false;
                                            break;
                                        }
                                    }

                                    if (flag) {
                                        // 这里加一个换行, 否则会影响后续的数据读取
                                        testArea.append(System.lineSeparator());
                                        dataBytes.clear();
                                        isReadStatus = false;
                                    }
                                }

                                // 设置动作已结束
                                actiongFlag = false;
                                // 设置监听按钮不可用
                                listenFlag = false;
                            }
                        }

                        break;

                }
            } catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
                JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
                // 设置监听按钮可用
                listenFlag = true;
            } finally {
                listenButton.setEnabled(listenFlag);
                cancelListenButton.setEnabled(!listenFlag);
            }
        }
    }

    private boolean checkItem(String target, int size) {
        //    if (target.equals("ICCID") || target.equals("IMSI")) {
        //      return Pattern.matches("^[0-9A-Za-z]{" + size + "}$", target);
        // } else {
        return Pattern.matches("^[0-9]{" + size + "}$", target);
        //}
    }

    private boolean checkTarget(JSONObject json, String key, int length) {
        return json.get(key).toString().length() == length;
    }
}
