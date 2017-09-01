/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.swings.util;

import com.srm.services.dto.ServiceConstant;
import com.srm.services.entity.CustomerDetails;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.JTableHeader;

/**
 *
 * @author umprasad
 */
public class UIUtils {
    public static void dialogBox(Integer action,Component component){
       if(ServiceConstant.ACTION_SAVE.equals(action)){
         JOptionPane.showMessageDialog(component, ServiceConstant.SUCCESS_MSG, ServiceConstant.RESULT_SUCCESS,
                        JOptionPane.INFORMATION_MESSAGE);
       }else if(ServiceConstant.ACTION_UPDATE.equals(action)){
         JOptionPane.showMessageDialog(component, ServiceConstant.UPDATE_MSG, ServiceConstant.RESULT_SUCCESS,
                        JOptionPane.INFORMATION_MESSAGE);   
       }else if(ServiceConstant.ACTION_DELETE.equals(action)){
          JOptionPane.showMessageDialog(component, ServiceConstant.DETLE_MSG, ServiceConstant.RESULT_SUCCESS,
                        JOptionPane.INFORMATION_MESSAGE);   
       }else if(ServiceConstant.ACTION_LOGIN_FAILURE.equals(action)){
           JOptionPane.showMessageDialog(component, ServiceConstant.LOGIN_FAILURE_MSG, ServiceConstant.RESULT_FAIURE,
                        JOptionPane.WARNING_MESSAGE); 
       }
    }
    public static void clearFields(java.awt.Component component) {
        if (component instanceof JPanel) {
            for (java.awt.Component comp : ((JPanel) component).getComponents()) {
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setText("");
                } else if (comp instanceof JComboBox) {
                    ((JComboBox) comp).setSelectedIndex(0);
                } else if(comp instanceof JCheckBox){
                    ((JCheckBox)comp).setSelected(false);
                }
            }
        }
    }
    public static void setTableSettings(JTable jTable) {
       Font font =new FontUIResource("Times New Roman", Font.PLAIN,28);
       JTableHeader header =jTable.getTableHeader();
       header.setFont(font);
    }
    public static Integer getWidth(){
        return 702;
    }
     public static Integer getHeight(){
        return 436;
    }
    public static Dimension getScreensize(){
         Toolkit toolkit=Toolkit.getDefaultToolkit();
         Dimension dimension=toolkit.getScreenSize();
         dimension.setSize(2560,1440);
         return dimension;
    } 
  
     
}
