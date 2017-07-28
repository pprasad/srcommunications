/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.swings.util;

import com.srm.services.dto.ServiceConstant;
import com.srm.services.entity.CustomerDetails;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
}
