package com.nnlight.listeners;

import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description:
 */
public class ReadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String  msg = "68 00 00 00 00 00 02 68 FE FE 00 00 08 06 07 07 02 05 00 03 05 00 00 02 00 03 03 D1 04 0F FF 0F FF F2 16 FE FD FC FB FA F9";
        String[] msgs = msg.split(" ");
        byte[] command = new byte[msgs.length];
        for (int i = 0; i < msgs.length; i++) {
            command[i] = (byte)Integer.parseInt(msgs[i], 16);
        }
        CommonUtil.sendMsg(MsgEntity.READSTATUS);
    }
}
