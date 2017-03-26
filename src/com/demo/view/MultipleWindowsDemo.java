package com.demo.view;

//使用直方图

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MultipleWindowsDemo extends JFrame implements ActionListener
{
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
       private JButton jbtnShowHistogram = new JButton("显示直方图");
       private Histogram histogram = new Histogram();
       private JFrame histogramFrame = new JFrame();
       public MultipleWindowsDemo()
       {
           JScrollPane scrollPane = new JScrollPane(jta = new JTextArea());
           scrollPane.setPreferredSize(new Dimension(300 , 200));
           jta.setWrapStyleWord(true);
           jta.setLineWrap(true);
           getContentPane().add(scrollPane , BorderLayout.CENTER);
           getContentPane().add(jbtnShowHistogram , BorderLayout.SOUTH);
           jbtnShowHistogram.addActionListener(this);
           histogramFrame.getContentPane().add(histogram);
           histogramFrame.pack();
           histogramFrame.setTitle("直方图");
       }
       private int[] countLetters()
       {
           int[] count = new int[26];
           String text = jta.getText();
           for (int i = 0 ; i < text.length() ; i++)
           {
               char character = text.charAt(i);
               if ((character >= 'A') && (character <= 'Z'))
               {
                   count[(int)(character - 65)]++;
               }
               else if ((character >= 'a') && (character <= 'z'))
               {
                   count[(int)(character - 97)]++;
               }
           }
           return count;
       }   
       public void actionPerformed(ActionEvent e)
       {
           int[] count = countLetters();
           histogram.showHistogram(count);
           histogramFrame.setVisible(true);
       }
    public static void main(String[] args)
    {
        // TODO, add your application code
        MultipleWindowsDemo frame = new MultipleWindowsDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("直方图");
        frame.pack();
        frame.setVisible(true);
    }
}
