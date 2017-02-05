package ejtang.sizebook;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ejtang on 2017-02-04.
 */

public class PeopleAdapter extends ArrayAdapter<Person> {
    private ArrayList<Person> people;
    private Context context;

    public PeopleAdapter (ArrayList<Person> people, Context context) {
        super(context, R.layout.list_person, people);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        LayoutInflater peopleInflator = LayoutInflater.from(getContext());
        View customView = peopleInflator.inflate(R.layout.list_person, parent, false);

        Person person = getItem(postion);
        TextView nameText = (TextView) customView.findViewById(R.id.nameText);
        TextView dateText = (TextView) customView.findViewById(R.id.dateText);
        SpannableString ss = new SpannableString(person.getName());
        //ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, 0);


        nameText.setText(ss);
        dateText.setText("Last Updated:" + person.getDate());

        return customView;
    }



}
