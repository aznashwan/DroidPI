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
import com.upt.cti.droidpi.benchmarking.benchmarks.ArithmeticBenchmark;
import com.upt.cti.droidpi.benchmarking.timing.TimeUnit;


public class ArithmeticTestActivity extends Activity {

    private TextView text;
    private Button button;
    private LinearLayout progressLayout;
    private AlertDialog.Builder builder;

    private ArithmeticBenchmark arithmeticBenchmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetictest);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.arithmetic_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            this.showDescription();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_arithmetictest, container, false);
            return rootView;
        }
    }

    public void showDescription()
    {
        Intent intent=new Intent(this, AboutAppActivity.class);
        startActivity(intent);
    }


    public void ArithmeticTest(View view)
    {
        text=(TextView) findViewById(R.id.arithmetictest_text);
        button=(Button) findViewById(R.id.arithmetictest_start_button);
        progressLayout=(LinearLayout) findViewById(R.id.arithmetictest_progressbar_layout);

        builder=new AlertDialog.Builder(this);

        arithmeticBenchmark=new ArithmeticBenchmark();


        text.setText(R.string.arithmetictest_wait_text);
        progressLayout.setVisibility(View.VISIBLE);
        button.setText(R.string.arithmetictest_wait_button);
        button.setEnabled(false);


        arithmeticBenchmark.runTest();


        progressLayout.setVisibility(View.INVISIBLE);
        text.setText(R.string.arithmetictest_finished_text);

        builder.setTitle("Test results:");
        builder.setMessage(arithmeticBenchmark.resultMessage()+"\n The appreciated score of your device being "+ResultGauge.arithmeticScore(TimeUnit.convert(arithmeticBenchmark.getResult(), TimeUnit.MILI)));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        builder.show();

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
