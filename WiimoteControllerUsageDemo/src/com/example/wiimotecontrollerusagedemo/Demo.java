/*
 * Copyright 2011 Cvetko Pir�. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY CVETKO PIR� "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package com.example.wiimotecontrollerusagedemo;

/*
 ***************************************************************************
 ********************* I M P O R T A N T   N O T I C E *********************
 ***************************************************************************
 *
 * If you intent on reusing this demo for your own project directly, you can
 * do so,  but you  have to  change the package  name.  The package  name of
 * com.example.*  is reserved for example projects only and should not exist
 * in production code!
 *  
 */

import com.example.wiimotecontrollerusagedemo.R;
import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService;
import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Demo extends Activity {
    private static final int DIALOG_NOWIIMOTES = 1000;
    private static final int DIALOG_NOOROLDWIIMOTECONTROLLERAPP = 1001;
    private static final String APP_ID = "WiimoteController Usage Demo";

    //A handler for dispatching the toast showing job to the activity thread
    private Handler handler = new Handler();

    private IWiiControllerPublicService wcService = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ContentView to the correct layout
        setContentView(R.layout.main);

        //Set the Connect button event
        findViewById(R.id.connectBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the WiimoteController app is installed before continuing
                if (WiimoteController.isWiimoteControllerInstalled(Demo.this)) {
                    //Intent receiver exists, go ahead and connect
                    boolean success = WiimoteController.connectToWCService(Demo.this, wcServiceConnection);
                    if (!success) {
                        //Oops...
                        Toast.makeText(Demo.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Intent receiver doesn't exist, offer the user to install WiimoteController
                    showDialog(DIALOG_NOOROLDWIIMOTECONTROLLERAPP);
                }
            }
        });

        //Set the Disconnect button event
        findViewById(R.id.disconnectBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnectFromService();
            }
        });

        //Set the analog toggle button event
        findViewById(R.id.analogToggleBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Don't do anything if service is not connected!
                if (wcService == null) return;

                try {
                    //Get current status and then flip it around.
                    boolean status = wcService.getAnalogFeedback();
                    if (!status) {
                        //Enable the analog feedback
                        wcService.setAnalogFeedback(true);
                        Toast.makeText(Demo.this, "Enabled.", Toast.LENGTH_SHORT).show();
                    } else {
                        //Disable the analog feedback
                        wcService.setAnalogFeedback(false);
                        Toast.makeText(Demo.this, "Disabled.", Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    // For demo purposes, this is unhandled, however YOU SHOULD PROPERLY HANDLE unexpected events like this!
                    e.printStackTrace();
                }
            }
        });

        //Set the first function test button event
        findViewById(R.id.testPollingFn1Btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Don't do anything if service is not connected!
                if (wcService == null) return;

                try {
                    Log.v(APP_ID, "getNumberOfConnectedWiimotes(): " + wcService.getNumberOfConnectedWiimotes());
                } catch (RemoteException e) {
                    // For demo purposes, this is unhandled, however YOU SHOULD PROPERLY HANDLE unexpected events like this!
                    e.printStackTrace();
                }
            }
        });

        //Set the second function test button event
        findViewById(R.id.testPollingFn2Btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Don't do anything if service is not connected!
                if (wcService == null) return;

                try {
                    Log.v(APP_ID, "getButtonStatus(0,0): " + wcService.getButtonStatus(0, 0));
                } catch (RemoteException e) {
                    // For demo purposes, this is unhandled, however YOU SHOULD PROPERLY HANDLE unexpected events like this!
                    e.printStackTrace();
                }
            }
        });

        //Set the third function test button event
        findViewById(R.id.testPollingFn3Btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Don't do anything if service is not connected!
                if (wcService == null) return;

                try {
                    Log.v(APP_ID, "getPeripheralId(0): " + wcService.getPeripheralId(0));
                } catch (RemoteException e) {
                    // For demo purposes, this is unhandled, however YOU SHOULD PROPERLY HANDLE unexpected events like this!
                    e.printStackTrace();
                }
            }
        });

        //Set the forth function test button event
        findViewById(R.id.testPollingFn4Btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Don't do anything if service is not connected!
                if (wcService == null) return;

                try {
                    Log.v(APP_ID, "getAnalogInputStatus(0,0): " + wcService.getAnalogInputStatus(0, 0));
                } catch (RemoteException e) {
                    // For demo purposes, this is unhandled, however YOU SHOULD PROPERLY HANDLE unexpected events like this!
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        disconnectFromService();
        super.onDestroy();
    }

    /**
     * This function contains all dialogs
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            //Dialog to show when there are no wiimotes.
            case Demo.DIALOG_NOWIIMOTES:
                return new AlertDialog.Builder(Demo.this)
                        .setTitle("No wiimotes")
                        .setMessage("No wiimotes connected. Do you want to connect some?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Start the main activity of WiimoteController
                                try {
                                    wcService.displayConnectActivity();
                                } catch (RemoteException re) {
                                    re.printStackTrace();
                                    Toast.makeText(Demo.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                disconnectFromService();
                            }
                        })
                        .setCancelable(false)
                        .create();
            //Dialog to show when the WiimoteController app is missing or not updated
            case Demo.DIALOG_NOOROLDWIIMOTECONTROLLERAPP:
                return new AlertDialog.Builder(Demo.this)
                        .setTitle("WiimoteController app missing or old")
                        .setMessage("You have to get or update WiimoteController from the Android Market. Do you want to do this now?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Show the WiimoteController Android Market Page
                                WiimoteController.wiimoteControllerOnMarket(Demo.this);
                            }
                        })
                        .setNegativeButton("No", null)
                        .create();
            default:
                return super.onCreateDialog(id);
        }
    }

    //This function is used to gracefully disconnect from the WiimoteController service
    private void disconnectFromService() {
        if (wcService != null) {
            //Unregister the stub
            try {
                wcService.unregisterFromCallback(wcCallbackStub);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            //Unbind from the service
            try {
                unbindService(wcServiceConnection);
                wcService = null;
                Toast.makeText(Demo.this, "Disconnected.", Toast.LENGTH_SHORT).show();
            } catch (IllegalArgumentException iae) {
                Toast.makeText(Demo.this, "Not connected.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Create a WiimoteController callback stub. This object will get registered with the
     * WiimoteController's public service so that we will get notified of wiimote events.
     * This is where all event functions that react to wiimotes are written.
     * Note that these functions get called by the thread that is separate from your
     * Activity's thread so you might have to use Handlers to dispatch events to your
     * Activity. An example to this is shown in the wiimoteDisconnected callback below.
     */
    private IWiiControllerPublicServiceCallback.Stub wcCallbackStub = new IWiiControllerPublicServiceCallback.Stub() {
        @Override
        public void wiimotesDisconnected() throws RemoteException {
            Log.v(APP_ID, "All wiimotes disconnected.");

            //Also show a Toast about this event. Use the activity's handler to make the activity thread show the Toast.
            //This will get executed by whoever owns the "handler" object
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Demo.this, "All wiimotes disconnected.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void wiimotesConnected(int numberOfConnectedWiimotes) throws RemoteException {
            Log.v(APP_ID, numberOfConnectedWiimotes + " wiimote(s) connected.");
        }

        @Override
        public void wiimoteDisconnected(int whichWiimote) throws RemoteException {
            Log.v(APP_ID, "WM" + (whichWiimote + 1) + " disconnected.");
        }

        @Override
        public void newPeripheralDetected(int whichWiimote, int whichPeripheral) throws RemoteException {
            String perName = whichPeripheral == 0 ? ": peripheral disconnected." :
                    whichPeripheral == 1 ? ": nunchuk connected." :
                            whichPeripheral == 2 ? ": classic controller connected." :
                /* else */           ": unknown peripheral (code " + whichPeripheral + ") connected.";
            Log.v(APP_ID, "WM" + (whichWiimote + 1) + perName);
        }

        @Override
        public void buttonReleased(int whichWiimote, int whichButton) throws RemoteException {
            Log.v(APP_ID, "WM" + (whichWiimote + 1) + ": released button " + whichButton);
        }

        @Override
        public void buttonPressed(int whichWiimote, int whichButton) throws RemoteException {
            Log.v(APP_ID, "WM" + (whichWiimote + 1) + ": pressed button " + whichButton);
        }

        @Override
        public void analogInputStatusChanged(int wiimote, int analogId, double newValue) throws RemoteException {
            Log.v(APP_ID, "WM" + (wiimote + 1) + ": AN" + analogId + ": value changed to " + newValue);
        }
    };

    /**
     * This object handles the WiimoteController's service connection. You get notified when the
     * service connects (IBinder should be remembered because it is used to send commands to). As
     * soon as the service connects, you should check version (see checkVersion()) with the
     * version number
     */
    private ServiceConnection wcServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Connect to the WiimoteController service
            wcService = IWiiControllerPublicService.Stub.asInterface(service);
            try {
                if (!wcService.checkVersion(1)) {
                    //The current WiimoteController app must be outdated so ask the user to update!
                    unbindService(wcServiceConnection);
                    Toast.makeText(Demo.this, "Failed. Please update the WiimoteController app!", Toast.LENGTH_LONG).show();
                    showDialog(Demo.DIALOG_NOOROLDWIIMOTECONTROLLERAPP);
                    return;
                }

                //Register the callback stub and check if we will understand each other
                wcService.registerToCallback(wcCallbackStub);

                //Check number of connected wiimotes and report to the user
                int wiimotes = wcService.getNumberOfConnectedWiimotes();
                if (wiimotes <= 0) {
                    showDialog(Demo.DIALOG_NOWIIMOTES);
                } else {
                    Toast.makeText(Demo.this, "Connected to " + wiimotes + " wiimotes.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Demo.this, "See logcat to watch button events.", Toast.LENGTH_SHORT).show();
                }
            } catch (RemoteException e) {
                //Report the exception to the user
                e.printStackTrace();
                Toast.makeText(Demo.this, "Service crashed! Try again.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            wcService = null;
            Toast.makeText(Demo.this, "Service disconnected.", Toast.LENGTH_SHORT).show();
        }
    };
}