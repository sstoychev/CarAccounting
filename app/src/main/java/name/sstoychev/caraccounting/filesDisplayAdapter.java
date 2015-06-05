package name.sstoychev.caraccounting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class filesDisplayAdapter extends ArrayAdapter<File> {

    public File currentDIr;
    private Context context;
    private int resource;
    private ArrayList<File> objects;

    public filesDisplayAdapter(Context context, int resource, ArrayList<File> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.currentDIr = objects.get(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView filesTextView = (TextView) row.findViewById(R.id.filesTextView);
        ImageView  imageView = (ImageView) row.findViewById(R.id.imageView);
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);
        CheckBox checkRead = (CheckBox) row.findViewById(R.id.checkRead);
        CheckBox checkWrite = (CheckBox) row.findViewById(R.id.checkWrite);
        CheckBox checkExecute = (CheckBox) row.findViewById(R.id.checkExecute);
        File file = objects.get(position);
        if (position == 0) {
            filesTextView.setText("..");
        } else {
            filesTextView.setText(objects.get(position).getName());
        }

        if (file.isDirectory()) {
            checkBox.setVisibility(View.INVISIBLE);
            imageView.setImageResource(R.drawable.diricon);
        }
        else {
            checkBox.setEnabled(true);
            imageView.setImageResource(R.drawable.fileicon);
        }
        checkRead.setChecked(file.canRead());
        checkWrite.setChecked(file.canWrite());
        checkExecute.setChecked(file.canExecute());
        return row;
    }
}
