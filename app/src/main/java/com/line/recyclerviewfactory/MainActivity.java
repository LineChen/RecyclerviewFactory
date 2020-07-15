package com.line.recyclerviewfactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.line.recyclerview.factory.MultiTypeListView;
import com.line.recyclerview.factory.factory.MultiTypeListViewFactory;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    private MultiTypeListView listView;
    private SeaFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fl_content);
        factory = new SeaFactory(this);
        listView = MultiTypeListViewFactory.getMultiTypeListView(factory);
        frameLayout.addView(listView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }


    public void onRefreshClick(View view) {
        listView.refresh();
        Log.e("MainActivity", "onRefreshClick");
    }

    public void onRemoveClick(View view) {
        factory.remove(2);
    }

    public void onInsertClick(View view) {
        factory.insert(3);
    }
}
