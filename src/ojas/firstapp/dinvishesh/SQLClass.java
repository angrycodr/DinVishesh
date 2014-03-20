package ojas.firstapp.dinvishesh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClass {
	public static final String KEY_EVENTID = "_id";
	public static final String KEY_EVENTNAME = "event_name";
	public static final String KEY_EVENTDATE = "event_date";
	public static final String KEY_EVENTMONTH = "event_month";

	private static final String DATABASE_NAME = "Eventdb";
	private static final String DATABASE_TABLE = "eventTable";
	private static final int DATABASE_VERSION = 2;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" + KEY_EVENTID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_EVENTNAME
					+ " TEXT NOT NULL, " + KEY_EVENTDATE + " TEXT NOT NULL, "
					+ KEY_EVENTMONTH + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public SQLClass(Context c) {
		// TODO Auto-generated constructor stub
		ourContext = c;
	}

	public SQLClass open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String eName, String eDate, String eMonth) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_EVENTNAME, eName);
		cv.put(KEY_EVENTDATE, eDate);
		cv.put(KEY_EVENTMONTH, eMonth);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String[] getData(int dd, int mm) {
		// TODO Auto-generated method stub

		Cursor c = ourDatabase.rawQuery("SELECT " + KEY_EVENTNAME + " FROM "
				+ DATABASE_TABLE + " WHERE " + KEY_EVENTDATE + " = " + dd
				+ " AND " + KEY_EVENTMONTH + " = " + mm, null);
		String result[] = new String[c.getCount()];
		int ieventname = c.getColumnIndex(KEY_EVENTNAME);
		int i = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++)
			result[i] = c.getString(ieventname);
		return result;
	}
}
