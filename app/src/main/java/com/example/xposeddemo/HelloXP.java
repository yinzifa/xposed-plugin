package com.example.xposeddemo;

import android.app.ActivityManager;
import android.location.LocationManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HelloXP implements IXposedHookLoadPackage {
    public static final String SHARK = "SharkChilli";

    private void hook_method(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback){
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            Log.i(SHARK, "hook getDeviceId...");
            XposedBridge.log(e);
        }
    }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.i(SHARK, "pkg:"+lpparam.packageName);
        //4G获取IP- getHostAddress
        XposedHelpers.findAndHookMethod(
                java.net.InetAddress.class.getName(), // 需要hook的方法所在类的完整类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "getHostAddress",                     // 需要hook的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getHostAddress()获取了IP";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getHostAddress()","检测到 调用getHostAddress()获取了IP");
                    }
                }
        );
        //wifi获取IP- getConnectionInfo
        XposedHelpers.findAndHookMethod(
                android.net.wifi.WifiManager.class.getName(), // 需要hook的方法所在类的完整类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "getConnectionInfo",                     // 需要hook的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getConnectionInfo()获取了IP";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getConnectionInfo()","检测到 调用getConnectionInfo()获取了IP");
                    }
                }
        );

        //imei- getDeviceId-无参 固定格式
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(), // 需要hook的方法所在类的完整类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "getDeviceId",                     // 需要hook的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getDeviceId()获取了imei";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getDeviceId()","检测到 调用getDeviceId()获取了imei");
                    }
                }
        );

        //imei-getDeviceId-有参
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getDeviceId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getDeviceId(int)获取了imei";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getDeviceId(1)","检测到 调用getDeviceId(int)获取了imei");
                    }
                }
        );


        //imei- getImei-无参 固定格式
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(), // 需要hook的方法所在类的完整类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "getImei",                     // 需要hook的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getImei()获取了imei";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getImei()","检测到 调用getImei()获取了imei");
                    }
                }
        );

        //imei-getDeviceId-有参
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getImei",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getImei(int)获取了imei";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getImei(1)","检测到 调用getImei(int)获取了imei");
                    }
                }
        );


        //imsi
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getSubscriberId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getSubscriberId获取了imsi";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getSubscriberId()","检测到 调用getSubscriberId获取了imsi");
                    }
                }
        );

        //mac
        XposedHelpers.findAndHookMethod(
                android.net.wifi.WifiInfo.class.getName(),
                lpparam.classLoader,
                "getMacAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getMacAddress()获取了mac地址";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("调用getMacAddress()","检测到 调用getMacAddress()获取了mac地址");
                    }
                }

        );

        //mac
        XposedHelpers.findAndHookMethod(
                java.net.NetworkInterface.class.getName(),
                lpparam.classLoader,
                "getHardwareAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getHardwareAddress()获取了mac地址";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getHardwareAddress()","检测到 调用getHardwareAddress()获取了mac地址");
                    }
                }

        );
/*
        //android id  https://www.52pojie.cn/thread-1063915-1-1.html
        //android.provider.Settings.System.getString
        XposedHelpers.findAndHookMethod(android.provider.Settings.System.class.getName(),
                lpparam.classLoader,
                "getString",
                ContentResolver.class,
                String.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {

                        if (param == null || param.args == null) {
                            Log.i(SHARK, "getString 参数为空！！！！！");
                            return;
                        }
                        String log = "检测到 调用Settings.Secure.getString获取了" + param.args[1];
                        Log.i(SHARK, log);
                        XposedBridge.log(log);

                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("AndroidId()","检测到 调用Settings.Secure.getString获取了" + param.args[1]);
                    }
                });*/


        //获取定位
        XposedHelpers.findAndHookMethod(
                LocationManager.class.getName(),
                lpparam.classLoader,
                "getLastKnownLocation",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getLastKnownLocation获取了GPS地址";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getLastKnownLocation()","检测到 调用getLastKnownLocation获取了GPS地址");
                    }
                }
        );

        //获取任务列表 getRecentTasks
        XposedHelpers.findAndHookMethod(
                ActivityManager.class.getName(),
                lpparam.classLoader,
                "getRecentTasks",
                int.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getRecentTasks获取任务列表";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getRecentTasks()","检测到 调用getRecentTasks获取任务列表");
                    }
                }
        );

        //获取任务列表 getRunningTasks
        XposedHelpers.findAndHookMethod(
                ActivityManager.class.getName(),
                lpparam.classLoader,
                "getRunningTasks",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String log = "检测到 调用getRunningTasks（0）获取任务列表";
                        Log.i(SHARK, log);
                        XposedBridge.log(log);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        printStackTree("getRunningTasks()","检测到 调用getRunningTasks（0）获取任务列表");
                    }
                }
        );
    }
    public void printStackTree(String method, String discribe) {
        XposedBridge.log("***********************************");
        XposedBridge.log(method + " 堆栈:---------------start----------------");
        Log.i(SHARK, "***********************************");
        Log.i(SHARK, method + " 堆栈:---------------start----------------");
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        String format = discribe;
        format += "\n"+method + " 堆栈:---------------start----------------";
        for (int i = 0; i < stackElements.length; i++) {
            StackTraceElement element = stackElements[i];
            format += "\n"+String.format("at %s.%s(%s:%d)",
                    element.getClassName(), element.getMethodName(), element
                            .getFileName(), element.getLineNumber());
        }
        format += "\n"+method + " 堆栈:---------------end----------------"+"\n"+"\n";
        XposedBridge.log(format);
        Log.i(SHARK, format);
        MyOkHttp.postFormAsync("http://10.1.9.79:8099/api/uploadDynamicResult",format);
        XposedBridge.log(method + " 堆栈:---------------end----------------");
        Log.i(SHARK, method + " 堆栈:---------------end----------------");
    }

        /*//检测DeviceId
        hook_method("android.telephony.TelephonyManager", lpp.classLoader, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                String log = "检测到 getDeviceId()获取了imei";
                Log.i(SHARK, log);
                XposedBridge.log(log);
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Object obj = param.getResult();
//                param.setResult("shark chilli");
                printStackTree("getDeviceId()");
            }
        });
        //检测DeviceId
        hook_method("android.telephony.TelephonyManager", lpp.classLoader, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                String log = "检测到 getDeviceId()获取了imei";
                Log.i(SHARK, log);
                XposedBridge.log(log);
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Object obj = param.getResult();
//                param.setResult("shark chilli");
                printStackTree("getDeviceId()");
            }
        });
        
        hook_method("java.net.InetAddress", lpp.classLoader, "getLocalHost", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.i(SHARK, "hook getLocalHost...");
                Object obj = param.getResult();
                Log.i(SHARK, "imei args:"+obj);
                param.setResult("shark getLocalHost");
            }
        });
        hook_method("android.location.LocationManager", lpp.classLoader, "getCurrentLocation", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.i(SHARK, "hook getCurrentLocation...");
                Object obj = param.getResult();
                Log.i(SHARK, "imei args:"+obj);
                param.setResult("shark getCurrentLocation");
            }
        });*/
            /*XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //获取包名对象
                    Class clzz = (Class) param.getResult();
                    //获取类名
                    String className = clzz.getName();
                    if(className.contains("android.telephony.TelephonyManager")) {
                        Log.d("xposeddemo", "===============类名");
                        Log.d("xposeddemo", className);
                        Log.d("xposeddemo", "===============类名");
                        //获取类中所有的方法
                        Method[] mds = clzz.getDeclaredMethods();
                        for (int i=0; i< mds.length;i++) {
                            if(mds[i].toString().contains("getDeviceId")) {
                                Log.d("xposeddemo", "===============方法");
                                Log.d("xposeddemo", mds[i].toString());
                                Log.d("xposeddemo", "===============方法");
                            }
                          //XposedBridge.log("当前方法是："+mds[i].toString());
                        }
                    }
                }
            });*/
    }
//    //安卓系统启动时
//    @Override
//    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
//        Log.d("xposeddemo", startupParam.toString());
//    }
//
//    //资源被初始化时
//    @Override
//    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam initPackageResourcesParam) throws Throwable {
//        Log.d("xposeddemo", initPackageResourcesParam.toString());
//    }



