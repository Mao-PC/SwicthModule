package com.nnlight;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther: maopch
 * @Date: 2019/8/28
 * @Description:
 */
public class test {

    public static void main(String[] args) {
        JFrame myframe = new JFrame();
        myframe.setTitle("隐藏滚动条");
        myframe.setBounds(200, 200, 200, 200);
        JTextArea text=new JTextArea();

        text.setLineWrap(true);//设置自动换行，之后则不需要设置水平滚动条

        JScrollPane scroll=new JScrollPane(text);

        //分别设置水平和垂直滚动条自动出现

        scroll.setHorizontalScrollBarPolicy(

        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //scroll.setVerticalScrollBarPolicy(

        //JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);



        //分别设置水平和垂直滚动条总是出现

        //scroll.setHorizontalScrollBarPolicy(

        //JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scroll.setVerticalScrollBarPolicy(

                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //

        ////分别设置水平和垂直滚动条总是隐藏scroll.setHorizontalScrollBarPolicy(

//      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;

//        scroll.setVerticalScrollBarPolicy(
//
//                JScrollPane.VERTICAL_SCROLLBAR_NEVER);

//      Container container=mynote.getContentPane();

//      container.add(scroll,BorderLayout.EAST);



        //注意：将滚动条scoll添加到窗体后就不用再添加scoll的textarea,否则将无法显示

        myframe.add(scroll);
        myframe.setVisible(true);
    }
}
