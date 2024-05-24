package com.example.a21010398;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditBridgeActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.a21010398.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.a21010398.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.a21010398.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.a21010398.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bridge);

        editTextTitle = findViewById(R.id.edit_title);
        editTextDescription = findViewById(R.id.edit_description);
        spinner = findViewById(R.id.priority_picker);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Bridge");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            spinner.setPrompt(intent.getStringExtra(EXTRA_PRIORITY));
        } else {
            setTitle("Add Bridge");
        }
    }

    private void saveBridge() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String priority = spinner.getSelectedItem().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please add both a Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_bridge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_bridge:
                saveBridge();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}