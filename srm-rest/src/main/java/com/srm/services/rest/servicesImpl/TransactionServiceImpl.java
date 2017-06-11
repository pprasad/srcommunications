package com.srm.services.rest.servicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.services.entity.Users;
import com.srm.services.rest.dao.TransactionDao;
import com.srm.services.rest.daoImpl.TransactionDaoImpl;
import com.srm.services.rest.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final static Logger LOGGER=LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionDao transactionDao;
	
	public List<Users> findAllUsers() {
		return transactionDao.findAllUsers();
	}

}
