package com.nnlight;

import com.nnlight.utils.FormatUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description:
 */
public class test {

    public static void main(String[] args) {
        System.out.println(check("68 00 00 00 00 00 02 68 FE FE 00 00 08 06 07 07 02 05 00 03 05 00 00 02 00 03 03 D1 04 0F FF 0F FF "));
    }

    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch - 48);
            str = str + s4;
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

    public static int getHeight4(byte data){//获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    public static int getLow4(byte data){//获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }
}
