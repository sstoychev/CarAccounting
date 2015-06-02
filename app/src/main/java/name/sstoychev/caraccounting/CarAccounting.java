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
import java.util.Collections;
import java.util.Comparator;


public class CarAccounting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String externalStorageState = Environment.getExternalStorageState();
        String dataDir = Environment.getDataDirectory().getAbsolutePath();
        Toast.makeText(this, dataDir, Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_car_accounting);
        ListView lv = (ListView) findViewById(R.id.listView);
        showFiles(lv, 0);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView lv,
                                            View v,
                                            int position,
                                            long id) {
                        showFiles((ListView) lv, position);
                    }
                });
    }

    public ArrayList<File> getFullPathFiles(File dir) {
        File[] filesFromDir = dir.listFiles();
        if (filesFromDir == null) {
            ArrayList<File> allFiles = new ArrayList<>();
            if (dir.getParentFile() != null) {
                allFiles.add(0, dir);
            }
            return allFiles;
        } else {
            ArrayList<File> allFiles = new ArrayList<>(Arrays.asList(filesFromDir));
            Collections.sort(allFiles, new FileNameComparator());
            if (dir.getParentFile() != null) {
                allFiles.add(0, dir);
            }
            return allFiles;
        }
    }

    public void showFiles(ListView lv, int position) {
        filesDisplayAdapter arrayAdapter = (filesDisplayAdapter) lv.getAdapter();

        if (arrayAdapter == null) {
            ArrayList<File> files = getFullPathFiles(Environment.getRootDirectory());
            filesDisplayAdapter newArrayAdapter = new filesDisplayAdapter(this, R.layout.fileslistviewlayout, files);
            lv.setAdapter(newArrayAdapter);
            setTexts(Environment.getRootDirectory());
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
                setTexts(file);
            }
        }
    }

    public void setTexts(File dir) {
        TextView textPath = (TextView) findViewById(R.id.textPath);
        textPath.setText(dir.getAbsolutePath());
        TextView fileText = (TextView) findViewById(R.id.textFile);
        if (dir.canWrite()) {
            fileText.setText("can Write");
        } else {
            fileText.setText("cannot write");
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

    private static class FileNameComparator implements Comparator<File> {
        public int compare(File lhs, File rhs) {
            if (lhs.isDirectory() & !rhs.isDirectory()) {
                return -1;
            } else if (!lhs.isDirectory() & rhs.isDirectory()) {
                return 1;
            } else {
                return lhs.getName().compareTo(rhs.getName());
            }
        }
    }
}
