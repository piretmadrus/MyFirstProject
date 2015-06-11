package org.mypr.service;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mypr.PiretBaseTest;
import org.mypr.dao.DaoException;
import org.mypr.dao.DataDao;
import org.mypr.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class DataServiceImpTest extends PiretBaseTest {

	@InjectMocks
	DataServiceImpl dataServiceImpl;

	@Mock
	DataDao dataDao;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testGetPersonByIdNotFound() throws DaoException {
		int id = 1;
		when(dataDao.getPersonById(id)).thenReturn(null);
		Person person = dataServiceImpl.getPersonById(id);
		assertNull(person);
	}

	@Test
	public void testGetPersonByIdException() throws DaoException {
		int id = 1;
		when(dataDao.getPersonById(id)).thenThrow(new DaoException(null, null));
		Person person = dataServiceImpl.getPersonById(id);
		assertNull(person);
	}
	
	@Test
	public void testGetPersonByIdPersonFound() throws DaoException {
		int id = 1;
		Person person1 = new Person("A", "B", "111", new Date(), 1, 1, new Timestamp(1), new Timestamp(1));
		when(dataDao.getPersonById(id)).thenReturn(person1);
		Person result = dataServiceImpl.getPersonById(id);
		assertEquals(result, person1);
	}
}
