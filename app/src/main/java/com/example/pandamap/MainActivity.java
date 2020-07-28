package com.example.pandamap;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView titleLeftImv;
    private TextView titleTv;
    // 定义4个Fragment对象
    private FirstFragment fg1;
    private SecondFragment fg2;
    private ThirdFragment fg3;
    private FourthFragment fg4;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout thirdLayout;
    private RelativeLayout fourthLayout;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;
    private TextView fourthText;
//    public Button RefreshBtn;
    public TextView WifiTv;
    public TextView IpTv;
    public ListView mListView;

//    TextView tv_main_result = null;
    String[] list_result ;
    // 定义几个颜色
    private int white = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    private Handler mHandler;//


    @Override
    protected void onStart() {
        super.onStart();
        WifiTv = findViewById(R.id.WifiInfo);
        IpTv = findViewById(R.id.IpInfo);
        mListView = findViewById(R.id.arpInfo);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_main_lay);

        fragmentManager = getSupportFragmentManager();
//        RefreshBtn = findViewById(R.id.Refresh);
//        if (RefreshBtn != null) {
//            Log.d("MainInit", "初始化");
//        }else {
//            Log.d("MainInit", "初始化失败");
//        }

        initView();


//        mHandler = new MyHandler();
//        Thread getNetInfoThread = new MyThread();
//        getNetInfoThread.start();
        getNetWorkInfo();
        setChooseItem(1);
    }

    private void initView() {

        titleLeftImv = (ImageView) findViewById(R.id.title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        titleTv = (TextView) findViewById(R.id.title_text_tv);
        titleTv.setText("PandaMap");
        firstImage =  findViewById(R.id.first_image);
        secondImage = findViewById(R.id.second_image);
        thirdImage = findViewById(R.id.third_image);
        fourthImage = findViewById(R.id.fourth_image);
        firstText = findViewById(R.id.first_text);
        secondText = findViewById(R.id.second_text);
        thirdText = findViewById(R.id.third_text);
        fourthText = findViewById(R.id.fourth_text);
        firstLayout =  findViewById(R.id.first_layout);
        secondLayout =  findViewById(R.id.second_layout);
        thirdLayout = findViewById(R.id.third_layout);
        fourthLayout =  findViewById(R.id.fourth_layout);

        firstLayout.setOnClickListener(MainActivity.this);
        secondLayout.setOnClickListener(MainActivity.this);
        thirdLayout.setOnClickListener(MainActivity.this);
        fourthLayout.setOnClickListener(MainActivity.this);
//        RefreshBtn.setOnClickListener(MainActivity.this);
}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first_layout:
                setChooseItem(0);
                break;
            case R.id.second_layout:
                setChooseItem(1);
                break;
            case R.id.third_layout:
                setChooseItem(2);
                break;
            case R.id.fourth_layout:
                setChooseItem(3);
                break;
            default:
                break;
        }
    }

    private void setChooseItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChoose();//清空，隐藏所有fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
                if (fg1 == null) {//如果为空，创建fg1
                    fg1 = new FirstFragment();
                    SDKInitializer.initialize(getApplicationContext());
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
                    fragmentTransaction.show(fg1);
                }
                break;
            case 1:
                secondText.setTextColor(dark);
                secondLayout.setBackgroundColor(gray);
                if (fg2 == null) {//如果为空，创建fg1
                    fg2 = new SecondFragment();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);

                }
                break;
            case 2:
                thirdText.setTextColor(dark);
                thirdLayout.setBackgroundColor(gray);
                if (fg3 == null) {//如果为空，创建fg1
                    fg3 = new ThirdFragment();
                    SDKInitializer.initialize(getApplicationContext());
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;
            case 3:
                fourthText.setTextColor(dark);
                fourthLayout.setBackgroundColor(gray);
                if (fg4 == null) {
                    fg4 = new FourthFragment();
                    fragmentTransaction.add(R.id.content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;
        }
        fragmentTransaction.commit();//提交
    }



    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChoose() {
        {
// firstImage.setImageResource(R.drawable.XXX);
            firstText.setTextColor(gray);
            firstLayout.setBackgroundColor(white);
// secondImage.setImageResource(R.drawable.XXX);
            secondText.setTextColor(gray);
            secondLayout.setBackgroundColor(white);
// thirdImage.setImageResource(R.drawable.XXX);
            thirdText.setTextColor(gray);
            thirdLayout.setBackgroundColor(white);
// fourthImage.setImageResource(R.drawable.XXX);
            fourthText.setTextColor(gray);
            fourthLayout.setBackgroundColor(white);
        }
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
        if (fg4 != null) {
            fragmentTransaction.hide(fg4);
        }
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
                String ipAddress = address.getHostAddress();
                Message message = mHandler.obtainMessage();
                message.obj = ipAddress;
                mHandler.sendMessage(message);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }
    }
//    private class MyHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            String ip = (String) msg.obj;
//            String WifiSSID = getWIFISSID(MainActivity.this);
//            Log.d("Wifi", WifiSSID);
//            Log.d("IP", ip);
//            if (WifiTv != null) {
//                WifiTv.setText("Wifi:"+WifiSSID);
//            }else {
//                Log.d("WifiInfo", "wifiinfo为空");
//            }
//            if (IpTv != null) {
//                IpTv.setText("IP:"+ip);
//            }
//            else {
//                Log.d("IpInfo", "IpInfo为空");
//            }
//
//        }
//    }
    /**
     * 获取SSID
     * @param activity 上下文
     * @return  WIFI 的SSID
     */
    public String getWIFISSID(Activity activity) {
        String ssid="unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O||Build.VERSION.SDK_INT==Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return info.getSSID();
            } else {
                return info.getSSID().replace("\"", "");
            }
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O_MR1){

            ConnectivityManager connManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo()!=null){
                    return networkInfo.getExtraInfo().replace("\"","");
                }
            }
        }
        return ssid;
    }

    public void getNetWorkInfo() {
        String myIp="IP:";
        String myWifiName="Wifi";
        try {
            WifiManager wm = null;
            try {
                wm = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            } catch (Exception e) {
                wm = null;
                Log.d("getNetWorkInfo", "未获取到wm");
            }
            if (wm != null && wm.isWifiEnabled()) {
                WifiInfo wifi = wm.getConnectionInfo();
                if (wifi.getRssi() != -200) {
                    myIp = myIp+getWiFiIPAddress(wifi.getIpAddress());//获取ip
                }
                myWifiName =myWifiName+ wifi.getSSID(); //获取被连接网络的名称
                WifiTv.setText(myWifiName);
                IpTv.setText(myIp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWiFiIPAddress(int ipaddr) {
        String ip = "";
        if(ipaddr==0) return ip;
        byte[] addressBytes = {(byte)(0xff & ipaddr), (byte)(0xff & (ipaddr >> 8)),
                (byte)(0xff & (ipaddr >> 16)), (byte)(0xff & (ipaddr >> 24))};
        try {
            ip = InetAddress.getByAddress(addressBytes).toString();
            if (ip.length() > 1) {
                ip = ip.substring(1, ip.length());
            } else {
                ip = "";
            }
        } catch (UnknownHostException e) {
            ip = "";
        } catch (Exception e) {
            ip = "";
        }
        return ip;
    }

        private void sendDataToLocal() {
        //局域网内存在的ip集合
        final List<String> ipList = new ArrayList<>();
        final Map<String, String> map = new HashMap<>();

        //获取本机所在的局域网地址
        String hostIP = getHostIP();
        int lastIndexOf = hostIP.lastIndexOf(".");
        final String substring = hostIP.substring(0, lastIndexOf + 1);

        //创建线程池
        //final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramPacket dp = new DatagramPacket(new byte[0], 0, 0);
                DatagramSocket socket;
                try {
                    socket = new DatagramSocket();
                    int position = 2;
                    while (position < 255) {
                        Log.e("Scanner ", "run: udp-" + substring + position);
                        dp.setAddress(InetAddress.getByName(substring + String.valueOf(position)));
                        socket.send(dp);
                        position++;
                        if (position == 125) {//分两段掉包，一次性发的话，达到236左右，会耗时3秒左右再往下发
                            socket.close();
                            socket = new DatagramSocket();
                        }
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     * 获取本机 ip地址
     *
     * @return
     */
    private String getHostIP() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("kalshen", "SocketException");
            e.printStackTrace();
        }
        return hostIp;
    }

    /**
     * 每隔8秒读取一下本机arp缓存表内容
     */
    private void readArpLoop() {
        readArp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readArpLoop();
            }
        }, 8000);
    }

    private void readArp() {
        try {
            int i=0;
            String [] list_temp= new String[255];
            List<String> list=new ArrayList<String>();

            BufferedReader br = new BufferedReader(
                    new FileReader("/proc/net/arp"));
            String line = "";
            String ip = "";
            String flag = "";
            String mac = "";
//            tv_main_result.setText("");
            if (br.readLine() == null) {
                Log.e("scanner", "readArp: null");
            }
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() < 63) continue;
                if (line.toUpperCase(Locale.US).contains("IP")) continue;
                ip = line.substring(0, 17).trim();
                flag = line.substring(29, 32).trim();
                mac = line.substring(41, 63).trim();
                if (mac.contains("00:00:00:00:00:00")) continue;
                Log.e("scanner", "readArp: mac= " + mac + " ; ip= " + ip + " ;flag= " + flag);
//                tv_main_result.append("\nip:" + ip + "\tmac:" + mac);
                String str="ip:" + ip + "\nmac:" + mac;
                list_temp[i++] = str;
            }
            for (int j = 0; j < list_temp.length; j++) {
                if (list_temp[j] == null || "".equals(list_temp[j].trim().toString())) {
                    continue;
                }else {
                    list.add(list_temp[j]);
                }
            }
            list_result = new String[list.size()];
            for (int j = 0; j < list_result.length; j++) {
                list_result[j] = list.get(j);
            }
            br.close();
        } catch (Exception ignored) {
        }
    }

    public void setList() {
        //首先向所处网段的所有设备发送一遍数据包
        sendDataToLocal();
        //延迟3秒后读取本机arp缓存表内容
        readArpLoop();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.array_adapter, list_result);
        ListView listView = findViewById(R.id.arpInfo);
        listView.setAdapter(adapter);
    }


}