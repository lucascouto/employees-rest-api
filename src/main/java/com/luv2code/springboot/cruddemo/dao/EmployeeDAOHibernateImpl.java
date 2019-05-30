package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> query =
				session.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = query.getResultList();
		
		// return the results
		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		//get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee employee = session.get(Employee.class, id);
		
		// return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//save employee
		session.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int id) {
		// get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//get employee
		Employee employee = session.get(Employee.class, id);
		
		//delete employee
		session.delete(employee);
		
	}

	

}
