package sg.edu.rp.c346.id20019652.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd;
    Button btnClear;
    ListView lvToDo;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    Spinner spnAddClear;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Simple To Do");

        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.listViewToDo);

        spnAddClear = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etToDo.getText().toString();
                //add task to arraylist
                alToDo.add(newTask);
                //call adapter to update, notify there's a data change
                aaToDo.notifyDataSetChanged();
                etToDo.setText("");
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear tasks from arraylist
                alToDo.clear();
                //call adapter to update, notify there's a data change
                aaToDo.notifyDataSetChanged();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index= Integer.parseInt(etToDo.getText().toString());

                if (alToDo.size() == 0) {
                    String noItemMssg = "You don't have any task to remove";
                    Toast.makeText(getApplicationContext(), noItemMssg , Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    if (index >= alToDo.size()) {
                        String noIndexMssg = "Wrong index number";
                        Toast.makeText(MainActivity.this, noIndexMssg, Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        alToDo.remove(index);
                        aaToDo.notifyDataSetChanged();
                        etToDo.setText(null);
                    }
                }
            }
        });
        spnAddClear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}