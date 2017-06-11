package com.srm.services.rest.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
			session=sessionFactory.openSession();
			Query query=session.createQuery("SELECT O FROM Users O");
		    users=query.list();
		}catch(Exception ex){
			LOGGER.error("Exception{}"+ex.getMessage(),ex);
		}
		return users!=null?users:new ArrayList<Users>();
	}

}
