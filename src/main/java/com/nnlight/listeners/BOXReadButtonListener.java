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
 * @Description: 读取BOXID
 */
public class BOXReadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.isReadStatus = false;

        if(CommonUtil.sendStringMsg(MsgEntity.BOXREAD)) {
            CommonUtil.refreshPanel(TextType.BOX);
        };
    }
}
