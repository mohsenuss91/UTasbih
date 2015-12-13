/**
 * MainActivity.java
 *
 * Containing the main activity (main class)
 *
 * @author : UDevCommunity <Contact@UDevCommunity.com>
 */
package org.udevcommunity.utasbih.utasbih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * MainActivity
 *
 * The main activity of the application
 *
 * @package :
 * @author : UDevCommunity <Contact@UDevCommunity.com>
 * @license :
 * @link : https://github.com/ztickm/UTasbih
 */
public class MainActivity extends AppCompatActivity
{
    UTasbihSQLiteHelper database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Go = (Button) findViewById(R.id.Go); // a test button to go to Counter activity
        Button mode2 = (Button) findViewById(R.id.mode2);
        Button mode3 = (Button) findViewById(R.id.mode3);
        Button mode4 = (Button) findViewById(R.id.mode4);
        TextView textView = (TextView) findViewById(R.id.textView);
        Button analytics = (Button) findViewById(R.id.analytics);

        database = new UTasbihSQLiteHelper(this);

        // Getting data from the DataBase as object (will be used for the graph)
        List<DayInfo> table = database.getAllInfos(1);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(2);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(3);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(4);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        // listener for the test Button 'Go' to Open Counter Activity
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Counter Activity
                Intent counterActivity = new Intent(MainActivity.this, Counter.class);
                counterActivity.putExtra("mode",1); // Sending the parameter of mode of tasbih to the Counter Activity

                startActivity(counterActivity);
            }
        });
        mode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Counter Activity
                Intent counterActivity = new Intent(MainActivity.this, Counter.class);
                counterActivity.putExtra("mode",2); // Sending the parameter of mode of tasbih to the Counter Activity
                startActivity(counterActivity);
            }
        });
        mode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Counter Activity
                Intent counterActivity = new Intent(MainActivity.this, Counter.class);
                counterActivity.putExtra("mode",3); // Sending the parameter of mode of tasbih to the Counter Activity
                startActivity(counterActivity);
            }
        });
        mode4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Counter Activity
                Intent counterActivity = new Intent(MainActivity.this, Counter.class);
                counterActivity.putExtra("mode",4); // Sending the parameter of mode of tasbih to the Counter Activity
                startActivity(counterActivity);
            }
        });

        analytics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View v){
                // Opening the Counter Activity
                Intent analyticsActivity = new Intent(MainActivity.this, Analytics.class);
                startActivity(analyticsActivity);
            }
        }

        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
