package com.srm.services.rest.daoImpl;

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
import com.srm.services.entity.Users;
import com.srm.services.rest.dao.TransactionDao;

@Repository
public class TransactionDaoImpl implements TransactionDao{
	private final static Logger LOGGER=LoggerFactory.getLogger(TransactionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Users> findAllUsers() {
		Session session=null;
		List<Users> users=null;
		try{
			LOGGER.info("sessionFactory{}"+sessionFactory);
			session=sessionFactory.openSession();
			Query query=session.createQuery("SELECT O FROM Users O");
		    users=query.list();
		}catch(Exception ex){
			LOGGER.error("Exception{}"+ex.getMessage(),ex);
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return users!=null?users:new ArrayList<Users>();
	}

	public Boolean saveOrupdateCategory(Category category) {
		Session session=null;
		Transaction transaction=null;
		Boolean flag=Boolean.FALSE;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			if(category.getCateId()==null){
			   session.save(category);
			}else{
				Category cat=findCategoryById(category.getCateId());
				cat.setCateName(category.getCateName());
				session.update(cat);
			}
		    transaction.commit();
			flag=Boolean.TRUE;
		}catch(Exception ex){
			flag=Boolean.FALSE;
			transaction.rollback();
			LOGGER.error(ex.getMessage(),ex);
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return flag;
	}

	public List<Category> getCategory() {
	     List<Category> categories=null;
	     Session session=null;
	     try{
	    	  session=sessionFactory.openSession();
	    	  Query query=session.createQuery("SELECT O FROM Category O ORDER BY O.cateId");
	    	  categories=(List<Category>)query.list();
	     }catch(Exception ex){
	    	 LOGGER.error(ex.getMessage(),ex);
	     }finally{
			if(session!=null){
				session.close();
			}
		  }
	     return categories!=null?categories:new ArrayList<Category>();
	}

	public Boolean deleteCategory(Category category) {
		Session session=null;
		Transaction transaction=null;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.delete(category);
			transaction.commit();
		}catch(Exception ex){
			transaction.rollback();
			LOGGER.error(ex.getMessage(),ex);
			return Boolean.FALSE;
		}finally{
			if(session!=null){
				session.close();
			}
	    }
		return Boolean.TRUE;
	}

	public Category findCategoryById(Long Id) {
		Session session=null;
		Category category=null;
		try{
			session=sessionFactory.openSession();
			category=(Category)session.get(Category.class,Id);
		}catch(Exception ex){
			LOGGER.error(ex.getMessage(),ex);
		}finally{
			if(session!=null){
				session.close();
			}
	    }
		return category;
	}

}
