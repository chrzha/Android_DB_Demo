package com.example.test;

import java.util.List;

import com.example.domain.Person;
import com.example.service.DBOpenHelper;
import com.example.service.PersonService;

import android.test.AndroidTestCase;
import android.util.Log;

public class DBTest extends AndroidTestCase {

	private static final String TAG="DBTest";
	
	public void testDBHelper() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	}

	public void testAdd() {

		PersonService personService = new PersonService(getContext());
		for (int i = 0; i < 20; i++) {
			personService.add(new Person(1,"chrzha", "15251327856"));
		}
	}

	public void testDelete() {
		PersonService personService = new PersonService(getContext());
		personService.delete(1);
		
	}

	public void testUpdate() {

		PersonService personService = new PersonService(getContext());
		personService.update(new Person(2,"chrzha2", "15251327856"));
		
	}

	public void testQuery() {
		PersonService personService = new PersonService(getContext());
		Person person = personService.query(2);
		Log.i(TAG, person.getName());
	}

	public void testQueryPage() {

		PersonService personService = new PersonService(getContext());
		List<Person> persons = personService.queryPage(0, 5);
		Log.i(TAG, String.valueOf(persons.size()));
	}

	public void testQueryCount() {

		PersonService personService = new PersonService(getContext());
		long count = personService.getCount();
		Log.i(TAG, String.valueOf(count));
	}
}
