/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.swings.luncher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JWindow;

/**
 *
 * @author umprasad
 */
public class SpinnerWin extends  JWindow{

    private Image image=null;
    
    public SpinnerWin() {
       image=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/spinner.gif"));
    }

    @Override
    public void paint(Graphics g) {
      g.drawImage(image, 0, 0,this);	 
    }
 }
