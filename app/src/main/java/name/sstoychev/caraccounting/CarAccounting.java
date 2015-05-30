package name.sstoychev.caraccounting;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CarAccounting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_accounting);
        //String[] files = {"1", "2", "3"};
        //String[] files = Environment.getRootDirectory().list();
        String[] files = getFullPathFiles(Environment.getRootDirectory());
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,files);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.fileslistviewlayout,R.id.filesTextView,files);
        List<String> fileslist = Arrays.asList(files);
        filesDisplayAdapter arrayAdapter = new filesDisplayAdapter(this, R.layout.fileslistviewlayout, fileslist);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(arrayAdapter);
    }

    public String[] getFullPathFiles(File dir){
        String[] filesfromdir = dir.list();
        String path = dir.getAbsolutePath();
        List<String> allfiles = new ArrayList<String>();
        allfiles.add("..");
        for (int i = 0; i < filesfromdir.length; i++) {
            allfiles.add(path + "/" + filesfromdir[i]);
        }
        String[] files = allfiles.toArray(new String[allfiles.size()]);
        return files;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_accounting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void browseForFiles(View view) {
        String path = Environment.getExternalStorageDirectory().toString();

        ListView l =(ListView)findViewById(R.id.listView);
        /////////
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "Media mounted",
                    Toast.LENGTH_LONG).show();
        } else if  (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            Toast.makeText(getApplicationContext(), "Media mounted read only",
                    Toast.LENGTH_LONG).show();
        } else  {
            Toast.makeText(getApplicationContext(), "Media not mounted",
                    Toast.LENGTH_LONG).show();
        }
        //////
        File dir = Environment.getRootDirectory();
        File files[] = dir.listFiles();
        /*Log.d("Files", "Size: "+ file.length);
        for (int i=0; i < file.length; i++)
        {
            Log.d("Files", "FileName:" + file[i].getName());
        }
        Toast.makeText(getApplicationContext(), files.toString(),
                Toast.LENGTH_LONG).show();
         */
    }
}
