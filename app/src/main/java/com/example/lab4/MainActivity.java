package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static int theme = 0;
    static int pattern = 0;
    static boolean isDescriptionVisible = true;
    String [] patterns = { "Singleton", "Observer", "State", "Factory", "Composite"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (theme) {
            case 0:
                setTheme(R.style.dark);
                break;
            case 1:
                setTheme(R.style.light);
                break;
        }
        setContentView(R.layout.activity_main);

        if (isDescriptionVisible) findViewById(R.id.descriptionLayout).setVisibility(View.VISIBLE);
        else findViewById(R.id.descriptionLayout).setVisibility(View.GONE);

        TextView description = findViewById(R.id.descriptionText);
        TextView application = findViewById(R.id.applicationText);
        TextView pros = findViewById(R.id.prosText);
        TextView cons = findViewById(R.id.consText);
        Spinner spinner = findViewById(R.id.patternsSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, patterns);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ImageView image = findViewById(R.id.patternScheme);
                pattern = position;
                switch (position) {
                    case 0:
                        description.setText(R.string.singleton_description);
                        application.setText(R.string.singleton_application);
                        pros.setText(R.string.singleton_pros);
                        cons.setText(R.string.singleton_cons);
                        image.setImageResource(R.drawable.singleton);
                        break;
                    case 1:
                        description.setText(R.string.observer_description);
                        application.setText(R.string.observer_application);
                        pros.setText(R.string.observer_pros);
                        cons.setText(R.string.observer_cons);
                        image.setImageResource(R.drawable.observer);
                        break;
                    case 2:
                        description.setText(R.string.state_description);
                        application.setText(R.string.state_application);
                        pros.setText(R.string.state_pros);
                        cons.setText(R.string.state_cons);
                        image.setImageResource(R.drawable.state);
                        break;
                    case 3:
                        description.setText(R.string.factory_description);
                        application.setText(R.string.factory_application);
                        pros.setText(R.string.factory_pros);
                        cons.setText(R.string.factory_cons);
                        image.setImageResource(R.drawable.factory);
                        break;
                    case 4:
                        description.setText(R.string.composite_description);
                        application.setText(R.string.composite_application);
                        pros.setText(R.string.composite_pros);
                        cons.setText(R.string.composite_cons);
                        image.setImageResource(R.drawable.composite);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.styles_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.styleDark:
                if (theme != 0) {
                    theme = 0;
                    recreate();
                }
                return true;
            case R.id.styleLight:
                if (theme != 1) {
                    theme = 1;
                    recreate();
                }
                return true;
            case R.id.descriptionCheck:
                View description = findViewById(R.id.descriptionLayout);
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                TextView dialogText = dialog.findViewById(R.id.dialogText);

                if (isDescriptionVisible) {
                    isDescriptionVisible = false;
                    description.setVisibility(View.GONE);
                    item.setChecked(false);
                    dialogText.setText("Описание паттерна убрано!");
                }
                else {
                    isDescriptionVisible = true;
                    description.setVisibility(View.VISIBLE);
                    item.setChecked(true);
                    dialogText.setText("Описание паттерна показано!");
                }

                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}