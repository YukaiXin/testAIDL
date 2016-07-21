package com.test.liine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.kxyu.service.ItestInterface;
import com.kxyu.service.ItestInterfaceListener;


/**
 * Created by kxyu on 16-7-18.
 */
public class serviceHelper {

    private Context mContext;
    private ServiceConnectionListen mServiceConnectionListen;
    private RequestUpgradeInfoListen mRequestUpgradeInfoListen;

    public interface RequestUpgradeInfoListen {
        public void  toAccquireUpgradeError(int mErrorMsg);
        public void  toAcquireUpgradeProgress(int mUpgradeProgress);
    }

    public interface ServiceConnectionListen {
        public void  ConnectionSeccuss(ItestInterface remoteService) ;
    }

    private  ItestInterface remoteService;

    public void bindToService(Context ctx,ServiceConnectionListen serviceConnectionListen){
        mContext = ctx;
        Intent mIntent = new Intent();

        mIntent.setAction(Constants.SERVICE_ACTION);
        mIntent.setPackage(Constants.SERVICE_PACKAGE_NAME);
        if(ctx.bindService(mIntent,mServiceConnection,Context.BIND_AUTO_CREATE) == false){
            ctx.bindService(mIntent,mServiceConnection,Context.BIND_AUTO_CREATE);
        }
        this.mServiceConnectionListen = serviceConnectionListen;
    }

    private void bindToService(Context ctx){
        Intent mIntent = new Intent();

        mIntent.setAction(Constants.SERVICE_ACTION);
        mIntent.setPackage(Constants.SERVICE_PACKAGE_NAME);
        ctx.bindService(mIntent,mServiceConnection,Context.BIND_AUTO_CREATE);
    }

    private final ItestInterfaceListener.Stub mBinder = new ItestInterfaceListener.Stub() {

        public void  toAccquireUpgradeError(int mErrorMsg){
            mRequestUpgradeInfoListen.toAccquireUpgradeError(mErrorMsg);
        }

        public void  toAcquireUpgradeProgress(int mUpgradeProgress){
            mRequestUpgradeInfoListen.toAcquireUpgradeProgress(mUpgradeProgress);
        }
    };

    public void unbindFromService(Context ctx) {

        ctx.unbindService(mServiceConnection);
    }

    public  void getAppInfo(String mPkgName, int mVersionInfo) {

        if(remoteService == null) bindToService(mContext);

        try {
            remoteService.getAppInfo(mPkgName,mVersionInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setRequestUpgradeInfoListen(RequestUpgradeInfoListen requestUpgradeInfoListen) {
        this.mRequestUpgradeInfoListen = requestUpgradeInfoListen;
    }

    public void isUpgradeAPP(boolean isF)
    {
        if(remoteService == null) bindToService(mContext);

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

            try {
                remoteService.setBinder(mBinder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

           mServiceConnectionListen.ConnectionSeccuss(remoteService);
        }
    };

}
