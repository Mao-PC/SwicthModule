package com.nnlight;

import com.nnlight.entity.RecordFile;
import com.nnlight.listeners.DirChooserListener;
import com.nnlight.listeners.FileChangeListener;
import com.nnlight.utils.RecordFileUtil;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @Auther: maopch
 * @Date: 2019/8/29
 * @Description: 程序启动类, 这个类中主要写了选择地址的面板
 */
public class Main {

    /**
     * 程序界面宽度
     */
    public static final int WIDTH = 800;

    /**
     * 程序界面高度
     */
    public static final int HEIGHT = 720;

    /**
     * 程序界面出现位置（横坐标）
     */
    public static final int LOC_X = 200;

    /**
     * 程序界面出现位置（纵坐标）
     */
    public static final int LOC_Y = 70;

    Color color = Color.WHITE;

    public static String cDir = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    public static String cRecordFilePath = cDir + "keep.red";

    private static Font font = new Font("微软雅黑", Font.PLAIN, 14);

    // 文件夹选择
    public static JFileChooser dirChooser = new JFileChooser();

    // 文件夹路径
    public static JTextField dirText = new JTextField();

    // 文件路径
    public static JTextField fileText = new JTextField();

    // 文件名
    public static JTextField fileNameText = new JTextField();

    // 默认的路径
    public static String absolutePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + File.separator + "十二路继电器模块配置终端" + File.separator;

    public static void main(String[] args) {
        // 主面板
        JFrame frame = new JFrame();

        frame.setBounds(LOC_X, LOC_Y, WIDTH, HEIGHT / 2);
        frame.setLayout(null);
        frame.setTitle("十二路继电器模块配置终端");
        // 设置图标
        frame.setIconImage(new ImageIcon("computer.png").getImage());

        // 画面板
        printPanel(frame);

        // 设置居中
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static void printPanel(JFrame frame) {

        RecordFile recordFile = RecordFileUtil.readRecord();

        if (recordFile == null) {

            int y = 30;
            Label dirPath = new Label("请选择生成文件的地址: ");
            dirPath.setBounds(90, y, 230, 30);
            dirPath.setFont(font);
            frame.add(dirPath);
            JButton dirButton = new JButton("浏览目录");
            dirButton.setFont(font);
            dirButton.setBounds(340, y, 100, 30);
            dirButton.addActionListener(new DirChooserListener());
            frame.add(dirButton);

            y += 40;
            dirText.setBounds(100, y, 600, 30);
            dirText.setFont(font);
            dirText.setText(absolutePath);
            dirText.setEnabled(false);
            frame.add(dirText);

            y += 60;
            Label filePath = new Label("请输入文件名: ");
            filePath.setBounds(90, y, 150, 30);
            filePath.setFont(font);
            frame.add(filePath);

            String filename = "xxx";

            fileNameText.setText(filename);
            fileNameText.setFont(font);
            fileNameText.setBounds(250, y, 350, 30);
            fileNameText.getDocument().addDocumentListener(new FileChangeListener());
            frame.add(fileNameText);

            y += 40;
            fileText.setBounds(100, y, 600, 30);
            fileText.setFont(font);
            fileText.setText(absolutePath + filename.trim() + ".xlsx");
            // 设置不可编辑
            fileText.setEnabled(false);
            frame.add(fileText);

            y += 60;
            JButton commitButton = new JButton("确认");
            commitButton.setFont(font);
            commitButton.setBounds((WIDTH - 100) / 2, y, 100, 30);
            commitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    // 保存记录
                    RecordFileUtil.saveRecord();
                    // 显示软件界面
                    Client.getClient().openClient();
                }
            });
            frame.add(commitButton);

            frame.setVisible(true);
        } else {
            frame.setVisible(false);
            // 显示软件界面
            Client.getClient().openClient();
        }
    }

}
