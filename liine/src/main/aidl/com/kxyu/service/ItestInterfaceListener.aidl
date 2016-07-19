// ItestInterfaceListener.aidl
package com.kxyu.service;

// Declare any non-default types here with import statements

interface ItestInterfaceListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
      void isUpgradeAPP(boolean isF);
      void  toAcquireUpgradeInfoComplete(String pkgName, String versionInfo, String rtNewApkMD5, String upPathAddress, String newApkAddress);
}
