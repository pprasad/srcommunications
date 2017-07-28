/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srm.swings.models;

import com.srm.services.entity.Category;
import com.srm.services.entity.CategoryProduct;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author umprasad
 */
public class CategoryProductModel extends AbstractTableModel {

    private String columns[] = new String[]{
        "Product Id", "Product Code", "Product Name", "Product Type"
    };
    Class[] types = new Class[]{
        java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
    private List<CategoryProduct> models = new ArrayList<CategoryProduct>();

    public int getRowCount() {
        return models.size();
    }

    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       CategoryProduct product=models.get(rowIndex);
       switch(columnIndex){
            case 0:
                product.setProductID((Long)aValue);
                break;
            case 1:
                product.setProductCode((String)aValue);
                break;
            case 2: 
                product.setProductName((String)aValue);
                break;
            case 3: 
                product.setCategory((Category) aValue);
                break;
        }
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue=null;
        CategoryProduct product=models.get(rowIndex);
        switch(columnIndex){
            case 0:
                returnValue=product.getProductID();
                break;
            case 1:
                returnValue=product.getProductCode();
                break;
            case 2: 
                returnValue=product.getProductName();
                break;
            case 3: 
                returnValue=product.getCategory();
                break;
        }
        return returnValue;
    }
    @Override
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }
    public void addRow(CategoryProduct categoryProduct){
        this.models.add(categoryProduct);
    }
}
