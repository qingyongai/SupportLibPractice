package com.qingyong.supportlibpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private volatile boolean needBreak;
    private Handler mHandler;
    private Socket mSocket;
    private TextView mTextView;
    private EditText mEditText;
    private EditText mHost;

    private PrintWriter mPrintWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.rec_msg);
        mEditText = (EditText) findViewById(R.id.need_send);
        mHost = (EditText) findViewById(R.id.host);
        mHandler = new Handler(this);
        mHost.setText("114.215.88.247,2370");
    }

    public void connect(View view) {
        try {
            String res = mHost.getText().toString();
            if (!TextUtils.isEmpty(res)) {
                final String[] s = res.split("[,，]");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        socket(s);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(View view) {
        if (mEditText != null) {
            String msg = mEditText.getText().toString();
            if (!TextUtils.isEmpty(msg.trim())) {
                if (msg.equals("end")) {
                    needBreak = true;
                    return;
                }
                if (mSocket != null && !mSocket.isClosed() && mSocket.isConnected()) {
                    try {
                        if (mPrintWriter == null) {
                            mPrintWriter = new PrintWriter(mSocket.getOutputStream(), true);
                        }
                        mPrintWriter.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.obj instanceof CharSequence) {
            mTextView.append((CharSequence) msg.obj);
            mTextView.append("\n");
        }
        return true;
    }

    private void socket(String[] host) {
        try {
            mSocket = new Socket(host[0], Integer.parseInt(host[1]));
            BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));
            while (true) {
                if (needBreak) {
                    break;
                }
                Message message = new Message();
                message.obj = in.readLine().trim();
                mHandler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        needBreak = true;
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
        if (mSocket != null)
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
