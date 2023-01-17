package com.nadeem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nadeem.model.JCyear;

@Repository
public class JCServiceDaoImpl implements JcServiceDao{
	
	
	@Autowired
	 private SessionFactory sessionFactory;

	 protected Session getSession(){
	  return sessionFactory.getCurrentSession();
	 }

	 
	@SuppressWarnings("unchecked")
	@Override
	public List<JCyear> getJcDates() {
		Criteria criteria = getSession().createCriteria(JCyear.class);
		return (List<JCyear>) criteria.list();
	}


	@SuppressWarnings("unchecked")
	public List<JCyear> getJcMonthByYear(int year) {
		 SQLQuery query =  getSession().createSQLQuery ("SELECT * FROM jcyear WHERE YEAR(jcdates) = :year").addEntity(JCyear.class);
	        query.setParameter("year", year);
	        return query.list();
}


}


	
