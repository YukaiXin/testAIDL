package com.kxyu.testaidl;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.test.liine.entity.UpgradeInfo;
import com.test.liine.serviceHelper;

public class CilentActivity extends AppCompatActivity implements View.OnClickListener{

    private serviceHelper mServiceHelper;


    private IBinder myBinder;
    String mDisplaytx;
    TextView mDisplay ;
    TextView mDoubleDisplay;
    private Context mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilent);

        findViewById(R.id.linebtn).setOnClickListener(this);
        findViewById(R.id.displaybtn).setOnClickListener(this);
        findViewById(R.id.Overbtn).setOnClickListener(this);
        mDisplay = (TextView)findViewById(R.id.textView);
        mContent = getApplicationContext();

        mServiceHelper = new serviceHelper();
        mServiceHelper.setRequestUpgradeInfoListen(mRequestUpgradeInfoListen);
        mServiceHelper.bindToServicce(mContent,"com.service","com.kxyu.service");
    }


    @Override

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.linebtn:

//                Intent mIntent = new Intent();
//                mIntent.setAction("com.servce");
////                mIntent.setPackage("com.kxyu.service");
////                mContent.bindService(mIntent,serconn,Context.BIND_AUTO_CREATE);
                break;
            case R.id.displaybtn:
//                try {
////                    mDisplay.setText(remoteService.getString());
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.doubleDisplay:

                break;
            case R.id.Overbtn:
//                if(serconn!=null)
//                {
//                    //  serconn.onServiceDisconnected();
//                 //   unbindService(serconn);
//                }
                break;
        }
    }



    private serviceHelper.RequestUpgradeInfoListen mRequestUpgradeInfoListen = new serviceHelper.RequestUpgradeInfoListen() {

        @Override
        public void  isUpgradeAPP(boolean isF){

        }
        @Override
        public void toAcquireUpgradeInfoComplete(UpgradeInfo upgradeInfo) {

        }


    };

}
