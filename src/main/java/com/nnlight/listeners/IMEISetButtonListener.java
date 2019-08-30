package com.nnlight.listeners;

import static com.nnlight.Client.*;

import com.nnlight.Client;
import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 设置IMEI
 */
public class IMEISetButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Client.isReadStatus = false;

        // IMEI
        String imeiStr = imeiText.getText();
        int size = 17;
        // 校验IMEI
        if (!Pattern.matches("^\\d{" + size + "}$", imeiStr)) {
            JOptionPane.showMessageDialog(null, "IMEI 必须是长度 17 的数字", "错误", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 保存IMEI
        allIMEI.add(imeiStr);

        // 自增IMEI
        imeiText.setText(CommonUtil.getIncImei(imeiStr, size));
        CommonUtil.sendStringMsg(MsgEntity.IMEISETTOKEN + "\"" + imeiStr + "\"");
    }
}
