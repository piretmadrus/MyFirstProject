package org.mypr.dao;

import org.mypr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DataDao extends JpaRepository<Person, Integer> {
	public Person getPersonById(int id) throws DaoException;
}
