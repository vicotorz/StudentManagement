package com.demo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Histogram extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] count;
    public void showHistogram(int[] count)
    {
        this.count = count;
        repaint();
    }
    protected void paintComponent(Graphics g)
    {
        if (null == count)
        {
            return;
        }
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int interval = (width - 40) / count.length;
        int individualWidth = (int)(((width - 40) / 24) * 0.60);
        int maxCount = 0;
        for (int i = 0 ; i < count.length ; i++)
        {
            if (maxCount < count[i])
            {
                maxCount = count[i];
            }
        }
        int x = 30;
        g.setColor(Color.red);
        g.drawLine(10 , height - 45 , width - 10 , height - 45);
        for (int i = 0 ; i < count.length ; i++)
        {
            int barHeight = (int)(((double )count[i] / (double)maxCount) * (height - 55));
            g.drawRect(x , height - 45 - barHeight , individualWidth , barHeight);
            g.setColor(Color.red);
            g.drawString((char)(65 + i) + " " , x , height - 30);
            x += interval;
        }
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(300 , 300);
    }
}
