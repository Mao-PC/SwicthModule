package com.nnlight.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static com.nnlight.Main.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/15
 * @Description: 文件名修改监听
 */
public class FileChangeListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateText();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateText();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

    private void updateText() {
        String filename = fileNameText.getText();
        fileText.setText(absolutePath + filename);
    }
}
