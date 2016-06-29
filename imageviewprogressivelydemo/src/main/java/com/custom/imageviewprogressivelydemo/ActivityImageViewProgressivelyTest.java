package com.custom.imageviewprogressivelydemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by tandewei on 2015/11/28.
 */
public class ActivityImageViewProgressivelyTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState,PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,persistentState);
        Log.d("TEST","1111111111111111");
        setContentView(R.layout.activity_imageviewprogressively_test);

    }

}
