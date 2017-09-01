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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author umprasad
 */
public class BillReceiptView extends JPanel implements Printable {

    private final static Logger LOGGER = LoggerFactory.getLogger(BillReceiptView.class);

    private final Rectangle2D.Double square
            = new Rectangle2D.Double(10, 10, 478, 185);

    private PrintRequestAttributeSet attributes;

    private final static String HEADER_FONT = "Monospaced";

    private final static String HEADER_CONTANT = "SRCOMMUNICATIONS";

    private CustomerDetails customerDetails = null;

    private HeaderSettings headerSettings = null;

    public static int total_item_count = 0;

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss a";

    public static String title[] = new String[]{"Item ID", "Item Name", "Price", "Quantity", "Amount"};

    private static DecimalFormat currentyFormat = new DecimalFormat("###,###.##");
    
    private Double total=0d;
    
    public BillReceiptView() {
        init();
    }

    public BillReceiptView(CustomerDetails customerDetails) {
        init();
        this.customerDetails = customerDetails;
    }

    public BillReceiptView(CustomerDetails customerDetails, HeaderSettings headerSettings) {
        init();
        this.customerDetails = customerDetails;
        this.headerSettings = headerSettings;
    }

    public void init() {
        this.setBackground(Color.WHITE);
    }

    public String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(date);
    }

    public PageFormat getPageFormat(PrinterJob printerJob) {
        PageFormat pf = printerJob.defaultPage();
        Paper paper = pf.getPaper();
        double middleHeight = total_item_count * 1.0;  //dynamic----->change with the row count of jtable
        double headerHeight = 5.0;                  //fixed----->but can be mod
        double footerHeight = 5.0;                  //fixed----->but can be mod

        double width = convert_CM_To_PPI(28);      //printer know only point per inch.default value is 72ppi

        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        LOGGER.info("Width{}" + width);
        LOGGER.info("Height{}" + height);
        paper.setSize(width, height);
        paper.setImageableArea(
                convert_CM_To_PPI(0.25),
                convert_CM_To_PPI(0.5),
                width - convert_CM_To_PPI(0.35),
                height - convert_CM_To_PPI(1));   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected double toPPI(double inch) {
        return inch * 72d;
    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//       Graphics2D g2 = (Graphics2D) g;
//      
//        Font font = new Font("Monospaced", Font.PLAIN, 16);
//        g2.setFont(font);
//        int y = 0;
//        if (headerSettings != null) {
//            g2.drawString(headerSettings.getCompanyName(), 100, y + 20);;
//            font = new Font("Monospaced", Font.PLAIN, 15);
//            g2.setFont(font);
//            g2.drawString(headerSettings.getAddress(), 50, y + 40);
//            g2.drawString(headerSettings.getAddressSub(), 50, y + 60);
//            g2.drawString("GST No.:" + headerSettings.getRegistrationNo(), 50, y + 80);
//        }
//        /*Customer Details information*/
//        font = new Font("Monospaced", Font.PLAIN, 10);
//        g2.setFont(font);
//        g2.drawString("Bill No.     :" + customerDetails.getBillNo(), 50, y + 100);
//        g2.drawString("Date         :" + toString(customerDetails.getBillDate()), 50, y + 120);
//        g2.drawString("Customer Name:" + customerDetails.getCustomerName(), 50, y + 140);
//        /*Draw Colums*/
//        font = new Font("Monospaced", Font.PLAIN, 10);
//        g2.setFont(font);
//        int x = 10;
//        g2.drawLine(10, y + 150, 400, y + 150);
//        g2.drawString(title[0], x, y + 170);
//        g2.drawString(title[1], x + 55, y + 170);
//        g2.drawString(title[2], x + 155, y + 170);
//        g2.drawString(title[3], x + 205, y + 170);
//        g2.drawString(title[4], x + 290, y + 170);
//        g2.drawLine(10, y + 180, 400, y + 180);
//        /*items print*/
//        int cH = 0, i = 0;
//        LOGGER.info("customerDetails.getBillEntrys()"+customerDetails.getBillEntrys().size());
//        if (customerDetails.getBillEntrys() != null) {
//            total_item_count = customerDetails.getBillEntrys().size()+2;
//            for (BillEntry billEntry : customerDetails.getBillEntrys()) {
//                cH = (y +200) + (10 * i);
//                g2.drawString(billEntry.getProdCode(), x, cH);
//                g2.drawString(billEntry.getProdName(), x + 55, cH);
//                g2.drawString(currentyFormat.format(billEntry.getPrice()), x + 155, cH);
//                g2.drawString(billEntry.getQty().toString(), x + 205, cH);
//                g2.drawString(billEntry.getAmount().toString(), x + 290, cH);
//                i++;
//
//            }
//        }
//    }

    public void printReceipt() {
        // Create the PrintJob object
        attributes = new HashPrintRequestAttributeSet();
        PrinterJob pjb = PrinterJob.getPrinterJob();
        pjb.setPrintable(this);
        boolean flag=pjb.printDialog(attributes);
        try {
           if(flag){
              pjb.print();
           }
        } catch (Exception ex) {

        }
    }

    public JPanel getPanel() {
        return this;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        LOGGER.info("pageindex{}" + pageIndex);
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) graphics;
        double height = pageFormat.getImageableHeight();
        double width = pageFormat.getImageableWidth();
        g2.translate(pageFormat.getImageableX(),
                pageFormat.getImageableY());
        //this.paint(g2);
        Font font = new Font("Monospaced",Font.BOLD, 11);
        g2.setFont(font);
        int y = 0;
        if (headerSettings != null) {
            g2.drawString(headerSettings.getCompanyName(), 100, y + 20);;
            font = new Font("Monospaced", Font.PLAIN, 10);
            g2.setFont(font);
            g2.drawString(headerSettings.getAddress(), 50, y + 40);
            g2.drawString(headerSettings.getAddressSub(), 50, y + 60);
            if(!StringUtils.isEmpty(headerSettings.getRegistrationNo())){
               g2.drawString("GST No.:" + headerSettings.getRegistrationNo(), 50, y + 80);
            }
        }
        /*Customer Details information*/
        int x = 10;
        font = new Font("Monospaced", Font.PLAIN, 10);
        g2.setFont(font);
        g2.drawString("Bill No.     :" + customerDetails.getBillNo(),x, y + 100);
        g2.drawString("Date         :" + toString(customerDetails.getBillDate()),x, y + 120);
        g2.drawString("Customer Name:" + customerDetails.getCustomerName(),x, y + 140);
        /*Draw Colums*/
        font = new Font("Monospaced", Font.PLAIN, 10);
        g2.setFont(font);
        g2.drawLine(10, y + 150, 400, y + 150);
        g2.drawString(title[0], x, y + 170);
        g2.drawString(title[1], x + 55, y + 170);
        g2.drawString(title[2], x + 155, y + 170);
        g2.drawString(title[3], x + 205, y + 170);
        g2.drawString(title[4],x+280, y + 170);
        g2.drawLine(10, y + 180, 400, y + 180);
        /*items print*/
        int cH = 0, i = 0;
        LOGGER.info("customerDetails.getBillEntrys()" + customerDetails.getBillEntrys().size());
        if (customerDetails.getBillEntrys() != null) {
            total_item_count = customerDetails.getBillEntrys().size() + 2;
            for (BillEntry billEntry : customerDetails.getBillEntrys()) {
                cH = (y + 200) + (10 * i);
                g2.drawString(billEntry.getProdCode(), x, cH);
                g2.drawString(billEntry.getProdName(), x + 55, cH);
                g2.drawString(currentyFormat.format(billEntry.getPrice()), x + 155, cH);
                g2.drawString(billEntry.getQty().toString(), x + 205, cH);
                g2.drawString(billEntry.getAmount().toString(), x+280, cH);
                i++;
                total+=billEntry.getAmount();
            }
        }
        g2.drawString(total.toString(), x+280,cH+15);
        /*Footer*/
        font = new Font("Arial", Font.BOLD,8);                  //changed font size
        g2.setFont(font);
        g2.drawString("****Thank You,Please Visit Again****",95,cH+26);
        return Printable.PAGE_EXISTS;
    }
}
