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
 * @Description: 设置动作间隔
 */
public class SpaceSetButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.isReadStatus = false;

        // 动作间隔
        String spaceStr = Client.spaceText.getText().trim();

        // 校验IMEI
        if (spaceStr == null || spaceStr.length() == 0 || !Pattern.matches("^\\d*$", spaceStr) || Integer.parseInt(spaceStr) < 0 || Integer.parseInt(spaceStr) > 60000) {
            JOptionPane.showMessageDialog(null, "动作间隔必须是 0 - 60000 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        CommonUtil.sendStringMsg(MsgEntity.SPACERSETTOKEN + spaceStr);
    }
}
