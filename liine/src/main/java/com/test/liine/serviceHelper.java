package com.test.liine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.kxyu.service.ItestInterface;
import com.kxyu.service.ItestInterfaceListener;
import com.test.liine.entity.UpgradeInfo;

import java.util.HashMap;


/**
 * Created by kxyu on 16-7-18.
 */
public class serviceHelper {

    private HashMap<Context,ServiceConnection> sConnectionMap;
    private Context mContext;
    private RequestUpgradeInfoListen mRequestUpgradeInfoListen;

    public interface RequestUpgradeInfoListen {
        public void  toAcquireUpgradeInfoComplete(UpgradeInfo upgradeInfo);
    }

    private ItestInterface remoteService;
    private IBinder myBinder;


    public void bindToService(Context ctx){
        mContext = ctx;
        Intent mIntent = new Intent();


//     //   ContextWrapper cw = new ContextWrapper(realActivity);
//      //  Intent mIntent = new Intent();
//        mIntent.setClassName("", "com.kxyu.service.testService");
//        cw.startService(mIntent);

        mIntent.setAction("com.service");
        mIntent.setPackage("com.kxyu.service");
        ctx.bindService(mIntent,mServiceConnection,Context.BIND_AUTO_CREATE);
        Log.i("kxyu","ss");
    }

    private final ItestInterfaceListener.Stub mBinder = new ItestInterfaceListener.Stub() {

        @Override
        public void toAcquireUpgradeInfoComplete(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress) throws RemoteException {

            UpgradeInfo mUpgradeInfo = new UpgradeInfo(pkgName,versionInfo,rtNewApkMD5,upPathAddress,newApkAddress);
            mRequestUpgradeInfoListen.toAcquireUpgradeInfoComplete(mUpgradeInfo);
        }
    };

    public void unbindFromService(Context ctx) {

        ctx.unbindService(mServiceConnection);
    }

    public  void appInfo(String mPkgName, int mVersionInfo) {
        if(remoteService == null){
            bindToService(mContext);
        }

        try {
            remoteService.appInfo(mPkgName,mVersionInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setRequestUpgradeInfoListen(RequestUpgradeInfoListen requestUpgradeInfoListen) {
        this.mRequestUpgradeInfoListen = requestUpgradeInfoListen;
    }

    public void isUpgradeAPP(boolean isF)
    {
        try {
            remoteService.isUpgradeAPP(isF);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    ServiceConnection mServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remoteService = null;

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = ItestInterface.Stub.asInterface(service);
            Log.i("kxyu","  seccusss !!!!");
            try {
                remoteService.setBinder(mBinder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
