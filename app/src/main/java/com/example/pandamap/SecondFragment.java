package com.example.pandamap;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
//    private Handler mHandler;//
    public Button RefreshBtn;
    public TextView WifiTv;
    public TextView IpTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fg2, container, false);
//        WifiTv = view.findViewById(R.id.WifiInfo);
//        IpTv = view.findViewById(R.id.IpInfo);
//        mHandler = new MyHandler();
//        Thread getNetInfoThread = new MyThread();
//        getNetInfoThread.start();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RefreshBtn = getActivity().findViewById(R.id.Refresh);
//        WifiTv = getActivity().findViewById(R.id.WifiInfo);
//        IpTv = getActivity().findViewById(R.id.IpInfo);
        RefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Fragment", "点击Button");
                ((MainActivity) getActivity()).getNetWorkInfo();
                ((MainActivity)getActivity()).setList();

            }
        });
    }

    //        private class MyThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            InetAddress address = null;
//            try {
//                address = InetAddress.getLocalHost();
//                String ipAddress = address.getHostAddress();
//                Message message = mHandler.obtainMessage();
//                message.obj = ipAddress;
//                mHandler.sendMessage(message);
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//    private class MyHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            String ip = (String) msg.obj;
//            String WifiSSID = getWIFISSID(getActivity());
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
//    /**
//     * 获取SSID
//     * @param activity 上下文
//     * @return  WIFI 的SSID
//     */
//    public String getWIFISSID(Activity activity) {
//        String ssid="unknown id";
//
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O||Build.VERSION.SDK_INT==Build.VERSION_CODES.P) {
//
//            WifiManager mWifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//            assert mWifiManager != null;
//            WifiInfo info = mWifiManager.getConnectionInfo();
//
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//                return info.getSSID();
//            } else {
//                return info.getSSID().replace("\"", "");
//            }
//        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O_MR1){
//
//            ConnectivityManager connManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//            assert connManager != null;
//            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
//            if (networkInfo.isConnected()) {
//                if (networkInfo.getExtraInfo()!=null){
//                    return networkInfo.getExtraInfo().replace("\"","");
//                }
//            }
//        }
//        return ssid;
//    }
}
