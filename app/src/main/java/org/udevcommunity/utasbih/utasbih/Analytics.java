/**
 * Analytics
 *
 * This Class is used to render the Analytics charts for each mode
 *
 * @package :
 * @author : UDevCommunity <Contact@UDevCommunity.com>
 * @license :
 * @link : https://github.com/ztickm/UTasbih
 */
package org.udevcommunity.utasbih.utasbih;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;

public class Analytics extends AppCompatActivity {

    private UTasbihSQLiteHelper database = null;
    private int SALAT_MODE = 1;
    private int TASBIH_MODE = 2;
    private int TAHMID_MODE = 3;
    private int TAKBEER_MODE = 4;

    private String MODE_TASBIH_LEGEND = "تسبيح";
    private String MODE_TAHMID_LEGEND = "تحميد";
    private String MODE_TAKBEER_LEGEND = "تكبير";

    private BarChart salatChart;
    private String salatChartDescription = "تسابيح الصلاة";

    private LineChart freeModeChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        //retrieve tasbih data from the database
        database = new UTasbihSQLiteHelper(this);

        // Getting data from the DataBase as object (will be used for the graph)
        List<DayInfo> salatModeList = database.getAllInfos(SALAT_MODE);
        List<DayInfo> tasbihModeList = database.getAllInfos(TASBIH_MODE);
        List<DayInfo> tahmidModeList = database.getAllInfos(TAHMID_MODE);
        List<DayInfo> takbeerModeList = database.getAllInfos(TAKBEER_MODE);

        //init BarChart for Salat mode
        salatChart = (BarChart) findViewById(R.id.salatTasbihChart);
        BarData salatData = new BarData( getSalatChartXAxisValues(salatModeList), getSalatChartDataSet(salatModeList) );
        salatChart.setData(salatData);

        //set Description
        salatChart.setDescription(salatChartDescription);
        //set the chart's Xaxis
        setSalatChartXAxis();
        setSalatChartLegends();
        //refresh BarChart
        salatChart.invalidate();

        //define a LineChart for Free tasbih mode
        freeModeChart = (LineChart) findViewById(R.id.freeModeTasbihChart);
        //set the Lines for each mode
        freeModeChart.setData(getLineData(tasbihModeList, tahmidModeList,takbeerModeList));
        freeModeChart.invalidate();

    }

    /**
     * getSalatChartXAxisValues
     *
     *
     *
     * @param : a list of DayInfo.
     * @expectedException : none.
     * @return List of xAxis string values for BarChart
     * @link  https://github.com/ztickm/UTasbih
     */
    private List<String> getSalatChartXAxisValues(List<DayInfo> xValues) {

        List<String> xAxis = new ArrayList<>();
        for (int i=0;i<xValues.size();i++) {
            xAxis.add(String.valueOf(xValues.get(i).getDay()));
        }
        return xAxis;
    }

    /**
     * getSalatChartDataSet
     *
     *
     *
     * @param : a list of DayInfo.
     * @expectedException : none.
     * @return ArrayList of DataSet entries for BarChart
     * @link  https://github.com/ztickm/UTasbih
     */

    private ArrayList<BarDataSet> getSalatChartDataSet(List<DayInfo> salahList) {

        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        for (int i=0;i<salahList.size();i++) {

            //Convert String values of the List to flaot
            String tasbihValue = String.valueOf(salahList.get(i).getNumber());
            // create Bar entries from tasbih values
            Float tasbihEntry= Float.parseFloat(tasbihValue);
            BarEntry entry = new BarEntry(tasbihEntry,i);
            valueSet1.add(entry);
        }

        //create BarChart data set with the values extracted from the tasbih list
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "تسابيح");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        //add BarChart Dataset to an ArrayList
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    /**
     * setSalatChartXAxisValues
     *
     *  set xAxis parameters
     *
     * @param : none.
     * @expectedException : none.
     * @return void
     * @link  https://github.com/ztickm/UTasbih
     */

    private void setSalatChartXAxis (){

        XAxis xAxis = salatChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

    /**
     * setSalatChartLegends
     *
     * set Legends parameters
     *
     * @param : none
     * @expectedException : none.
     * @return void
     * @link  https://github.com/ztickm/UTasbih
     */
    private void setSalatChartLegends(){
        Legend l = salatChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setTextSize(12f);
        l.setTextColor(BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
        l.setExtra(new int[]{GREEN}, new String[]{"khtok el3awra"});

    }
    /**
     * getLineData
     *
     *
     *
     * @param : 3 lists of DayInfo for each mode.
     * @expectedException : none.
     * @return LineData for LineChart
     * @link  https://github.com/ztickm/UTasbih
     */

    private LineData getLineData(List<DayInfo> tasbih, List<DayInfo> tahmid,
            List<DayInfo> takbeer){

        //get the numbers of days
        List<DayInfo> date = getDates(tasbih, tahmid,takbeer);

        //init entries ArrayLists for each mode
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp3 = new ArrayList<Entry>();

        //init xAxis values ArrayList
        ArrayList<String> xVals = new ArrayList<String>();


        String freemode1Value;
        String freemode2Value;
        String freemode3Value;
        int datesCount = date.size();
        // have equal lists size
        for (int i=0;i<date.size();i++) {

            //convert each item of list to string then to float
            if ( datesCount > tasbih.size() || tasbih.size()==0)  freemode1Value = "0";
            else freemode1Value= String.valueOf(tasbih.get(i).getNumber());

            if ( datesCount > tahmid.size() || tahmid.size()==0) freemode2Value = "0";
            else freemode2Value = String.valueOf(tahmid.get(i).getNumber());

            if( datesCount > takbeer.size() || takbeer.size() ==0) freemode3Value = "0";
            else freemode3Value = String.valueOf(takbeer.get(i).getNumber());

            Float freeMode1Entry = Float.parseFloat(freemode1Value);
            Float freeMode2Entry = Float.parseFloat(freemode2Value);
            Float freeMode3Entry = Float.parseFloat(freemode3Value);

            //Make a new entry and assigned to a Xaxis value (i)
            Entry freeMode1Point = new Entry(freeMode1Entry, i);
            Entry freeMode2Point = new Entry(freeMode2Entry, i);
            Entry freeMode3Point = new Entry(freeMode3Entry, i);

            //add each point to its specific line mode
            valsComp1.add(freeMode1Point);
            valsComp2.add(freeMode2Point);
            valsComp3.add(freeMode3Point);

            //xVals for date
            xVals.add(String.valueOf(date.get(i).getDay()));

        }
        //set a line data set for each mode by the lists created above
        LineDataSet setComp1 = new LineDataSet(valsComp1, MODE_TASBIH_LEGEND);
        LineDataSet setComp2 = new LineDataSet(valsComp2, MODE_TAHMID_LEGEND);
        LineDataSet setComp3 = new LineDataSet(valsComp3, MODE_TAKBEER_LEGEND);

        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);

        //set a color for each mode
        setComp1.setColor(BLACK);
        setComp2.setColor(Color.BLUE);
        setComp3.setColor(Color.RED);

        //create a list of data sets
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();

        //add each Line set to the list
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        dataSets.add(setComp3);

        //create a lines data and return it
        LineData data = new LineData(xVals, dataSets);
        return data;
    }

    /**
     * getDates: return the list with the max
     * date entries
     *
     *
     * @param : 3 lists of DayInfo for each mode.
     * @expectedException : none.
     * @return List of xAxis string values for BarChart
     * @link  https://github.com/ztickm/UTasbih
     */
    private List<DayInfo> getDates(List<DayInfo> list1,List<DayInfo> list2,
            List<DayInfo>list3){

        // list of all modes lists sizes
        List<Integer> sizes= new ArrayList<>();
        sizes.add(list1.size());
        sizes.add(list2.size());
        sizes.add(list3.size());

        List<DayInfo> dateList = new ArrayList<DayInfo>();
        //return the list with the max size
        if (Collections.max(sizes)==list1.size()) dateList= list1;
        else if (Collections.max(sizes)==list2.size()) dateList = list2;
        else if (Collections.max(sizes)==list3.size()) dateList = list3;

        return dateList;

    }

}
