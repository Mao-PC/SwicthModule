package com.nnlight.listeners;

import com.nnlight.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 设置动作间隔
 */
public class SpaceSetButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 动作间隔
        String speceStr = Client.spaceText.getText().trim();

        // 校验IMEI
        if (speceStr == null || speceStr.length() == 0 || !Pattern.matches("^\\d*$", speceStr) || Integer.parseInt(speceStr) < 0 || Integer.parseInt(speceStr) > 253) {
            JOptionPane.showMessageDialog(null, "动作间隔必须是 0 - 60000 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
}
