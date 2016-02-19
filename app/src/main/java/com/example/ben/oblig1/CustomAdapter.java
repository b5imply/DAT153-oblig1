package com.example.ben.oblig1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ben.oblig1.classes.PersonArchive;

import java.util.List;

/**
 * This is a custom Adapter for a ListView for the list of people. This adapter forms how each row
 * is gonna look like. This takes in a List of people and sets the image of each person and name,
 * and displays it on the list.
 */
public class CustomAdapter extends ArrayAdapter {

    /**
     * Constructor for the Custom adapter
     *
     * @param context
     * @param values
     */
    public CustomAdapter(Context context, List<PersonArchive.People> values) {
        super(context, R.layout.row_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        if (!PersonArchive.isEmpty()) {
            PersonArchive.People currentPerson = PersonArchive.ITEM_MAP.get(getItem(position).toString());
            if (!currentPerson.equals(null)) {
                if (currentPerson.name != null) {
                    final String itemName = currentPerson.name;
                    TextView textView = (TextView) rowView.findViewById(R.id.itemName);
                    textView.setText(itemName);

                    ImageView imageView = (ImageView) rowView.findViewById(R.id.profilePic);
                    if (currentPerson.imgPath != null) {
                        imageView.setImageURI(Uri.parse(currentPerson.imgPath));
                    }else{
                        imageView.setImageResource(R.mipmap.blank_person);
                    }
                } else {
                    Toast.makeText(getContext(), "Could not load row", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(getContext(), "Archive is empty", Toast.LENGTH_LONG).show();
        }
        return rowView;
    }
}
