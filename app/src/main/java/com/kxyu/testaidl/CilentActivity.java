package com.kxyu.testaidl;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.test.liine.serviceHelper;

public class CilentActivity extends AppCompatActivity implements View.OnClickListener{

    private serviceHelper mServiceHelper;


    private IBinder myBinder;
    String mDisplaytx;
    TextView mDisplay ;
    TextView mDoubleDisplay;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilent);

        findViewById(R.id.linebtn).setOnClickListener(this);
        findViewById(R.id.displaybtn).setOnClickListener(this);
        findViewById(R.id.Overbtn).setOnClickListener(this);
        findViewById(R.id.doubleDisplay).setOnClickListener(this);
        mDisplay = (TextView)findViewById(R.id.textView);
        mContext = getApplicationContext();

        mServiceHelper = new serviceHelper();
        mServiceHelper.bindToService(mContext);
        mServiceHelper.setRequestUpgradeInfoListen(new serviceHelper.RequestUpgradeInfoListen() {
            @Override
            public void toAccquireUpgradeError(int mErrorMsg) {

            }

            @Override
            public void toAcquireUpgradeProgress(int mUpgradeProgress) {

            }
        });

        mServiceHelper.getAppInfo("com.weibo.sina",1);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.linebtn:
                mServiceHelper.getAppInfo("com.weibo.sina",1);
                break;
            case R.id.displaybtn:
                mDisplay.setText("Upgrade");
                mServiceHelper.isUpgradeAPP(true);
                break;
            case R.id.doubleDisplay:
                mDisplay.setText("not upgrade");
                mServiceHelper.isUpgradeAPP(false);
                break;
            case R.id.Overbtn:
                mServiceHelper.getAppInfo("com.weibo.sina",2);
                break;
        }
    }

}
