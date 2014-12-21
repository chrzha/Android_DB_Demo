package com.example.db;

import com.example.service.DBOpenHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;



public class PersonProvider extends ContentProvider {

	private DBOpenHelper dbOpenHelper;
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
	static{
		MATCHER.addURI("com.example.providers.personProvider", "person", PERSONS);
	}
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			long rowId = db.insert("person", "name", values);
			Uri insertUri = Uri.parse("com.example.providers.personProvider/person/"+rowId);
			
			return insertUri;
		default:
			throw new IllegalArgumentException("unknown uri:"+uri);
		}
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbOpenHelper = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
