package ojas.firstapp.dinvishesh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEventPage extends Activity implements OnClickListener {
	String values[] = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	EditText etEventName, etEventDate, etEventMonth;
	Button btsubmitEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event_page);

		etEventName = (EditText) findViewById(R.id.etEventName);
		etEventDate = (EditText) findViewById(R.id.etEventDate);
		etEventMonth = (EditText) findViewById(R.id.etEventMonth);
		btsubmitEvent = (Button) findViewById(R.id.submitEvent);

		btsubmitEvent.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String eName = etEventName.getText().toString();
		String eDate = etEventDate.getText().toString();
		String eMonth = etEventMonth.getText().toString();
		
		if(0<=Integer.parseInt(eMonth) && Integer.parseInt(eMonth)<=11){
			boolean flag = true;
			try {
			SQLClass entry = new SQLClass(AddEventPage.this);
			entry.open();
			entry.createEntry(eName,eDate,eMonth);
			entry.close();
			} catch(Exception e) {
			flag = false;
			} finally {
				if(flag) {
	            	Toast t = Toast.makeText(this, "Saving", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		}
		else {
			Toast t = Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT);
			t.show();
		}
	}
}