package ejtang.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

public class EditPerson extends AppCompatActivity {
    private static final String FILENAME = "SizeBook.sav";
    private ArrayList<Person> people;
    private Person person;
    int position;
    EditText name;
    EditText date;
    EditText neck;
    EditText bust;
    EditText chest;
    EditText waist;
    EditText hip;
    EditText inseam;
    EditText comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        setContentView(R.layout.activity_edit_person);
        name = (EditText) findViewById(R.id.nameText);
        date = (EditText) findViewById(R.id.dateText);
        neck = (EditText) findViewById(R.id.neckText);
        bust = (EditText) findViewById(R.id.bustText);
        chest = (EditText) findViewById(R.id.chestText);
        waist = (EditText) findViewById(R.id.waistText);
        hip = (EditText) findViewById(R.id.hipText);
        inseam = (EditText) findViewById(R.id.inseamText);
        comments = (EditText) findViewById(R.id.commentsText);


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFile();
        person = people.get(position);

        name.setText(person.getName());
        neck.setText(person.getNeck().toString());
        bust.setText(person.getBust().toString());
        chest.setText(person.getChest().toString());
        waist.setText(person.getWaist().toString());
        hip.setText(person.getHip().toString());
        inseam.setText(person.getInseam().toString());
        comments.setText(person.getComment());
    }

    protected void finish(View view) {
        person.setName(name.getText().toString());
        person.setNeck(Float.parseFloat(neck.getText().toString()));
        person.setBust(Float.parseFloat(bust.getText().toString()));
        person.setChest(Float.parseFloat(chest.getText().toString()));
        person.setWaist(Float.parseFloat(waist.getText().toString()));
        person.setHip(Float.parseFloat(hip.getText().toString()));
        person.setInseam(Float.parseFloat(inseam.getText().toString()));
        person.setComment(comments.getText().toString());

        saveInFile();
        finish();
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
