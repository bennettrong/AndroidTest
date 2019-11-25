package com.android.testapp;
import android.util.Log;
public class TestClass {
    public static void func1(){
        String classPath = System.getProperty("java.class.path", ".");
        String librarySearchPath = System.getProperty("java.library.path", "");
        Log.d("rongqingyu","classPath="+classPath);
        Log.d("rongqingyu","librarySearchPath="+librarySearchPath);
    }
}