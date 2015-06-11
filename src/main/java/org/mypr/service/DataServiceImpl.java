package org.mypr.service;

import java.util.List;

import org.mypr.dao.DataDao;
import org.mypr.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataService")
public class DataServiceImpl implements DataService {

	@Autowired
	DataDao dataDao;

	public Person getPersonById(int id) {
		Person person = null;
		try {
			person = dataDao.getPersonById(id);
		} catch (Exception ex) {
			System.out.println("PersonServiceImpl.getPersonById():"
					+ ex.getMessage());
		}
		return person;
	}
	
	public List<Person> findAll(){
		return dataDao.findAll();
	}

}
