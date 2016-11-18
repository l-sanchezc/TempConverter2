package edu.iit.lazaro.tempconverter2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;  //declare seekbar object
    TextView textView;
    TextView textViewFahrenheit;
    //declare member variables for SeekBar
    int discrete = 0;
    int start = 50;
    int start_position = 50; //progress tracker
    int temp = 0;
    //declare objects for ViewStub
    ViewStub stub;
    CheckBox checkBox;
    //declare Listview object
    ListView lv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewFahrenheit = (TextView) findViewById(R.id.textviewFahrenheit);
        //declare viewstub object
        stub = (ViewStub) findViewById(R.id.viewStub1);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //viewstub logic

        checkBox = (CheckBox) findViewById(R.id.checkBox1);
        //handle checkbox click event
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //remove objects from parent view to allow for child view
                    checkBox.setVisibility(View.GONE);
                    seekBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    textViewFahrenheit.setVisibility(View.GONE);
                    stub.setVisibility(View.VISIBLE);

                }
            }
        });

        //seekbar logic

        textView = (TextView) findViewById(R.id.textview);
        textView.setText("     Celsius at 0 degrees");  //set for default view
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setProgress(start_position);

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if (temp == 0)  //for initial view result
                    textViewFahrenheit.setText("Fahrenheit result 32 degrees");
                else
                    textViewFahrenheit.setText("Fahrenheit result "
                            + String.valueOf(discrete) + " degrees");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // TODO Auto-generated method stub
                // To convert progress passed as discrete (Fahrenheit) value
                temp = progress - start;
                discrete = (int) Math.round((((temp * 9.0) / 5.0) + 32));  //convert C to F temp
                textView.setText("     Celsius at " + temp + " degrees");
            }
        });

        //Listview logic
        String[] wkTemps = new String[]{"Monday: 32F/0C", "Tuesday: 22F/-6C", "Wednesday: 18F/-8C",
                                        "Thursday: 24F/-4C", "Friday: 28F/-2C"};

        lv = (ListView) findViewById(R.id.listView);
        @SuppressWarnings({"unchecked", "rawtypes"})
		       /*
		       * To use a basic ArrayAdapter, you just need to initialize the adapter and
		       * attach the adapter to the ListView. First, initialize the adapter...:
		       *
		       */
                ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, wkTemps);

        // Assign adapter to ListView
        lv.setAdapter(adapter);

    }//end onCreate method

}