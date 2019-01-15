package com.asdev.wiimote;

import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class KeyHandler extends Thread {

    public MainActivity activity;

    public KeyHandler(MainActivity activity) {
        super();
        this.activity = activity;
    }

    public static AtomicBoolean isRunning = new AtomicBoolean(false);

    public static int BIND_PORT = 20495;

    private Deque<Integer> queue = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        isRunning.set(true);
        // setup server socket
        Socket s;
        InputStream in;
        OutputStream out;
        try {
            ServerSocket socket = new ServerSocket(BIND_PORT);
            msg("\nWaiting for connection...");
            msg("\n" + activity.wifiIpAddress(activity) + ":" + BIND_PORT);
            s = socket.accept();
            msg("\nAccepted connection from: " + s.getInetAddress().getHostAddress());

            in = s.getInputStream();
            out = s.getOutputStream();
        } catch (IOException e) {
            error(e);
            return;
        }

        // all connected, now run loop
        while(isRunning.get()) {

            try {
                int i;
                if((i = in.available()) > 0) {
                    // read it
                    for(int j = 0; j < i; j ++) {
                        int val = in.read();
                        if(val == -1) {
                            // close up
                            isRunning.set(false);
                            throw new IOException("Socket closed");
                        } else {
                            // TODO: process commands
                            Log.d("KeyHandler", "PROCESS: " + val);
                        }
                    }
                }

                for(Integer val : queue) {
                    // write to stream
                    out.write(val);
                    queue.remove();
                }

                out.flush();

            } catch (IOException e) {
                error(e);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                error(e);
            }
        }

        msg("\nStopping...");
        // disconnect the socket
        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException e) {
            error(e);
        }
    }

    public void error(Exception e) {
        e.printStackTrace();
        msg("\nERROR: " + e.getMessage());
    }

    private static final int KEY_PRESS_MOD = 0, KEY_RELEASE_MOD = 100;
    public void keyPress(int wiimote, int keyCode) {
        keyCode += KEY_PRESS_MOD;
        // add to queue
        queue.add(wiimote);
        queue.add(keyCode);
    }

    public void keyRelease(int wiimote, int keyCode) {
        keyCode += KEY_RELEASE_MOD;
        queue.add(wiimote);
        queue.add(keyCode);
    }

    public void msg(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.textView.append(message);
                activity.scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
