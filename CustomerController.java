package com.surya.springmvc.hibernate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.surya.springmvc.hibernate.DAO.CustomerDAO;
import com.surya.springmvc.hibernate.Entity.Customer;
import com.surya.springmvc.hibernate.Service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the DAO
		List<Customer> customers= customerService.getCustomers();
				
				
		// add the customers to the model
		theModel.addAttribute("customers",customers);
		
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer customer=new Customer();
		
		theModel.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer from our Service
		Customer customer= customerService.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute(customer);
		
		// send over to our form
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("Id") int theId) {
		
		customerService.deleteCustomer(theId);
		System.out.println("customerId : "+theId);
		return "redirect:/customer/list";
	}
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName,Model theModel) {
		
		//Search customers from the service
		List<Customer> customers=customerService.searchCustomers(theSearchName);
		
		//add customers to the model
		theModel.addAttribute("customers",customers);
		
		return "list-customers";
	}
}
