package com.example.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.domain.Person;
import com.example.service.PersonService;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView;
	private PersonService personService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		personService = new PersonService(this);
		listView = (ListView) this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListner());
		show();
	}
	private final class ItemClickListner implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long arg3) {
		  ListView lView = (ListView)parent;
		  Map<String, Object> person = (Map<String, Object>) lView.getItemAtPosition(position);
		  Toast.makeText(getApplicationContext(), person.get("pid").toString(), 1).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void show() {
		List<Person> persons = personService.queryPage(0, 20);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for(Person person:persons){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("pid", person.getId());
			map.put("name", person.getName());
			map.put("phone", person.getPhone());
			data.add(map);
		}
		 
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				new String[] {"pid","name","phone"}, new int[] { R.id.pid, R.id.name, R.id.phone });

		listView.setAdapter(adapter);
	}

}
