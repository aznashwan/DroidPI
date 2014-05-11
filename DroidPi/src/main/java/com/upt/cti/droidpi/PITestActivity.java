package com.upt.cti.droidpi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upt.cti.droidpi.benchmarking.ResultGauge;
import com.upt.cti.droidpi.benchmarking.benchmarks.PIBenchmark;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;


public class PITestActivity extends Activity
{
    //the text field to be displayed at the top of the view
    private TextView text;

    //the button which will start/stop the testing phases
    private Button button;

    //the layout which contains the progress spinner [invisible by default]
    private LinearLayout progressLayout;

    //the alert builder which will display the final result
    private AlertDialog.Builder builder;

    //the benchmark object which will run the actual test
    private PIBenchmark piBenchmark;

    //auto-generated, belonging to the Activity base class
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitest);
        if(savedInstanceState==null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    //auto-generated; governs the option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pitest, menu);
        return true;
    }

    //auto-generated; handles on option click
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId();
        if(id==R.id.action_about)
        {
            this.showDescription();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    //this class constains the infused Fragment which is used on this.setContentView(...) up over in this.onCreate(...)
    public static class PlaceholderFragment extends Fragment
    {

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView=inflater.inflate(R.layout.fragment_pitest, container, false);
            return rootView;
        }
    }

    public void showDescription()
    {
        Intent intent=new Intent(this, AboutAppActivity.class);
        startActivity(intent);
    }


    //the actual place where the testing is to be done; called when this.button is tapped
    public void PITest(View view)
    {
        //initialization of all the elements to be manipulated; we don't want no NullPointerExceptions thrown now, do we? ;D
        text=(TextView) findViewById(R.id.pitest_text);
        button=(Button) findViewById(R.id.pitest_start_button);
        progressLayout=(LinearLayout) findViewById(R.id.pitest_progressbar_layout);

        piBenchmark=new PIBenchmark();

        builder=new AlertDialog.Builder(this);


        //change textView to loading text
        text.setText(R.string.pitest_wait_text);
        //set the progress layout to show loading behavior
        progressLayout.setVisibility(View.VISIBLE);
        //change button text and set to inactive
        button.setText(R.string.pitest_wait_button);
        button.setEnabled(false);


        //run the actual test from the benchmark object
        piBenchmark.runTest();


        //hide the progressbar layout
        progressLayout.setVisibility(View.INVISIBLE);
        //change textView to finished text
        text.setText(R.string.pitest_finished_text);

        //configure the result popup builder and show it on the screen
        builder.setTitle("The result:");
        builder.setMessage(piBenchmark.resultMessage()+"\n Your device's score being "+ResultGauge.PIScore(TimeUnit.convert(piBenchmark.getResult(), TimeUnit.MILI)));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });
        builder.show();

        //make button active and finalize this activity when tapped
        button.setText(R.string.back_button);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }
}
