package com.nnlight.listeners;

import static com.nnlight.Client.*;

import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 设置IMEI
 */
public class IMEISetButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // IMEI
        String imeiStr = imeiText.getText();
        int size = 17;
        // 校验IMEI
        if (!Pattern.matches("^\\d{" + size + "}$", imeiStr)) {
            JOptionPane.showMessageDialog(null, "IMEI 必须是长度 18 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 自增IMEI
        int orderNum = Integer.parseInt(imeiStr);

        orderNum++;
        StringBuilder orderStr = new StringBuilder(orderNum + "");
        // 补全 18 位
        if (orderStr.length() < size) {
            int length = size - orderStr.length();
            for (int i = 0; i < length; i++) {
                orderStr.insert(0, 0);
            }
        }

        imeiText.setText(orderStr.toString());
        CommonUtil.sendMsg(MsgEntity.IMEISETTOKEN + "\"" + imeiStr + "\"");
    }
}
