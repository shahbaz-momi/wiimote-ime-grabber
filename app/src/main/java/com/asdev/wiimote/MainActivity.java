package com.asdev.wiimote;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService;
import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    private KeyHandler keyHandler;
    public TextView textView;
    public ScrollView scrollView;

    private IWiiControllerPublicService service;
    private ServiceConnection serviceConnection;
    private IWiiControllerPublicServiceCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        textView.append("\nCreating key handler...");
        // create key handler
        keyHandler = new KeyHandler(this);
        keyHandler.start();

        setupCallback();
        setupServiceConnection();

        // start the public service
        WiimoteController.connectToWCService(this, serviceConnection);
    }

    private void disconnectService() {
        try {
            service.unregisterFromCallback(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Unable to unbind service: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        unbindService(serviceConnection);
        service = null;
    }

    private void setupCallback() {
        callback = new IWiiControllerPublicServiceCallback.Stub() {

            @Override
            public void buttonPressed(int whichWiimote, int whichButton) throws RemoteException {
                keyHandler.keyPress(whichWiimote, whichButton);
            }

            @Override
            public void buttonReleased(int whichWiimote, int whichButton) throws RemoteException {
                keyHandler.keyRelease(whichWiimote, whichButton);
            }

            @Override
            public void newPeripheralDetected(int whichWiimote, int whichPeripheral) throws RemoteException {

            }

            @Override
            public void wiimotesDisconnected() throws RemoteException {

            }

            @Override
            public void wiimoteDisconnected(int whichWiimote) throws RemoteException {

            }

            @Override
            public void wiimotesConnected(int numberOfConnectedWiimotes) throws RemoteException {

            }

            @Override
            public void analogInputStatusChanged(int whichWiimote, int whichAnalogInput, double newValue) throws RemoteException {

            }
        };
    }

    private void setupServiceConnection() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                service = IWiiControllerPublicService.Stub.asInterface(iBinder);
                try {
                    service.registerToCallback(callback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Unable to connect to service: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                service = null;
            }
        };
    }

    protected String wifiIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endianif needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }
}
