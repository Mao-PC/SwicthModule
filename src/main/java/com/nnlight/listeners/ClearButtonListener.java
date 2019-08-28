package com.nnlight.listeners;

import com.nnlight.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 清空测试数据
 */
public class ClearButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.testArea.setText("");
    }
}
