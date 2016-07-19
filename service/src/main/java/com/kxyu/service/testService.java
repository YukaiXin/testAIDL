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
        return null;
    }


  private final ItestInterface.Stub mBinder = new ItestInterface.Stub() {

      public void appInfo (String pkName,int versonInfo){

      }

      public void setBinder(IBinder mbinder) throws RemoteException {

          client = ItestInterfaceListener.Stub.asInterface(mbinder);
          client.toAcquireUpgradeInfoComplete("co","s","sss","sss","ss");
      }

  };

}
