package com.nadeem.service;

import java.util.Set;



public interface JCService {

	Set<Integer> getJCYears();

	Set<Integer> getJcMonthsByYear(String year);

}
