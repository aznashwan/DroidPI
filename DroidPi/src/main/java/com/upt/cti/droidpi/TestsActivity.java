package com.upt.cti.droidpi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TestsActivity extends Activity
{

    Button piButton, threadsButton, recursionButton, arithmeticsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        if(savedInstanceState==null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        piButton=(Button) findViewById(R.id.pi_button);
        threadsButton=(Button) findViewById(R.id.threads_button);
        recursionButton=(Button) findViewById(R.id.recursion_button);
        arithmeticsButton=(Button) findViewById(R.id.arithmetics_button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tests, menu);
        return true;
    }

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
    public static class PlaceholderFragment extends Fragment
    {

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView=inflater.inflate(R.layout.fragment_tests, container, false);
            return rootView;
        }
    }

    public void showDescription()
    {
        Intent intent=new Intent(this, AboutAppActivity.class);
        startActivity(intent);
    }


    public void piTest(View view)
    {
        Intent intent=new Intent(this, PITestActivity.class);
        startActivity(intent);
    }

    public void threadsTest(View view)
    {
        Intent intent=new Intent(this, ThreadsTestActivity.class);
        startActivity(intent);
    }

    public void recursionTest(View view)
    {
        Intent intent=new Intent(this, RecursionTestActivity.class);
        startActivity(intent);
    }

    public void arithmeticsTest(View view)
    {
        Intent intent=new Intent(this, ArithmeticTestActivity.class);
        startActivity(intent);
    }
}