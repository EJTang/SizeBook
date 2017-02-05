package ejtang.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExpandPerson extends AppCompatActivity {
    private static final String FILENAME = "SizeBook.sav";
    private ArrayList<Person> people;
    private Person person;
    int position;
    TextView nameField;
    TextView dateField;
    TextView neckField;
    TextView bustField;
    TextView chestField;
    TextView waistField;
    TextView hipField;
    TextView inseamField;
    TextView commentsField;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_person);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        nameField = (TextView) findViewById(R.id.nameText);
        dateField = (TextView) findViewById(R.id.dateText);
        neckField = (TextView) findViewById(R.id.neckText);
        bustField = (TextView) findViewById(R.id.bustText);
        chestField = (TextView) findViewById(R.id.chestText);
        waistField = (TextView) findViewById(R.id.waistText);
        hipField = (TextView) findViewById(R.id.hipText);
        inseamField = (TextView) findViewById(R.id.inseamText);
        commentsField = (TextView) findViewById(R.id.commentsText);


        Button deleteButton = (Button) findViewById(R.id.delete);
        Button editButton = (Button) findViewById(R.id.edit);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                people.remove(position);
                saveInFile();
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFile();
        person = people.get(position);
        nameField.setText(person.getName());
        dateField.setText(person.getDate().toString());
        neckField.setText(person.getNeck().toString());
        bustField.setText(person.getBust().toString());
        chestField.setText(person.getChest().toString());
        waistField.setText(person.getWaist().toString());
        hipField.setText(person.getHip().toString());
        inseamField.setText(person.getInseam().toString());
        commentsField.setText(person.getComment().toString());


    }

    protected void loadFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            people = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            people = new ArrayList<Person>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(people, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception later
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
