package com.company;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by lion on 16/11/14.
 */

public class Myapplet extends Applet{

    public void paint(Graphics g) {
        g.drawRect(0,0,250,100);
        g.setColor(Color.blue);
        g.drawString("Look at me,I'm a Java Applets",10,50);

    }
}
