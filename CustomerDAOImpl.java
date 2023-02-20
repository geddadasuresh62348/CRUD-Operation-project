package com.surya.springmvc.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.surya.springmvc.hibernate.Entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	// need to inject the Session Factory
	@Autowired
	private SessionFactory factory;
	
	
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session session=factory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery=session.createQuery("from Customer order by lastName",Customer.class);	
		
		// execute query and get result list
		
		List<Customer> customers=theQuery.getResultList();
		
		// return the results
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get the current hibernate session
		Session session=factory.getCurrentSession();
		
		// save the customer 
		session.saveOrUpdate(theCustomer);

	
	}


	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		Session session=factory.getCurrentSession();
		
		// retrive the customer from database
		Query query=session.createQuery("from Customer where id=:theId").setParameter("theId", theId);
		
		Customer customer=(Customer) query.getResultList();
//		Customer customer=session.get(Customer.class, theId);
		return customer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session session=factory.getCurrentSession();	
		
		// retrieve the customer from database
		Customer customer=session.get(Customer.class, theId);
		
		// delete the customer from database
//		Query query= session.createQuery("delete from Customer where id=:theId").setParameter("theId", theId);
//		query.executeUpdate();
		
		System.out.println("\nObject : "+customer);
		session.delete(customer);
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current hibernate session
		Session session=factory.getCurrentSession();	
		
		// get the customers from database
		Query query=null;
				
		//only search by name if theSearchName is not empty
		if(theSearchName !=null && theSearchName.trim().length()>0) {
			query=session.createQuery("from Customer where lower(firstName) like:theName or lower(lastName) like:theName",Customer.class);
			query.setParameter("theName","%"+theSearchName.toLowerCase()+"%");
		}
		else {
				query=session.createQuery("from Customer",Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers=query.getResultList();
		System.out.println("\nCustomers List : "+customers);
		return customers;
	}
}
