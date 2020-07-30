package com.project.matrix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.res.Configuration;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectListener,
        GridFragment.OnItemSelectListener {

    private ListFragment listFragment;
    private GridFragment gridFragment;

    @Override
    public void onItemSelected(int position) {
        if (!isTablet()) {
            Fragment fragment = GridFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.list_container, fragment).addToBackStack(null).commit();
//            Intent intent = new Intent(this, EventGridActivity.class);
//            intent.putExtra("position", position);
//            startActivity(intent);
        } else {
            gridFragment.onItemSelected(position);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show different fragments based on screen size.

        //add list view
        if (getSupportFragmentManager().findFragmentById(R.id.list_container) == null) {
            listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.list_container, listFragment).commit();
        }

        //add Gridview
        if (getSupportFragmentManager().findFragmentById(R.id.grid_container) == null && isTablet()) {
            gridFragment = new GridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.grid_container, gridFragment).commit();
        }


    }



    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void onCommentSelected(int position) {
        listFragment.onItemSelected(position);
    }


}
