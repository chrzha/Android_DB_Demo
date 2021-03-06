package com.example.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;

public class PersonService {

	private DBOpenHelper dbOpenHelper;
	public PersonService(Context context){
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	public void add(Person person){
		SQLiteDatabase dbDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "insert into person(name,phone) values(?,?)";
		dbDatabase.execSQL(sql,new Object[]{person.getName(),person.getPhone()});
		dbDatabase.close();
		
	}
	public void delete(int id){
		SQLiteDatabase dbDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from person where id = ?";
		dbDatabase.execSQL(sql,new Object[]{id});
	}
	public void update(Person person){
		SQLiteDatabase dbDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "update person set name=?,phone=? where id=?";
		dbDatabase.execSQL(sql,new Object[]{person.getName(),person.getPhone(),person.getId()});
	}
	public  Person  query(Integer id){
		SQLiteDatabase dbDatabase = dbOpenHelper.getReadableDatabase();
		String sql = "select * from person where id = ?";
		Cursor cursor = dbDatabase.rawQuery(sql,new String[]{id.toString()});
		if (cursor.moveToFirst()) {
			int pid = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(pid, name, phone);
		}
		cursor.close();
		return null;
	}
	public  List<Person>  queryPage(int offSet,int maxSize){
		List<Person> list = new ArrayList<Person>();
		SQLiteDatabase dbDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from person order by id asc limit ?,?";
		Cursor cursor = dbDatabase.rawQuery(sql,new String[]{String.valueOf(offSet),String.valueOf(maxSize)});
		while (cursor.moveToNext()) {
			int pid = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			list.add(new Person(pid, name, phone));
		}
		cursor.close();
		return list;
	}
	public long getCount(){
		SQLiteDatabase dbDatabase = dbOpenHelper.getReadableDatabase();
		String sql = "select count(*) from person ";
		Cursor cursor = dbDatabase.rawQuery(sql,null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
	
}
