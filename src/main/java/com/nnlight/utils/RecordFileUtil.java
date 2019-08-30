package com.nnlight.utils;

import com.nnlight.Client;
import com.nnlight.entity.RecordFile;

import java.io.*;

import static com.nnlight.Main.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/29
 * @Description:
 */
public class RecordFileUtil {

    /**
     * 读取上次的记录
     *
     * @return
     */
    public static RecordFile readRecord() {
        FileInputStream in = null;
        ObjectInput inObj = null;
        RecordFile record = null;
        try {
            File file = new File(cRecordFilePath);
            if (file.exists()) {
                in = new FileInputStream(file);
                inObj = new ObjectInputStream(in);
                record = (RecordFile) inObj.readObject();

                if (record == null) {
                    return null;
                }

                String savePath = record.getSavePath();
                if (savePath == null || savePath.length() == 0) {
                    return null;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (inObj != null) inObj.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return record;
    }

    /**
     * 记录文件的生成和读取稳位置
     */
    public static void saveRecord() {

        // 记录最新的 IMEI
        FileOutputStream recordFile = null;
        ObjectOutputStream recordObject = null;

        try {
            File file = new File(cRecordFilePath);

            RecordFile record = new RecordFile();
            record.setSavePath(dirText.getText());
            record.setSaveFileName(fileNameText.getText());
            record.setFullSavePath(fileText.getText());

            Client.record = record;

            File dir = new File(cDir);
            if (!dir.exists()) dir.mkdirs();

            recordFile = new FileOutputStream(file);
            recordObject = new ObjectOutputStream(recordFile);

            recordObject.writeObject(record);

            recordObject.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (recordFile != null)
                    recordFile.close();
                if (recordObject != null)
                    recordObject.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
