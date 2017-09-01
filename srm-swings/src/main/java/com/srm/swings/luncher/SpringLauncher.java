/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srm.swings.luncher;

import com.srm.swings.config.AppConfig;
import com.srm.swings.theme.SRMTheme;
import com.srm.swings.util.UIUtils;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author umprasad
 */
public class SpringLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLauncher.class);

    public static void main(String args[]) throws ClassNotFoundException {
        try {
            System.setProperty("sun.java2d.dpiaware", "false");
            MetalLookAndFeel.setCurrentTheme(new SRMTheme());
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SpinnerWin appStart = new SpinnerWin();
            appStart.pack();
            appStart.setSize(150, 150);
            appStart.setOpacity(0.1f);
            appStart.setLocationRelativeTo(null);
            appStart.show();
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            JDialog.setDefaultLookAndFeelDecorated(true);
            LoginForm loginForm = (LoginForm) context.getBean("loginForm");
            LOGGER.info("**********SpringLanuncher*****************");
            if (loginForm != null) {
                appStart.dispose();
                loginForm.pack();
                loginForm.setLocationRelativeTo(null);
                SwingUtilities.updateComponentTreeUI(loginForm);
                loginForm.show();
            }
        } catch (Exception e) {
        }
    }
}
