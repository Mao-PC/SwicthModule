package com.nnlight.entity;

import java.io.Serializable;

/**
 * @Auther: maopch
 * @Date: 2019/8/12
 * @Description: 当前已经打印的最新的记录, 保存路径
 */
public class RecordFile implements Serializable {

    private String savePath;
    private String saveFileName;
    private String fullSavePath;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getFullSavePath() {
        return fullSavePath;
    }

    public void setFullSavePath(String fullSavePath) {
        this.fullSavePath = fullSavePath;
    }
}
