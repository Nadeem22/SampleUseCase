package com.nadeem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nadeem.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

 @Autowired
 private SessionFactory sessionFactory;

 protected Session getSession(){
  return sessionFactory.getCurrentSession();
 }
 
 @SuppressWarnings("unchecked")
 public List listAllCustomers() {
  Criteria criteria = getSession().createCriteria(Customer.class);
  System.out.println("Customer=======================" +(List) criteria.list());
  return (List) criteria.list();
 }

 public void saveOrUpdate(Customer customer) {
  getSession().saveOrUpdate(customer);
 }

 public Customer findCustomerById(int id) {
  Customer customer = (Customer) getSession().get(Customer.class, id);
  return customer;
 }

 public void deleteCustomer(int id) {
  Customer customer = (Customer) getSession().get(Customer.class, id);
  getSession().delete(customer);
 }
}
