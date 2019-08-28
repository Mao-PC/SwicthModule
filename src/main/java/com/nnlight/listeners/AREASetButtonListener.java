package com.nnlight.listeners;

import com.nnlight.Client;
import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 设置 AREAID
 */
public class AREASetButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // AREAID
        String areaStr = Client.areaText.getText().trim();

        // 校验IMEI
        if (areaStr == null || areaStr.length() == 0 || !Pattern.matches("^\\d*$", areaStr) || Integer.parseInt(areaStr) < 0 || Integer.parseInt(areaStr) > 253) {
            JOptionPane.showMessageDialog(null, "AREAID 必须是 1 - 253 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        CommonUtil.sendMsg(MsgEntity.AREASETTOKEN + areaStr);
    }
}
