package com.nadeem.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadeem.dao.JcServiceDao;
import com.nadeem.model.JCyear;

@Service
public class JCServiceImpl implements JCService {

	@Autowired
	private JcServiceDao jcSserviceDao;

	@Override
	@Transactional
	public Set<Integer> getJCYears() {
		List<JCyear> jcdates = jcSserviceDao.getJcDates();
		Set<Integer> years = getYearsFromDates(jcdates);
		System.out.println("=============" + years);
		return years;
	}

	public static Set<Integer> getYearsFromDates(List<JCyear> jcdates) {
		Set<Integer> years = new HashSet<Integer>();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		for (JCyear date : jcdates) {
			System.out.println("Date Format is :" +date.getJcdates().toString());
			LocalDateTime localDateTime = LocalDateTime.parse(date.getJcdates().toString(), formatter);
            int year = localDateTime.getYear();
            years.add(year);
		}
		System.out.println("Years are :" +years);
		return years;
	}

	@Override
	@Transactional
	public Set<Integer> getJcMonthsByYear(String year) {
			List<JCyear> jcdates = jcSserviceDao.getJcMonthByYear(Integer.parseInt(year));
			System.out.println(" JCDATES IN SERVICE IMPL :" +jcdates);
			Set<Integer> months = getMonthsFromDates(jcdates);
			return months;
		
	}
	
	public static Set<Integer> getMonthsFromDates(List<JCyear> dates) {
        Set<Integer> months = new HashSet<Integer>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        for (JCyear date : dates) {
            try {
                java.util.Date parsedDate = dateFormat.parse(date.getJcdates().toString());
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.setTime(parsedDate);
                months.add(calendar.get(java.util.Calendar.MONTH) + 1);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Month Set in converter: " +months);
        return months;
    }

}
