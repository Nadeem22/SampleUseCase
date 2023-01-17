package com.nadeem.dao;

import java.util.List;

import com.nadeem.model.Customer;

public interface CustomerDao {
	 
	 public List listAllCustomers();
	 
	 public void saveOrUpdate(Customer customer);
	 
	 public Customer findCustomerById(int id);
	 
	 public void deleteCustomer(int id);
	 
	}
