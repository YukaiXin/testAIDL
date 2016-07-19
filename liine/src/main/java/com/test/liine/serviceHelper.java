package com.test.liine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.kxyu.service.ItestInterface;
import com.kxyu.service.ItestInterfaceListener;
import com.test.liine.entity.UpgradeInfo;


/**
 * Created by kxyu on 16-7-18.
 */
public class serviceHelper {

    private RequestUpgradeInfoListen mRequestUpgradeInfoListen;

    public interface RequestUpgradeInfoListen {
        public void  toAcquireUpgradeInfoComplete(UpgradeInfo upgradeInfo);
        public void  isUpgradeAPP(boolean isF);
    }

    private ItestInterface remoteService;
    private IBinder myBinder;

    public void bindToServicce(Context mCtx,String mAction,String mServicePackage){

        Intent mIntent = new Intent();
        mIntent.setAction(mAction);
        mIntent.setPackage(mServicePackage);
//        mIntent.setAction("com.servce");
//        mIntent.setPackage("com.kxyu.service");
        if(mCtx.bindService(mIntent,serconn,Context.BIND_AUTO_CREATE) == false){
            Toast.makeText(mCtx, "服务绑定失败", Toast.LENGTH_SHORT).show();
        }
    }

    private final ItestInterfaceListener.Stub mBinder = new ItestInterfaceListener.Stub() {

        @Override
        public void isUpgradeAPP(boolean isF){

        }
        @Override
        public void toAcquireUpgradeInfoComplete(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress) throws RemoteException {

            UpgradeInfo mUpgradeInfo = new UpgradeInfo(pkgName,versionInfo,rtNewApkMD5,upPathAddress,newApkAddress);
            mRequestUpgradeInfoListen.toAcquireUpgradeInfoComplete(mUpgradeInfo);
        }

    };

    public void setRequestUpgradeInfoListen(RequestUpgradeInfoListen requestUpgradeInfoListen) {
        this.mRequestUpgradeInfoListen = requestUpgradeInfoListen;
    }
    ServiceConnection serconn = new ServiceConnection(){

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remoteService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = ItestInterface.Stub.asInterface(service);
            System.out.println("bind success  !!! ");
            try {
                remoteService.setBinder(mBinder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
