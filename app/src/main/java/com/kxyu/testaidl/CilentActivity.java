package com.kxyu.testaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kxyu.service.ItestInterface;

public class CilentActivity extends AppCompatActivity implements View.OnClickListener{

    private ItestInterface remoteService;
    String mDisplaytx;
    TextView mDisplay ;
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
    }


    ServiceConnection serconn = new ServiceConnection(){

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remoteService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = ItestInterface.Stub.asInterface(service);

            System.out.println("bind success! ");
        }

    };

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.linebtn:

                Intent mIntent = new Intent();
                mIntent.setAction("com.servce");
                mIntent.setPackage("com.kxyu.service");
                mContent.bindService(mIntent,serconn,Context.BIND_AUTO_CREATE);

                break;
            case R.id.displaybtn:

                try {
                    mDisplay.setText(remoteService.getString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.Overbtn:
                unbindService(serconn);
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(serconn);
    }
}
