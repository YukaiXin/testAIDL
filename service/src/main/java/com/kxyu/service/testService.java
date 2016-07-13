package com.kxyu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lewa on 16-7-13.
 */
public class testService extends Service {

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
      @Override
      public String getString() throws RemoteException {


          return " This is Ok!!!";
      }
  };




}
