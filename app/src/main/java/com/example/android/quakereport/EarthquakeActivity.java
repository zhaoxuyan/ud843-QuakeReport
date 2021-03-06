/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

//        // Create a fake list of earthquake locations.
//        ArrayList<Earthquake> earthquakes = new ArrayList<>();
//
//        earthquakes.add(new Earthquake("7.2", "San Francisco", "Feb 2 2016"));
//        earthquakes.add(new Earthquake("6.1", "London", "July 2 2016"));
//        earthquakes.add(new Earthquake("1.2", "Sydney", "May 3 2016"));
//        earthquakes.add(new Earthquake("2.8", "Mexico", "June 2 2017"));
//        earthquakes.add(new Earthquake("6.2", "Paris", "September 21 2016"));



        // Find a reference to the {@link ListView} in the layout
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 查找单击的当前地震
                Earthquake currentEarthquake = adapter.getItem(position);

                // 将字符串 URL 转换为 URI 对象（以传递至 Intent 中 constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());

                // 创建一个新的 Intent 以查看地震 URI
                // ACTION_VIEW: 打开浏览器的INTENT操作
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // 发送 Intent 以启动新活动
                startActivity(websiteIntent);

            }
        });
    }
}
