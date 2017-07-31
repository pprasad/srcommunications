package com.srm.services.rest.daoImpl;

import com.srm.services.dto.ServiceConstant;
import com.srm.services.entity.BillEntry;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srm.services.entity.Category;
import com.srm.services.entity.CategoryProduct;
import com.srm.services.entity.CategoryStock;
import com.srm.services.entity.CustomerDetails;
import com.srm.services.entity.HeaderSettings;
import com.srm.services.entity.ProductEntry;
import com.srm.services.entity.ProductStock;
import com.srm.services.entity.UserInfo;
import com.srm.services.entity.Users;
import com.srm.services.rest.dao.TransactionDao;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public List<Users> findAllUsers() {
        Session session = null;
        List<Users> users = null;
        try {
            LOGGER.info("sessionFactory{}" + sessionFactory);
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM Users O");
            users = query.list();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users != null ? users : new ArrayList<Users>();
    }

    public Boolean saveOrupdateCategory(Category category) {
        Session session = null;
        Transaction transaction = null;
        Boolean flag = Boolean.FALSE;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if (category.getCateId() == null) {
                session.save(category);
            } else {
                Category cat = findCategoryById(category.getCateId());
                cat.setCateName(category.getCateName());
                session.update(cat);
            }
            transaction.commit();
            flag = Boolean.TRUE;
        } catch (Exception ex) {
            flag = Boolean.FALSE;
            transaction.rollback();
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return flag;
    }

    public List<Category> getCategory() {
        List<Category> categories = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM Category O ORDER BY O.cateId");
            categories = (List<Category>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return categories != null ? categories : new ArrayList<Category>();
    }

    public Boolean deleteCategory(Category category) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            LOGGER.error(ex.getMessage(), ex);
            return Boolean.FALSE;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Boolean.TRUE;
    }

    public Category findCategoryById(Long Id) {
        Session session = null;
        Category category = null;
        try {
            session = sessionFactory.openSession();
            category = (Category) session.get(Category.class, Id);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return category;
    }

    public Category save(Category category) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return category;
    }

    public CategoryProduct saveCategoryProd(CategoryProduct product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return product;
    }

    public List<CategoryProduct> getCategoryProducts() {
        List<CategoryProduct> categoryProducts = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM CategoryProduct O ORDER BY O.productID");
            categoryProducts = (List<CategoryProduct>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return categoryProducts != null ? categoryProducts : new ArrayList<CategoryProduct>();
    }

    public Boolean updateCategoryProduct(CategoryProduct product) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.update(product);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error(ex.getMessage(), ex);
            return Boolean.FALSE;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Boolean.TRUE;
    }

    public Boolean deleteCategoryProduct(Long Id) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            CategoryProduct categoryProduct = (CategoryProduct) session.get(CategoryProduct.class, Id);
            if (categoryProduct != null) {
                session.delete(categoryProduct);
            }
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public ProductEntry saveProductEntry(ProductEntry productEntry) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(productEntry);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        }
        return productEntry;
    }

    public List<ProductEntry> findAllProductEntries() {
        Session session = null;
        List<ProductEntry> productEntrys = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM ProductEntry O ORDER BY O.Id");
            productEntrys = query.list();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return productEntrys != null ? productEntrys : new ArrayList<ProductEntry>();
    }

    public Boolean updateProductEntry(ProductEntry productEntry) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.update(productEntry);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exceptions", ex);
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Boolean deleteProductEntry(Long Id) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            ProductEntry productEntry = (ProductEntry) session.get(ProductEntry.class, Id);
            session.delete(productEntry);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exceptions", ex);
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public ProductStock findProdStockByCode(String prodCode) {
        Session session = null;
        ProductStock stock = null;
        try {
            session = sessionFactory.openSession();
            stock = (ProductStock) session.get(ProductStock.class, prodCode);
        } catch (Exception ex) {
            LOGGER.error("Exception{}", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return stock;
    }

    public Boolean saveProductStock(String prodCode, Integer quantity) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            ProductStock stock = findProdStockByCode(prodCode);
            if (stock != null) {
                stock.setQuantity(stock.getQuantity() + quantity);
            } else {
                stock = new ProductStock();
                stock.setProductCode(prodCode);
                stock.setQuantity(quantity);
            }
            session.saveOrUpdate(stock);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}", ex);
        }
        return true;
    }

    public Boolean saveProductStock(ProductStock productStock) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(productStock);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}", ex);
        }
        return true;
    }

    public ProductEntry findProductEntryById(Long Id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            ProductEntry productEntry = (ProductEntry) session.get(ProductEntry.class, Id);
            return productEntry;
        } catch (Exception ex) {
            LOGGER.error("Exceptions", ex);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public CategoryProduct findCategoryProdById(Long Id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            CategoryProduct categoryProduct = (CategoryProduct) session.get(CategoryProduct.class, Id);
            return categoryProduct;
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return null;
        }
    }

    public List<String> findAllProuctCodes() {
        Session session = null;
        List<String> productCodes = null;
        try {
            productCodes = new ArrayList<String>();
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O.productCode FROM CategoryProduct O ORDER BY O.productCode");
            productCodes = query.list();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return productCodes;
    }

    public CategoryStock findProdNameAndPriceByProdCode(String prodCode) {
        Session session = null;
        CategoryStock categoryStock = null;
        try {
            session = sessionFactory.openSession();
            SQLQuery query = session.createSQLQuery(ServiceConstant.CATEGORY_PROD_CODE_SQL);
            query.addEntity(CategoryStock.class);
            query.setParameter(ServiceConstant.PARM_PROD_CODE, prodCode);
            categoryStock = (CategoryStock) query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return categoryStock;
    }

    public CustomerDetails saveCutomerDetails(CustomerDetails customerDetails) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(customerDetails);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customerDetails;
    }

    public BillEntry saveBillDetails(BillEntry billEntry) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(billEntry);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return billEntry;
    }

    public Boolean deleteBillDetails(Long Id) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            BillEntry entry = (BillEntry) session.get(BillEntry.class, Id);
            session.delete(entry);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<CustomerDetails> findAllCustomerDetails() {
        Session session = null;
        List<CustomerDetails> customerDetailses = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM CustomerDetails O ORDER BY O.billNo");
            customerDetailses = query.list();
            LOGGER.info("Customer Details{}" + customerDetailses);
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customerDetailses;
    }

    public CustomerDetails findCustomerByBillId(Long Id) {
        Session session = null;
        CustomerDetails customerDetails = null;
        try {
            session = sessionFactory.openSession();
            customerDetails = (CustomerDetails) session.get(CustomerDetails.class, Id);
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customerDetails;
    }

    public List<BillEntry> findBillDetailsByBillId(Long Id) {
        Session session = null;
        List<BillEntry> billEntrys = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM BillEntry O JOIN O.customerDetails CST ON O.billId=CST.billNo WHERE CST.billNo=:billId");
            query.setParameter("billId", Id);
            billEntrys = query.list();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return billEntrys;
    }

    public Boolean updateCustomerDetails(CustomerDetails customerDetails) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(customerDetails);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public BillEntry findBillDetailsById(Long Id) {
        Session session = null;
        BillEntry billEntry = null;
        try {
            session = sessionFactory.openSession();
            billEntry = (BillEntry) session.get(BillEntry.class, Id);
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return billEntry;
    }

    public HeaderSettings saveHeaderSetting(HeaderSettings headerSetting) {
        Session session = null;
        Transaction trans = null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(headerSetting);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return headerSetting;
    }

    public HeaderSettings findHeaderSettingByUse(String useFlag) {
        Session session = null;
        HeaderSettings headerSettings = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT O FROM HeaderSettings O WHERE O.defaultUse=:defaultUse");
            query.setParameter("defaultUse", useFlag);
            headerSettings = (HeaderSettings) query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return headerSettings;
    }

    public UserInfo findUserByLoginId(String loginId) {
        Session session = null;
        UserInfo userInfo=null;
        try {
             session=sessionFactory.openSession();
             Criteria sqlCriteria=session.createCriteria(UserInfo.class);
             sqlCriteria.add(Restrictions.eq("loginId",loginId));
             userInfo=(UserInfo)sqlCriteria.uniqueResult();
        }catch (Exception ex) {
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return userInfo;
    }

    public Boolean updateHeaderSetting(HeaderSettings headerSetting) {
        Session session=null;
        Transaction trans=null;
        try{
            session=sessionFactory.openSession();
            trans=session.beginTransaction();
            session.update(headerSetting);
            trans.commit();
            return true;
        }catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public UserInfo saveUserDetails(UserInfo userInfo) {
       Session session=null;
       Transaction trans=null;
       try{
           session=sessionFactory.openSession();
           trans=session.beginTransaction();
           session.save(userInfo);
           trans.commit();
       }catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
       return userInfo;
    }

    public Boolean updateUserDetails(UserInfo userInfo) {
        Session session=null;
        Transaction trans=null;
        try{
            session=sessionFactory.openSession();
            trans=session.beginTransaction();
            session.update(userInfo);
            trans.commit();
            return true;
        }catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Boolean deleteUserDetails(Long id) {
         Session session=null;
        Transaction trans=null;
        try{
            session=sessionFactory.openSession();
            trans=session.beginTransaction();
            UserInfo userInfo=findUserDetailsById(id);
            session.delete( userInfo);
            trans.commit();
            return true;
        }catch (Exception ex) {
            trans.rollback();
            LOGGER.error("Exception{}" + ex, ex.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public UserInfo findUserDetailsByLoginId(String loginId) {
       Session session=null;
        try{
            session=sessionFactory.openSession();
            Criteria sqlCriteria=session.createCriteria(UserInfo.class);
            sqlCriteria.add(Restrictions.eq("loginId",loginId));
            return (UserInfo)sqlCriteria.uniqueResult();
        }catch(Exception ex){
            LOGGER.error("Exception{}"+ex,ex);
            return null;
        }
    }

    public List<UserInfo> findAllUserDetails() {
        Session session=null;
        try{
            session=sessionFactory.openSession();
            Criteria sqlCriteria=session.createCriteria(UserInfo.class);
            return (List<UserInfo>)sqlCriteria.list();
        }catch(Exception ex){
            LOGGER.error("Exception{}"+ex,ex);
            return null;
        }
    }

    public UserInfo findUserDetailsById(Long id) {
        Session session=null;
        try{
            session=sessionFactory.openSession();
            UserInfo userInfo=(UserInfo)session.get(UserInfo.class,id);
            return userInfo;
        }catch(Exception ex){
            LOGGER.error("Exception{}"+ex,ex);
            return null;
        }
    }

}
