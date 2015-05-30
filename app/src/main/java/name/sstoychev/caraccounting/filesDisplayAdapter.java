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
import java.util.List;


/**
 * Created by Admin on 25.5.2015 �..
 */
public class filesDisplayAdapter extends ArrayAdapter<String>{

    private Context context;
    private int resource;
    private String[] objects;

    public filesDisplayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater  = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView filesTextView = (TextView) row.findViewById(R.id.filesTextView);
        ImageView  imageView = (ImageView) row.findViewById(R.id.imageView);
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);

        filesTextView.setText((CharSequence) objects[position]);
        File file = new File(objects[position]);
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
