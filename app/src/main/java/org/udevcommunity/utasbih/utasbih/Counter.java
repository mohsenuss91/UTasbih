/**
 * Counter.java
 *
 * Counter Activity
 *
 * @author :   UDevCommunity <Contact@UDevCommunity.com>
 */

package org.udevcommunity.utasbih.utasbih;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Vibrator;
/**
 * Counter
 *
 * Counter Activity
 *
 * @package :
 * @author :  UDevCommunity <Contact@UDevCommunity.com>
 * @license :
 * @link : https://github.com/ztickm/UTasbih
 */
public class Counter extends Activity
{
    int counter = 0;                // counter used to stock the number of tasbih
    TextView tasbihText = null;     // Text of Tasbih ex: 'الحمد لله'
    TextView counterView = null;       // TextView of the counter 'used to display the number in counter'
    Button incrementCounter = null;     // Incrementing Button for the counter
    LinearLayout principalLayout = null;   // principal layout 'used to set the color when rich 33,66,99,100 etc ...
    Vibrator vibr_tasbih = null;  // create vibr_tasbih objet from vibrator class

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);

        // getting Views from the resources
        tasbihText = (TextView) findViewById(R.id.textTasbih);
        counterView = (TextView) findViewById(R.id.counter);
        incrementCounter = (Button) findViewById(R.id.incCounter);
        principalLayout = (LinearLayout) findViewById(R.id.layoutCounter);
        vibr_tasbih = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // onClick listener for the Incrementing Button "incrementCounter" to
        // execute the incrementing function "incCounterBasic()"
        incrementCounter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO verify the mode of tasbih (Salat is token as default)

                // call the incCounterSalat function
                incCounterSalat();
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

    /**
     * incCounterSalat.
     *
     * incCounterSalat.
     *
     * @param : none.
     * @expectedException : none.
     * @return void.
     * @link  https://github.com/ztickm/UTasbih
     */
    public void incCounterSalat()
    {
        // Conditions to set the Text+Backgtound when rich 33,66,99,100 .
        if (this.counter == 33)
        {
            vibr_tasbih.vibrate(500); // Vibrate for 500 milliseconds 
            tasbihText.setText("الحمد لله");
            principalLayout.setBackgroundColor(Color.rgb(103, 58, 183));
        }
        else if (this.counter == 66)
        {
            vibr_tasbih.vibrate(500); // Vibrate for 500 milliseconds 
            tasbihText.setText("الله أكبر");
            principalLayout.setBackgroundColor(Color.rgb(255, 152, 0));
        }
        else if (this.counter == 99)
        {
            vibr_tasbih.vibrate(500); // Vibrate for 500 milliseconds 
            tasbihText.setText("لا إله إلا الله وحده لا شريك له له الملك و له الحمد و هو على كل شيئ قدير");
            principalLayout.setBackgroundColor(Color.rgb(121, 85, 72));
            incrementCounter.setText("نهاية الأذكار");
        }
        // Return to the main Activty
        else if (this.counter == 100)
        {
            vibr_tasbih.vibrate(1000); //Vibrate for 1000 milliseconds  I choice 1000 to fill the difference --  end of tasbih
            // Opening the Main Activity
            Intent counterActivity = new Intent(Counter.this, MainActivity.class);
            startActivity(counterActivity);
        }

        // Incrementing the counter
        this.counter++;

        // Set the new value of counter to the Textview "counterView"
        this.counterView.setText(Integer.toString(this.counter));
    }
}
