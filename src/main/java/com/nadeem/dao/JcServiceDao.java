package com.nadeem.dao;

import java.util.List;

import com.nadeem.model.JCyear;

public interface JcServiceDao  {

	   public List<JCyear> getJcDates();

	   public List<JCyear> getJcMonthByYear(int year) ;
	
}
