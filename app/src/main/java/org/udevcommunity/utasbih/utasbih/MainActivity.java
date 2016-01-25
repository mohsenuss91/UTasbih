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
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * MainActivity
 *
 * The main activity of the application
 *
 * @package :
 * @author : UDevCommunity <Contact@UDevCommunity.org>
 * @license :
 * @link : https://github.com/ztickm/UTasbih
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    UTasbihSQLiteHelper database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button SalatButton = (Button) findViewById(R.id.SalatButton); // a test button to go to CounterActivity activity
        Button mode2 = (Button) findViewById(R.id.mode2);
        Button mode3 = (Button) findViewById(R.id.mode3);
        Button mode4 = (Button) findViewById(R.id.mode4);
        */
        //TextView salatType = (TextView) findViewById(R.id.salatTypeTV);

        /*database = new UTasbihSQLiteHelper(this);

        // Getting data from the DataBase as object (will be used for the graph)
        List<DayInfo> table = database.getAllInfos(1);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(2);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(3);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());

        table = database.getAllInfos(4);
        for (int i = 0; i < table.size(); i++)  textView.setText(textView.getText() + table.get(i).toString());
        */
        // listener for the test Button 'Go' to Open CounterActivity Activity
        /*SalatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the CounterActivity Activity

            }
        });
        mode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the CounterActivity Activity
                Intent counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                counterActivity.putExtra("mode",2); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                startActivity(counterActivity);
            }
        });
        mode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the CounterActivity Activity
                Intent counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                counterActivity.putExtra("mode",3); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                startActivity(counterActivity);
            }
        });
        mode4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the CounterActivity Activity
                Intent counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                counterActivity.putExtra("mode",4); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                startActivity(counterActivity);
            }
        });
        */
        //TODO Hna bdat la copie
        //Declaration Layout et Adapter
        String [] tasbih ={"اذكار الصلاة","سبحان الله","الحمد لله","الله اكبر"};
        //create Adapter
        ListAdapter adapter =new Adapter(this,tasbih);
        ListView tasbi_list =(ListView)findViewById(R.id.list1);
        tasbi_list.setAdapter(adapter);

        //create onclick methodes
        tasbi_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO mode (salat,sbhan lahh,al  hamdo lahh,lahh akbar)

                //String mode_Selected = String.valueOf(parent.getItemAtPosition(position));
                Intent counterActivity;
                switch (position){
                    case 0:
                        counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                        counterActivity.putExtra("mode", 1); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                        startActivity(counterActivity); break;
                    case 1:
                        counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                        counterActivity.putExtra("mode", 2); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                        startActivity(counterActivity); break;
                    case 2:
                        counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                        counterActivity.putExtra("mode", 3); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                        startActivity(counterActivity); break;
                    case 3:
                        counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                        counterActivity.putExtra("mode", 4); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                        startActivity(counterActivity); break;
                    default:
                        counterActivity = new Intent(MainActivity.this, CounterActivity.class);
                        counterActivity.putExtra("mode", 1); // Sending the parameter of mode of tasbih to the CounterActivity Activity
                        startActivity(counterActivity); break;
                }

            /*
                Toast.makeText(MainActivity.this, mode_Selected, Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG).show();
            */
            }


        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(MainActivity.this,"You\'re already in the home screen", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_analytics) {
            Intent analyticsActivity = new Intent(MainActivity.this, AnalyticsActivity.class);
            startActivity(analyticsActivity);
          //  Toast.makeText(MainActivity.this,"TEST", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_settings) {
            Intent settingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsActivity);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

