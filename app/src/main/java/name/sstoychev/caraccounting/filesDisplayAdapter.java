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


/**
 * Created by Admin on 25.5.2015 �..
 */
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
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater  = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView filesTextView = (TextView) row.findViewById(R.id.filesTextView);
        ImageView  imageView = (ImageView) row.findViewById(R.id.imageView);
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);
        File file = objects.get(position);
        if (position == 0) {
            filesTextView.setText("..");
        } else {
            filesTextView.setText(objects.get(position).getName());
        }

        if (file.isDirectory()) {
            //checkBox.setEnabled(false);
            checkBox.setVisibility(View.INVISIBLE);
            imageView.setImageResource(R.drawable.diricon);
        }
        else {
            checkBox.setEnabled(true);
            imageView.setImageResource(R.drawable.fileicon);
        }
        return row;
    }
}
