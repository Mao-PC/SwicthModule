package com.nnlight.listeners;

import com.nnlight.Client;
import com.nnlight.entity.MsgEntity;
import com.nnlight.utils.CommonUtil;
import com.nnlight.utils.FormatUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 读状态
 */
public class ReadStatusButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        // 先读取IMEI然后根据IMEI查询状态
        // 发送读取 IMEI 的命令
        CommonUtil.sendStringMsg(MsgEntity.IMEIREAD);

        String imeiStr = CommonUtil.getTargetString();

        String hexIMEI = strTo16(imeiStr);

        String readComStr = MsgEntity.READSTATUSHEAD + " " + hexIMEI + MsgEntity.READSTATISDATA;

        String checkeNum = check(readComStr);

        // 拼接最后的读取指令
        String[] msgs = (readComStr + " " + checkeNum + " " + MsgEntity.ENDFLAG).split("\\s+");
        byte[] command = new byte[msgs.length];
        for (int i = 0; i < msgs.length; i++) {
            command[i] = (byte)Integer.parseInt(msgs[i], 16);
        }
        Client.isReadStatus = true;
        CommonUtil.sendBetyMsg(command);
    }

    private static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch - 48);
            str = str + s4 + " ";
        }
        return str;
    }

    public static String check(String str) {
        String[] strArr = str.split("\\s+");

        int res = 0;

        for (int i = 0; i < strArr.length; i++) {
            res += Integer.parseInt(strArr[i], 16);
        }
        String hexString = Integer.toHexString(res);
        System.out.println(hexString);

        return hexString.substring(hexString.length()-2).toUpperCase();
    }
}
