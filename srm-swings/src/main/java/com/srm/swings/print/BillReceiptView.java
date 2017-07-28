/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srm.swings.print;

import com.srm.services.entity.BillEntry;
import com.srm.services.entity.CustomerDetails;
import com.srm.services.entity.HeaderSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JPanel;

/**
 *
 * @author umprasad
 */
public class BillReceiptView extends JPanel implements Printable {

    private final Rectangle2D.Double square
            = new Rectangle2D.Double(10, 10, 478, 185);

    private PrintRequestAttributeSet attributes;

    private final static String HEADER_FONT = "Monospaced";
    
    private final static String HEADER_CONTANT="SRCOMMUNICATIONS";
    
    private CustomerDetails customerDetails=null;
    
    private HeaderSettings headerSettings=null;
    
    private  static String title[] = new String[] {"Item ID","Item Name","Price","Quantity","Amount"};
    
    private Integer total_item_count=0;

    public BillReceiptView() {
       init();
    }

    public BillReceiptView(CustomerDetails customerDetails) {
        init();
        this.customerDetails=customerDetails;
    }
    public BillReceiptView(CustomerDetails customerDetails,HeaderSettings headerSettings) {
        init();
        this.customerDetails=customerDetails;
        this.headerSettings=headerSettings;
    }
    public void init(){
        this.setBackground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //Draw Header Part
        int y=30;
        if(headerSettings!=null){
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,25));y+=10;
            g2d.drawString(headerSettings.getCompanyName(),300,y);y+=20;
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,18));
            g2d.drawString(headerSettings.getAddress()+","+headerSettings.getAddressSub(),250,y);y+=20;
            g2d.drawString("Contact No."+headerSettings.getContactNo(),300,y);y+=20;
        }
        if(customerDetails!=null){
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,16));
            g2d.drawString("Date:",30,y+30);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.PLAIN,16));
            g2d.drawString(customerDetails.getBillDate().toString(),90,y+30);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,16));
            g2d.drawString("Bill No.:",600,y+30);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.PLAIN,16));
            g2d.drawString(customerDetails.getBillNo().toString(),700,y+30);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,16));
            g2d.drawString("Customer Name:",30,y+60);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.PLAIN,16));
            g2d.drawString(customerDetails.getCustomerName(),200,y+60);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.PLAIN,16));
            g2d.drawString(customerDetails.getBillNo().toString(),700,y+30);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.BOLD,16));
            g2d.drawString("Contact No.:",30,y+90);
            g2d.setFont(new java.awt.Font(HEADER_FONT,Font.PLAIN,16));
            g2d.drawString(customerDetails.getCustomerContact(),200,y+90);
            g2d.drawLine(30,y+120,700,y+120);
            /*Item Information*/
            g2d.drawString(title[0],30,y+140);
            g2d.drawString(title[1],150,y+140);
            g2d.drawString(title[2],350,y+140);
            g2d.drawString(title[3],450,y+140);
            g2d.drawString(title[4],550,y+140);
            g2d.drawLine(30,y+160,700,y+160);
            int cH=0;int i=0;Double total=0d;
            if(customerDetails.getBillEntrys()!=null){
                total_item_count=customerDetails.getBillEntrys().size()+2;
                for(BillEntry billEntry:customerDetails.getBillEntrys()){
                    cH = (y+180) + (20*i);
                    g2d.drawString(billEntry.getProdCode(),30,cH);
	            g2d.drawString(billEntry.getProdName(),150,cH);
	            g2d.drawString(billEntry.getPrice().toString(),350,cH);
                    g2d.drawString(billEntry.getQty().toString(),450,cH);
                    g2d.drawString(billEntry.getAmount().toString(),550,cH);
                    total+=billEntry.getAmount();
                    i++;
                }
                g2d.drawLine(30,cH+30,700,cH+30);
                g2d.drawString(total.toString(),550,cH+50);
            }
            /*Footer*/
	    Font font = new Font("Arial",Font.BOLD,16) ;                  //changed font size
	    g2d.setFont(font);
            g2d.drawString("Thank You Come Again",300,cH+100);
        }
        
        //g2d.draw(square);
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) graphics;
        double height = pageFormat.getImageableHeight();
        double width = pageFormat.getImageableWidth();
        g2.translate(pageFormat.getImageableX(),
                pageFormat.getImageableY());
        //g2.translate(0f, 0f);
        this.print(g2);
        return Printable.PAGE_EXISTS;
    }

    public PageFormat getPageFormat(PrinterJob printerJob){
        PageFormat pf=printerJob.defaultPage();
        Paper paper=pf.getPaper();
        double middleHeight=total_item_count*1.0;
        double headerHeight = 20.0;                  
        double footerHeight = 30.0;
        double width=convertCMToPPI(7d);
        double height=convertCMToPPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(convertCMToPPI(0.25d),convertCMToPPI(0.5d),width-convertCMToPPI(0.35d), height-convertCMToPPI(1d));
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }
    public Double convertCMToPPI(Double cm){
        return toPPI(cm* 0.393600787);
    }
    public Double toPPI(Double inch){
        return inch *350d;
    }
    public void printReceipt() {
        // Create the PrintJob object
        attributes = new HashPrintRequestAttributeSet();
        PrinterJob pjb = PrinterJob.getPrinterJob();
        pjb.setPrintable(this,getPageFormat(pjb));
        try{
             pjb.print();
        }catch(Exception ex) {

        }
    }

    public JPanel getPanel() {
        return this;
    }
}
