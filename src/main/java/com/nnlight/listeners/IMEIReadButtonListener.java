package com.nnlight.listeners;

import com.nnlight.Client;
import com.nnlight.entity.MsgEntity;
import com.nnlight.entity.TextType;
import com.nnlight.utils.CommonUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 读取IMEI
 */
public class IMEIReadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.isReadStatus = false;

        if (CommonUtil.sendStringMsg(MsgEntity.IMEIREAD)) {
            CommonUtil.refreshPanel(TextType.IMEI);
        };
    }
}
