package com.nnlight.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.nnlight.Main.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/15
 * @Description: 文件夹选择
 */
public class DirChooserListener extends JFrame implements ActionListener {


    // 文件夹选择
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        if (file != null) {
            absolutePath = file.getAbsolutePath() + File.separator;
            System.out.println(jfc.getSelectedFile().getName());

            dirText.setText(absolutePath);
            fileText.setText(absolutePath + fileNameText.getText() + ".xlsx");
        } else {
            System.out.println("未选择路径");
        }
    }

}
