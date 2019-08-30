package com.nnlight.entity;

import com.nnlight.utils.FormatUtil;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description: 发送消息的工具类
 */
public class MsgEntity {
    public static final String TOKEN = "AT+";

    // 读IMEI
    public static final String IMEIREAD = TOKEN + "UUID";

    // 写 IMEI 前缀
    public static final String IMEISETTOKEN = TOKEN + "UUID=";

    // 读 AREAID
    public static final String AREAREAD = TOKEN + "AREA";

    // 写 AREAID 前缀
    public static final String AREASETTOKEN = TOKEN + "AREA=";

    // 读 BOXID
    public static final String BOXREAD = TOKEN + "BOX";

    // 写 BOXID 前缀
    public static final String BOXSETTOKEN = TOKEN + "BOX=";

    // 读 动作间隔
    public static final String SPACEREAD = TOKEN + "ACTIONINTERVAL";

    // 写 动作间隔 前缀
    public static final String SPACERSETTOKEN = TOKEN + "ACTIONINTERVAL=";

    // 读状态
    public static final String READSTATUSHEAD = "68 00 00 00 00 00 02 68 FF FF";

    // 读状态 数据域
    public static final String READSTATISDATA = "D0 00";

    // 全开
    public static final String OPENALL = "68 00 00 00 00 00 02 68 FE FE 00 00 08 06 07 07 02 05 00 03 05 00 00 02 00 03 03 D1 04 0F FF 0F FF F2 16 FE FD FC FB FA F9";

    // 全关
    public static final String CLOSEALL = "68 00 00 00 00 00 02 68 FE FE 00 00 08 06 07 07 02 05 00 03 05 00 00 02 00 03 03 D1 04 0F FF 00 00 E4 16 FE FD FC FB FA F9";

    // 结束标志
    public static final String ENDFLAG = "16 FE FD FC FB FA F9";

}
