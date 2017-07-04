package com.srm.services.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.srm.services.dto.ServiceConstant;
/*
 * @Auth:Prasad
 * @Date:02/07/2017
 * @Descritpion: it will udpate create/update information for each table
 */
public class AuditoInterceptor extends EmptyInterceptor{
	private static final Logger LOGGER=LoggerFactory.getLogger(AuditoInterceptor.class);
	
	private String loginUser=null;
	private Date createOn=null;
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		loginUser=loginUser!=null?loginUser:"Guest";
		createOn=createOn!=null?createOn:new Date();
		setValue(state,propertyNames,ServiceConstant.LOG_CREATED_BY,loginUser);
		setValue(state,propertyNames,ServiceConstant.LOG_CREATED_ON,createOn);
		setValue(state,propertyNames,ServiceConstant.LOG_PUI_ENTRY_ON,new Date());
		setValue(state,propertyNames,ServiceConstant.LOG_CREATED_TIME,new Date());
		//return super.onSave(entity, id, state, propertyNames, types);
		return true;
	}
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,Object[] currentState, Object[] previousState,String[] propertyNames, Type[] types) {
		loginUser=loginUser!=null?loginUser:"Guest";
		createOn=createOn!=null?createOn:new Date();
		if(previousState!=null){
		  setValue(currentState,propertyNames,ServiceConstant.LOG_UPDATED_BY,loginUser);
		  setValue(currentState,propertyNames,ServiceConstant.LOG_UPDATED_ON,new Date());
		//return super.onFlushDirty(entity, id, currentState, previousState,propertyNames, types);
		   return true;
		}else{
		   return false;
		}
	}
	private void setValue(Object[] currentState,String[] propertyNames,String propertyToSet, Object value) {    
		 Integer index=-1;
		 index=findIndex(propertyNames,propertyToSet);
		 if(index.intValue()>-1){
			  if(currentState[index]==null && !(propertyToSet.equals(ServiceConstant.LOG_CREATED_TIME)||propertyToSet.equals(ServiceConstant.LOG_UPDATED_BY))){
			      currentState[index]=value;
			  }else if(propertyToSet.equals(ServiceConstant.LOG_UPDATED_BY)){
				  currentState[index]=value;
			  }
		 }
	}
	private Integer findIndex(String[] propertyNames,String propertyToSet){
	   Integer index=-1;
	   for(int i=0;i<propertyNames.length;i++){
			  if(propertyNames[i].equals(propertyToSet)){
				  index=i;
			  }
		 }
	   return index;
  }
}
