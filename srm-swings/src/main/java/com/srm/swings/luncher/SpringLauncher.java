/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srm.swings.luncher;

import com.srm.swings.config.AppConfig;
import com.srm.swings.theme.SRMTheme;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author umprasad
 */
public class SpringLauncher {

    public static void main(String args[]) throws ClassNotFoundException {
        try{
            GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle maxRectangle=ge.getMaximumWindowBounds();
            MetalLookAndFeel.setCurrentTheme(new SRMTheme());
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
                 //new ClassPathXmlApplicationContext("application-config.xml")
            JDialog.setDefaultLookAndFeelDecorated(true);
            JFrame.setDefaultLookAndFeelDecorated(true);
            LoginForm loginForm = (LoginForm) context.getBean("loginForm");
            loginForm.setLocationRelativeTo(null);
            SwingUtilities.updateComponentTreeUI(loginForm);
            loginForm.show();
        }catch (Exception e){}
    }
}
