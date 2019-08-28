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
 * @Description: 设置 BOXID
 */
public class BOXSetButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // BOXID
        String boxStr = Client.boxText.getText().trim();

        // 校验IMEI
        if (boxStr == null || boxStr.length() == 0 || !Pattern.matches("^\\d*$", boxStr) || Integer.parseInt(boxStr) < 0 || Integer.parseInt(boxStr) > 253) {
            JOptionPane.showMessageDialog(null, "BOXID 必须是 1 - 253 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        CommonUtil.sendMsg(MsgEntity.BOXSETTOKEN + boxStr);
    }
}
