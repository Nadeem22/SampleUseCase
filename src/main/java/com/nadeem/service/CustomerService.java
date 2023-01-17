package com.nadeem.service;

import java.util.List;

import com.nadeem.model.Customer;

public interface CustomerService {
	 
	 public List listAllCustomers();
	 
	 public void saveOrUpdate(Customer customer);
	 
	 public Customer findCustomerById(int id);
	 
	 public void deleteCustomer(int id);
	}
