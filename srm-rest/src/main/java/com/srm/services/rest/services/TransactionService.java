package com.srm.services.rest.services;

import com.srm.services.entity.BillEntry;
import java.util.List;

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

public interface TransactionService {

    public List<Users> findAllUsers();

    public Boolean saveOrupdateCategory(Category category);

    public Category save(Category category);

    public List<Category> getCategory();

    public Boolean deleteCategory(Long Id);

    public CategoryProduct saveCategoryProd(CategoryProduct product);

    public List<CategoryProduct> getCategoryProducts();

    public Boolean updateCategoryProduct(CategoryProduct product);

    public Boolean deleteCategoryProduct(Long Id);

    public ProductEntry saveProductEntry(ProductEntry productEntry);

    public List<ProductEntry> findAllProductEntries();

    public Boolean updateProductEntry(ProductEntry productEntry);

    public Boolean deleteProductEntry(Long Id);

    public ProductStock findProdStockByCode(String prodCode);

    public Boolean saveProductStock(String prodCode, Integer quantity);

    public Boolean saveProductStock(ProductStock productStock);

    public CategoryProduct findCategoryProdById(Long Id);

    public List<String> findAllProuctCodes();

    public CategoryStock findProdNameAndPriceByProdCode(String prodCode);

    public CustomerDetails saveCutomerDetails(CustomerDetails customerDetails);

    public BillEntry saveBillDetails(BillEntry billEntry);

    public Boolean deleteBillDetails(Long Id);

    public List<CustomerDetails> findAllCustomerDetails();

    public CustomerDetails findCustomerByBillId(Long Id);

    public List<BillEntry> findBillDetailsByBillId(Long Id);

    public Boolean updateCustomerDetails(CustomerDetails customerDetails);

    public HeaderSettings saveHeaderSetting(HeaderSettings headerSetting);

    public HeaderSettings findHeaderSettingByUse(String useFlag);

    public UserInfo findUserByLoginId(String loginId);

    public Boolean updateHeaderSetting(HeaderSettings headerSetting);

    public UserInfo saveUserDetails(UserInfo userInfo);

    public Boolean updateUserDetails(UserInfo userInfo);

    public Boolean deleteUserDetails(Long id);

    public UserInfo findUserDetailsByLoginId(String loginId);

    public UserInfo findUserDetailsById(Long id);

    public List<UserInfo> findAllUserDetails();
    
     public List<StockReport> findAllStocks();
}
