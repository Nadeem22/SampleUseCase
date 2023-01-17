package com.nadeem.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nadeem.model.Customer;
import com.nadeem.model.ExcelData;
import com.nadeem.service.CustomerService;
import com.nadeem.service.JCService;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {

 @Autowired
 private CustomerService customerService;
 
 @Autowired
 private JCService jcService;
 
 @RequestMapping(value="/list", method=RequestMethod.GET)
 public ModelAndView list(){
  ModelAndView model = new ModelAndView("customer/list");
  List list = customerService.listAllCustomers();
  model.addObject("list", list);
  return model;
 }
 
 
 // HUL SCREEN ONE MAPPING
 
 @GetMapping(value = "/screenone")
 public ModelAndView screenOne() {
	 ModelAndView model = new ModelAndView("hul/screenone");
	 model.addObject("years", jcService.getJCYears());
	 return model;
 }
 
 @GetMapping(value = "/jcmonth")
 public ResponseEntity<Set<Integer>> jcMonth(@RequestParam("year") String year) {
	 System.out.println("year is =========: " +year);
	 Set<Integer> monthList = new HashSet<Integer>();
	 if(year != null && year !="") {
		 monthList = jcService.getJcMonthsByYear(year);
	 }
	return new ResponseEntity<Set<Integer>>(monthList, HttpStatus.OK);
 }
 
 
 
 @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
 @ResponseBody
 public Set<ExcelData> handleFileUpload(@RequestParam("excelFile") MultipartFile file) throws InvalidFormatException {
     Set<ExcelData> excelDataList = new HashSet<>();
     if (!file.isEmpty()) {
         try {
             // Read the excel file data using Apache POI library
             Workbook workbook = WorkbookFactory.create(file.getInputStream());
             Sheet sheet = workbook.getSheetAt(0);

             // Extract the data from the excel file and store it in a list
             for (Row row : sheet) {
                 if (row.getRowNum() == 0) {
                     continue; // skip the header row
                 }
                 ExcelData data = new ExcelData();
                 data.setSapCode(row.getCell(0).getStringCellValue());
                 data.setSkuCode(row.getCell(1).getStringCellValue());
                 data.setSkuName(row.getCell(2).getStringCellValue());
                 data.setTargetQtyCtn((int)row.getCell(3).getNumericCellValue());
                 data.setTargetQtyPcs((int)row.getCell(4).getNumericCellValue());
                 excelDataList.add(data);
             }
             workbook.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     return excelDataList;
 }
 

 
 @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
 public ModelAndView update(@PathVariable("id") int id){
  ModelAndView model = new ModelAndView("customer/form");
  Customer customer = customerService.findCustomerById(id);
  model.addObject("customerForm", customer);
  
  return model;
 }
 
 @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
 public ModelAndView delete(@PathVariable("id") int id){
  customerService.deleteCustomer(id);
  
  return new ModelAndView("redirect:/customer/list");
 }
 
 @RequestMapping(value="/add", method=RequestMethod.GET)
 public ModelAndView add(){
  ModelAndView model = new ModelAndView("customer/form");
  Customer customer = new Customer();
  model.addObject("customerForm", customer);
  
  return model;
 }
 
 @RequestMapping(value="/save", method=RequestMethod.POST)
 public ModelAndView save(@ModelAttribute("customerForm") Customer customer){
  customerService.saveOrUpdate(customer);
  
  return new ModelAndView("redirect:/customer/list");
 }
}
