package com.nnlight.listeners;

import com.nnlight.entity.MsgEntity;
import com.nnlight.entity.TextType;
import com.nnlight.utils.CommonUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 读取动作间隔
 */
public class SpaceReadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (CommonUtil.sendMsg(MsgEntity.SPACEREAD)) {
            CommonUtil.refreshPanel(TextType.SPACE);
        };
    }
}
