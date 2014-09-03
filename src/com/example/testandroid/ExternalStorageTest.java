package com.example.testandroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ExternalStorageTest extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		
		String state = Environment.getExternalStorageState();
		if(!state.equals(Environment.MEDIA_MOUNTED)){
			textView.setText("No external storage mounted");
		}else{
			File externalDir = Environment.getExternalStorageDirectory();
			File textFile = new File(externalDir.getAbsolutePath() + File.separator + "text.txt");
			String text = "";
			try {
				writeTextFile(textFile, "This is a test. Roget");
				text = readTextFile(textFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textView.setText(text);
			if(!textFile.delete()){
				textView.setText("Couldn't temove temporary file");
			}
		}
	}

	private void writeTextFile( File file, String text ) throws IOException {
        if( !file.exists() ){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter( new FileWriter( file ));
        writer.write( text );
        writer.flush();
        writer.close();
    }

    private String readTextFile( File file ) throws IOException{
        BufferedReader reader = new BufferedReader( new FileReader( file ));
        String text = null;
        String line = null;
        while ((line = reader.readLine()) != null) {
            text += line;
        }
        return text;
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.external_storage_test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
