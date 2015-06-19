package org.mypr.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.mypr.dao.DataDao;
import org.mypr.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("dataService")
public class DataServiceImpl implements DataService {

	@Autowired
	DataDao dataDao;

	/*@Value("${key}")
	private String key;

	@PostConstruct
	public void init() {
		System.out.println("key = " + key);
	}*/

	public Person getPersonById(int id) {
		Person person = null;
		try {
			person = dataDao.getPersonById(id);
		} catch (Exception ex) {
			System.out.println("DataServiceImpl.getPersonById():"
					+ ex.getMessage());
		}
		return person;
	}
	
	public List<Person> findAll(){
		return dataDao.findAll();
	}

}
