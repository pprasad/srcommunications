/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.swings.theme;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 *
 * @author umprasad
 */
public class SRMTheme extends  DefaultMetalTheme{
   //javax.swing.plaf.metal.MetalLookAndFeel
   private final Integer fScrollBarWidth = new Integer(25); 
    
   public String getName() { return "SRM Theme"; }
    
   private final ColorUIResource primary1 		= new ColorUIResource(0xD9, 0xD9, 0xAA); 
    private final ColorUIResource primary2 		= new ColorUIResource(0xFF, 0xFF, 0xF0);
    private final ColorUIResource primary3 		= new ColorUIResource(0xF0, 0xF0, 0xE0);
    
    private final ColorUIResource secondary1 	= new ColorUIResource(0x6F, 0x6F, 0x6F);
    private final ColorUIResource secondary2 	= new ColorUIResource(0x9F, 0x9F, 0x9F);
    private final ColorUIResource secondary3 	= new ColorUIResource(0xDD, 0xDD, 0xCC);
    	// different fonts
    private final FontUIResource windowTitleFont = new FontUIResource("Verdana", Font.BOLD, 15);
    private final FontUIResource controlFont 	 = new FontUIResource("Verdana", Font.BOLD, 12);

		// the functions overridden from the base class => DefaultMetalTheme

    protected ColorUIResource getPrimary1() { return primary1; }  
    protected ColorUIResource getPrimary2() { return primary2; } 
    protected ColorUIResource getPrimary3() { return primary3; } 
    
    protected ColorUIResource getSecondary1() { return secondary1; }
    protected ColorUIResource getSecondary2() { return secondary2; }
    protected ColorUIResource getSecondary3() { return secondary3; }
    
    public FontUIResource getWindowTitleFont() { return windowTitleFont;}
    public FontUIResource getMenuTextFont() { return controlFont;}
    public FontUIResource getControlTextFont() { return controlFont;}

    @Override
    public void addCustomEntriesToTable(UIDefaults table) {
        table.put("nimbusLightBackground", new Color(0xFFFAFA));
        table.put("ComboBox:\"ComboBox.listRenderer\"[Selected].textForeground", new Color(0xFFFAFA));
    }
    
    

}
