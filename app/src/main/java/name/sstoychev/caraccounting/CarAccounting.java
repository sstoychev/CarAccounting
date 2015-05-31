package name.sstoychev.caraccounting;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class CarAccounting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_accounting);
        //String[] files = {"1", "2", "3"};
        //String[] files = Environment.getRootDirectory().list();
        //ArrayList<File> files = getFullPathFiles(Environment.getRootDirectory());
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,files);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.fileslistviewlayout,R.id.filesTextView,files);
        //List<String> fileset = Arrays.asList(files);
        //filesDisplayAdapter arrayAdapter = new filesDisplayAdapter(this, R.layout.fileslistviewlayout, files);
        ListView lv = (ListView) findViewById(R.id.listView);
        //lv.setAdapter(arrayAdapter);
        showFiles(lv, 0);
        //TextView textPath = (TextView) findViewById(R.id.textPath);
        //textPath.setText(Environment.getRootDirectory().getAbsolutePath());
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView lv,
                                            View v,
                                            int position,
                                            long id) {
                        /*
                        filesDisplayAdapter arrayAdapter = (filesDisplayAdapter) lv.getAdapter();
                        if (arrayAdapter.getItem(position).isDirectory()){
                            File file = (position == 0  & arrayAdapter.getItem(position).getParentFile() != null ? arrayAdapter.getItem(position).getParentFile(): arrayAdapter.getItem(position));
                            ArrayList<File> files = getFullPathFiles(file);
                          arrayAdapter.clear();
                            for (int i =0; i< files.size(); i++){
                                arrayAdapter.add(files.get(i));
                            }
                            arrayAdapter.notifyDataSetChanged();
                        }
                        */
                        showFiles((ListView) lv, position);
                    }
                });
    }

    public ArrayList<File> getFullPathFiles(File dir) {
        File[] filesFromDir = dir.listFiles();
        if (filesFromDir == null) {
            return null;
        }
        ArrayList<File> allFiles = new ArrayList<>(Arrays.asList(filesFromDir));
        if (dir.getParentFile() != null) {
            allFiles.add(0, dir);
        }

        return allFiles;
    }

    public void showFiles(ListView lv, int position) {
        filesDisplayAdapter arrayAdapter = (filesDisplayAdapter) lv.getAdapter();

        if (arrayAdapter == null) {
            ArrayList<File> files = getFullPathFiles(Environment.getRootDirectory());
            filesDisplayAdapter newArrayAdapter = new filesDisplayAdapter(this, R.layout.fileslistviewlayout, files);
            lv.setAdapter(newArrayAdapter);
            TextView textPath = (TextView) findViewById(R.id.textPath);
            textPath.setText(Environment.getRootDirectory().getAbsolutePath());
        } else {
            if (arrayAdapter.getItem(position).isDirectory()) {
                File file = (position == 0 & arrayAdapter.getItem(position).getParentFile() != null ? arrayAdapter.getItem(position).getParentFile() : arrayAdapter.getItem(position));
                ArrayList<File> files = getFullPathFiles(file);
                if (files == null) {
                    return;
                }
                arrayAdapter.clear();
                for (int i = 0; i < files.size(); i++) {
                    arrayAdapter.add(files.get(i));
                }
                arrayAdapter.notifyDataSetChanged();
                TextView textPath = (TextView) findViewById(R.id.textPath);
                textPath.setText(file.getAbsolutePath());
            }
        }
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
