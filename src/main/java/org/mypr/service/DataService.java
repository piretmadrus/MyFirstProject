package org.mypr.service;

import java.util.List;

import org.mypr.model.Person;

public interface DataService {
	public Person getPersonById(int id);
	public List<Person> findAll();
}
