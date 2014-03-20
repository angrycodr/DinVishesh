package ojas.firstapp.dinvishesh;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends Activity implements OnClickListener {

	Button viewCalendar,btAddEvent;
	ImageView prevDate, nextDate;
	ListView lvEvents;
	TextView Date;
	String values[] = new String[] {"Jayesh","Kajal"};
	String data[] = null;
	int mm, dd, yy;
	final Calendar c = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// GET DATE ON TEXTVIEW
		yy = c.get(Calendar.YEAR);
		mm = c.get(Calendar.MONTH);
		dd = c.get(Calendar.DATE);
		Date = (TextView) findViewById(R.id.Date);
		Date.setText(new StringBuilder().append("Today"));
		
		// UPDATE LISTVIEW
		
		SQLClass info = new SQLClass(this);
		info.open();
		data = info.getData(dd,mm+1);
		info.close();
		lvEvents = (ListView) findViewById(R.id.lvEvents);
		lvEvents.clearChoices();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
		lvEvents.setAdapter(adapter);
		data = null;
		adapter.notifyDataSetChanged();
		// VIEW CALENDAR BUTTON
		final Intent openCalendar = new Intent(this, viewCalendar.class);
		final Intent openAddEventPage = new Intent(this, AddEventPage.class);
		viewCalendar = (Button) findViewById(R.id.viewCalendar);
		viewCalendar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(openCalendar);
			}
		});
		
		btAddEvent = (Button) findViewById(R.id.btaddEvent);
		btAddEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(openAddEventPage);
			}
		});

		// GO LEFT BUTTON
		prevDate = (ImageView) this.findViewById(R.id.prevDate);
		prevDate.setOnClickListener(this);

		// GO RIGHT BUTTON
		nextDate = (ImageView) findViewById(R.id.nextDate);
		nextDate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//To view previous date
		if (v == prevDate) {
			if (dd == 1) {
				if (mm == 0) {
					dd = 31;
					mm = 11;
					yy--;
				} else if ((mm) == 2) {
					mm--;
					dd = 28;
				} else if ((mm == 3) || (mm == 5) || (mm == 7) || (mm == 8) || (mm == 10)) {
					mm--;
					dd = 31;
				} else {
					mm--;
					dd = 30;
				}
			} else
				dd--;
		}
		// To view next date
		if (v == nextDate) {
			if (dd >= 28) {
				if ((dd == 28) && (mm == 1)) {
					mm++;
					dd = 1;
				} else if ((dd >= 30)
						&& ((mm == 3) || (mm == 5) || (mm == 8) || (mm == 10))) {
					mm++;
					dd = 1;
				} else if ((dd == 31) && (mm == 11)) {
					mm = 0;
					dd = 1;
					yy++;
				} else if ((dd >= 31)
						&& ((mm == 0) || (mm == 2) || (mm == 4) || (mm == 6) || (mm == 7) || (mm == 9))) {
					mm++;
					dd = 1;
				}
				else
					dd++;
			} else
				dd++;
		}
		if(dd == c.get(Calendar.DATE) && mm == c.get(Calendar.MONTH) && yy == c.get(Calendar.YEAR))
			Date.setText(new StringBuilder().append("Today"));
		else
			Date.setText(new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy));

		// UPDATE LISTVIEW
				SQLClass info = new SQLClass(this);
				info.open();
				data = info.getData(dd,mm+1);
				info.close();
				lvEvents.clearChoices();
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
				lvEvents.setAdapter(adapter);
				data = null;
				adapter.notifyDataSetChanged();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
}
