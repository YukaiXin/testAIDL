// ItestInterface.aidl
package com.kxyu.service;

// Declare any non-default types here with import statements

interface ItestInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

       void appInfo (String pkName,int versonInfo);



       void setBinder(IBinder ibinder);
}
