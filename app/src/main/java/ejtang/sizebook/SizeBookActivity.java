package ejtang.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SizeBookActivity extends AppCompatActivity {
    private ListView peopleList;
    private ArrayList<person> people;
    private ArrayAdapter<person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_book);

        peopleList = (ListView) findViewById(R.id.people);
    }

    public void addPerson (View view) {
        Intent intent = new Intent(this, addPerson.class);
        startActivity(intent);
    }
}
