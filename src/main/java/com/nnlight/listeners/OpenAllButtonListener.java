package com.nnlight.listeners;

import com.nnlight.Client;
import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 全开按钮监听
 */
public class OpenAllButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.isReadStatus = false;

        String[] msgs = MsgEntity.OPENALL.split(" ");
        byte[] command = new byte[msgs.length];
        for (int i = 0; i < msgs.length; i++) {
            command[i] = (byte)Integer.parseInt(msgs[i], 16);
        }
        CommonUtil.sendBetyMsg(command);
    }
}
