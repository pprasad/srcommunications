package com.srm.services.rest.servicesImpl;

import com.srm.services.entity.BillEntry;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.services.entity.Category;
import com.srm.services.entity.CategoryProduct;
import com.srm.services.entity.CategoryStock;
import com.srm.services.entity.CustomerDetails;
import com.srm.services.entity.HeaderSettings;
import com.srm.services.entity.ProductEntry;
import com.srm.services.entity.ProductStock;
import com.srm.services.entity.StockReport;
import com.srm.services.entity.UserInfo;
import com.srm.services.entity.Users;
import com.srm.services.rest.dao.TransactionDao;
import com.srm.services.rest.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final static Logger LOGGER=LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionDao transactionDao;
	
	public List<Users> findAllUsers() {
		return transactionDao.findAllUsers();
	}

	public Boolean saveOrupdateCategory(Category category) {
		return transactionDao.saveOrupdateCategory(category);
	}

	public List<Category> getCategory() {
		return transactionDao.getCategory();
	}

	public Boolean deleteCategory(Long Id) {
		Category category=transactionDao.findCategoryById(Id);
		if(category!=null){
			return transactionDao.deleteCategory(category);
		}else{
			return Boolean.FALSE;
		}
	}

    public Category save(Category category) {
        return transactionDao.save(category);
    }

    public CategoryProduct saveCategoryProd(CategoryProduct product) {
         return transactionDao.saveCategoryProd(product);
    }

    public List<CategoryProduct> getCategoryProducts() {
       return transactionDao.getCategoryProducts();
    }

    public Boolean updateCategoryProduct(CategoryProduct product) {
       return transactionDao.updateCategoryProduct(product);
    }

    public Boolean deleteCategoryProduct(Long Id) {
        return transactionDao.deleteCategoryProduct(Id);
    }

    public ProductEntry saveProductEntry(ProductEntry productEntry) {
        boolean flag=false;
        productEntry=transactionDao.saveProductEntry(productEntry);
        if(productEntry.getId()!=null){
            CategoryProduct product=productEntry.getCategoryProduct();
            ProductStock stock=findProdStockByCode(product.getProductCode());
            if(stock!=null){
                stock.setQuantity(stock.getQuantity()+productEntry.getQuantity());
                stock.setPrice(productEntry.getSalesPrice());
            }else{
                stock=new ProductStock();
                stock.setProductCode(product.getProductCode());
                stock.setQuantity(productEntry.getQuantity());
                stock.setPrice(productEntry.getSalesPrice());
            }
            flag=transactionDao.saveProductStock(stock);
        }
        return productEntry;
    }

    public List<ProductEntry> findAllProductEntries() {
        return transactionDao.findAllProductEntries();
    }

    public Boolean updateProductEntry(ProductEntry productEntry) {
       boolean flag=false;
       ProductEntry oldEnty=transactionDao.findProductEntryById(productEntry.getId());
       if(transactionDao.updateProductEntry(productEntry)){
            if(productEntry!=null){
                CategoryProduct product=productEntry.getCategoryProduct();
                ProductStock stock=findProdStockByCode(product.getProductCode());
                if(stock!=null){
                    Integer qty=stock.getQuantity()-oldEnty.getQuantity();
                    qty+=productEntry.getQuantity();
                    stock.setPrice(productEntry.getSalesPrice());
                    flag=transactionDao.saveProductStock(stock);
                }else{
                    flag=true;
                }
            }
        }
       return flag;
    }

    public Boolean deleteProductEntry(Long Id) {
        boolean flag=false;
        ProductEntry productEntry=transactionDao.findProductEntryById(Id);
        if(transactionDao.deleteProductEntry(Id)){
            if(productEntry!=null){
                CategoryProduct product=productEntry.getCategoryProduct();
                ProductStock stock=findProdStockByCode(product.getProductCode());
                if(stock!=null){
                    stock.setQuantity(stock.getQuantity()-productEntry.getQuantity());
                    stock.setPrice(productEntry.getSalesPrice());
                    flag=transactionDao.saveProductStock(stock);
                }else{
                    flag=true;
                }
            }
        }
        return flag;
    }

    public ProductStock findProdStockByCode(String prodCode) {
       return  transactionDao.findProdStockByCode(prodCode);
    }

    public Boolean saveProductStock(String prodCode, Integer quantity) {
        return transactionDao.saveProductStock(prodCode, quantity);
    }

    public Boolean saveProductStock(ProductStock productStock) {
       return transactionDao.saveProductStock(productStock);
    }

    public CategoryProduct findCategoryProdById(Long Id) {
        return transactionDao.findCategoryProdById(Id);
    }

    public List<String> findAllProuctCodes() {
        return transactionDao.findAllProuctCodes();
    }

    public CategoryStock findProdNameAndPriceByProdCode(String prodCode) {
       return transactionDao.findProdNameAndPriceByProdCode(prodCode);
    }

    public CustomerDetails saveCutomerDetails(CustomerDetails customerDetails) {
       return transactionDao.saveCutomerDetails(customerDetails);
    }

    public BillEntry saveBillDetails(BillEntry billEntry) {
       ProductStock stock=findProdStockByCode(billEntry.getProdCode());
       stock.setQuantity(stock.getQuantity()-billEntry.getQty());
       if(transactionDao.saveProductStock(stock)){
              transactionDao.saveBillDetails(billEntry);
       }
       return billEntry;
    }

    public Boolean deleteBillDetails(Long Id) {
        BillEntry billEntry=transactionDao.findBillDetailsById(Id);
        if(billEntry!=null){
            ProductStock stock=findProdStockByCode(billEntry.getProdCode());
            stock.setQuantity(stock.getQuantity()+billEntry.getQty());
            if(transactionDao.deleteBillDetails(Id)){
               transactionDao.saveProductStock(stock);
            }
            return true;
        }
        return false;
    }

    public List<CustomerDetails> findAllCustomerDetails() {
       return transactionDao.findAllCustomerDetails();
    }

    public CustomerDetails findCustomerByBillId(Long Id) {
       return transactionDao.findCustomerByBillId(Id);
    }

    public List<BillEntry> findBillDetailsByBillId(Long Id) {
       return transactionDao.findBillDetailsByBillId(Id);
    }

    public Boolean updateCustomerDetails(CustomerDetails customerDetails) {
        return transactionDao.updateCustomerDetails(customerDetails);
    }

    public HeaderSettings saveHeaderSetting(HeaderSettings headerSetting) {
        return transactionDao.saveHeaderSetting(headerSetting);
    }

    public HeaderSettings findHeaderSettingByUse(String useFlag) {
        return transactionDao.findHeaderSettingByUse(useFlag);
    }

    public UserInfo findUserByLoginId(String loginId) {
        return transactionDao.findUserByLoginId(loginId);
    }

    public Boolean updateHeaderSetting(HeaderSettings headerSetting) {
        return transactionDao.updateHeaderSetting(headerSetting);
    }

    public UserInfo saveUserDetails(UserInfo userInfo) {
        return transactionDao.saveUserDetails(userInfo);
    }

    public Boolean updateUserDetails(UserInfo userInfo) {
        return transactionDao.updateUserDetails(userInfo);
    }

    public Boolean deleteUserDetails(Long id) {
       return transactionDao.deleteUserDetails(id);
    }

    public UserInfo findUserDetailsByLoginId(String loginId) {
        return transactionDao.findUserByLoginId(loginId);
    }

    public UserInfo findUserDetailsById(Long id) {
       return transactionDao.findUserDetailsById(id);
    }

    public List<UserInfo> findAllUserDetails() {
       return transactionDao.findAllUserDetails();
    }

    public List<StockReport> findAllStocks() {
        return transactionDao.findAllStocks();
    }

}
