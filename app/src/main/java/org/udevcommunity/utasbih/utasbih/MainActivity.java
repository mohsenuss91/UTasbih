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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Go = (Button) findViewById(R.id.Go); // a test button to go to Counter activity

        // listener for the test Button 'Go' to Open Counter Activity
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Counter Activity
                Intent counterActivity = new Intent(MainActivity.this, Counter.class);
                // TODO - Sending the parameter of mode of tasbih to the Counter Activity
                startActivity(counterActivity);
            }
        });
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
