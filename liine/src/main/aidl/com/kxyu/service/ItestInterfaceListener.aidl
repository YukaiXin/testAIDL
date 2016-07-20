// ItestInterfaceListener.aidl
package com.kxyu.service;

// Declare any non-default types here with import statements

interface ItestInterfaceListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
      void  toAccquireUpgradeError(int mErrorMsg);
      void  toAcquireUpgradeProgress(int mUpgradeProgress);
}
