package com.example.a21010398;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    private bridgeViewModel bridgeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addBridgeButton = findViewById(R.id.button_add_bridge);
        addBridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditBridgeActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        bridgeAdapter adapter = new bridgeAdapter();
        recyclerView.setAdapter(adapter);

        bridgeViewModel = new ViewModelProvider(this).get(bridgeViewModel.class);
        bridgeViewModel.getAllBridges().observe(this, new Observer<List<bridge>>() {
            @Override
            public void onChanged(@Nullable List<bridge> bridges) {
                adapter.setBridges(bridges);
            }
        });

        adapter.SetItemOnClickListener(new bridgeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(bridge bridge) {
                Intent intent = new Intent(MainActivity.this, AddEditBridgeActivity.class);
                intent.putExtra(AddEditBridgeActivity.EXTRA_ID, bridge.getId());
                intent.putExtra(AddEditBridgeActivity.EXTRA_TITLE, bridge.getName());
                intent.putExtra(AddEditBridgeActivity.EXTRA_DESCRIPTION, bridge.getDescription());
                intent.putExtra(AddEditBridgeActivity.EXTRA_PRIORITY, bridge.getStatus());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST && resultCode == RESULT_OK) {

            String title = data.getStringExtra(AddEditBridgeActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditBridgeActivity.EXTRA_DESCRIPTION);
            String priority = data.getStringExtra(AddEditBridgeActivity.EXTRA_PRIORITY);

            bridge bridge = new bridge(title, description, priority);
            bridgeViewModel.insert(bridge);

            Toast.makeText(this, "Bridge Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditBridgeActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Bridge Cannot be Updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditBridgeActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditBridgeActivity.EXTRA_DESCRIPTION);
            String priority = data.getStringExtra(AddEditBridgeActivity.EXTRA_PRIORITY);

            bridge bridge = new bridge(title, description, priority);
            bridge.setId(id);
            bridgeViewModel.update(bridge);

            Toast.makeText(this, "Bridge Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bridge Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}