package com.kxyu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by kxyu on 16-7-13.
 */
public class testService extends Service {

    private ItestInterfaceListener client;

    private IBinder binder;
    public void onCreate() {
        super.onCreate();
       Log.i("ykx","DDService onCreate........" + "Thread: " + Thread.currentThread().getName());
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


  private final ItestInterface.Stub mBinder = new ItestInterface.Stub() {

      public void getAppInfo (String pkName,int versionInfo) throws RemoteException {
        if(versionInfo == 1){
            Log.i("kxyu","1000000000");
            client.toAcquireUpgradeProgress(100);
        }else{
            client.toAcquireUpgradeProgress(2);
        }
      }

      public void setBinder(IBinder mbinder) throws RemoteException {

         client = ItestInterfaceListener.Stub.asInterface(mbinder);

      }

      public void isUpgradeAPP(boolean isF){
          if(isF == true)
          Log.i("kxyu"," 更新　:");
          else
              Log.i("kxyu"," 不更新　：");
      }

  };

}
