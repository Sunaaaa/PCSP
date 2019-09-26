package com.example.pcsp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.pcsp.VO.UserVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.System.out;

public class PCSPService extends Service {

    IBinder mBinder = new MyBinder();
    private Socket mSocket;
    BufferedReader br;
    PrintWriter out;
    UserVO userVO = new UserVO(0, "", "" ,"" ,"" ,"",0);
    BlockingQueue mBlockingQueue = new ArrayBlockingQueue(10);

    class SendRunnable implements Runnable{
        BlockingQueue blockingQueue;

        SendRunnable(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = (String) blockingQueue.take();
                    Log.i("ChattingClient", "blocking queue send: " + data);
                    out.println(data);
                    out.flush();
                } catch (Exception e) {
                    Log.i("ChattingClientError", "blocking queue 문제 : " + e.toString());
                }
            }


        }
    }

    class ReceiveRunnable implements Runnable{
        Intent mReceiveIntent = new Intent();

        @Override
        public void run() {
            try {
                try {
                    mSocket = new Socket("70.12.115.72",2206);
                    Log.i("ReceiveRunnable", "2206 서버 연결 성공!");
                    br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                    out = new PrintWriter(mSocket.getOutputStream());
                    out.println("APP/ANDROID/ ");
                    out.flush();

                }catch (Exception e){
                    Log.i("ReceiveRunnable_ERROR",e.toString());
                }
                String data = "";
                while ((data = br.readLine()) != null){
                    Log.i("서버에서 보낸 데이터", data);
                    Bundle bundle = new Bundle();
                    String[] serverdata = data.split("/");
                    Log.i("서버에서 보낸 데이터", serverdata[0]);
                    Log.i("서버에서 보낸 데이터", serverdata[1]);
                    Log.i("서버에서 보낸 데이터", serverdata[2]);
//                    ANDROID/LOGIN/FAIL
//                    ANDROID/LOGIN/OK/" + userVO.toString()
                    // 서버에서 들어오는 데이터가 ANDROID로 시작하는 것만 해당 앱에서 사용될 데이터이다.
                    if (serverdata[0].equals("ANDROID")){
                        if (serverdata[1].equals("LOGIN")){
                            ComponentName cname_login = new ComponentName("com.example.pcsp", "com.example.pcsp.LoginActivity");
                            mReceiveIntent.setComponent(cname_login);
                            Log.i(" 노우오우오우오우노우ㅗㄴ웅ㅇㄴ ", serverdata[2]);

                            if (serverdata[2].equals("OK")){
                                String[] userdata = serverdata[3].split(",");
                                Log.i("USERDATA",userdata[5]);
                                userVO = userVO.setUserVO(userdata);
                                Log.i(" 노우오우오우오우노우ㅗㄴ웅ㅇㄴ ", userVO.getUserName());
                                mReceiveIntent.putExtra("LOGIN_RESULT", "OK");
                                mReceiveIntent.putExtra("USERINFO", userVO);
                            }
                            if (serverdata[2].equals("FAIL")){
                                Log.i(" 노우오우오우오우노우ㅗㄴ웅ㅇㄴ ", "설마");
                                mReceiveIntent.putExtra("LOGIN_RESULT", "FAIL");
                                Log.i(" 노우오우오우오우노우ㅗㄴ웅ㅇㄴ ", "여기가 문제인가");
                            }
                            mReceiveIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mReceiveIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            mReceiveIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(mReceiveIntent);
                        }

                        // 프로토콜 추가

                    }
                }
            }
            catch (Exception e){
                Log.i("서버에서 보낸 데이터_ERROR", "서버에서 보낸 데이터를 못받음");
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 서비스에서 가장 먼저 호출됨 (최초 한번만 호출)
        Log.i("PCSPService", "서비스 시작, 서버랑 연결됨!");
    }

    class MyBinder extends Binder{
        PCSPService getService(){
            return PCSPService.this;
        }
    }

    public boolean connectServer(){
        boolean flag = false;
        return flag;
    }

    public void serverToClient(){

    }

    public void clientToServer(String protocol, String data){
        Log.i("serviceClient" , protocol);

        if(protocol.equals("USERLOGIN")){
            mBlockingQueue.add("APP/USERLOGIN/"+data);
            Log.i("serviceClient" , "로그인 정보 : " + data);
        } else if (protocol.equals("MKRO")){
//            blockingQueue.add("/@MKRO,"+msg);
        } else {
            Log.i("serviceClient" , "보낼때 프로토콜 문제 있음");
        }

//        switch(protocol){
//            case "USERLOGIN":
//                mBlockingQueue.add("/USERLOGIN/"+data);
//                break;
//            default :
//                Log.i("serviceClient" , "보낼때 프로토콜 문제 있음");
//                break;
//        }

    }

    public PCSPService() {
    }

    // APP/(protocol)/data1, data2, data3
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ReceiveRunnable mReceiveRunnable = new ReceiveRunnable();
        SendRunnable mSendRunnable = new SendRunnable(mBlockingQueue);
        Thread t1 = new Thread(mReceiveRunnable);
        Thread t2 = new Thread(mSendRunnable);
        t1.start();
        t2.start();

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        // 서비스가 종료될 때 실행

    }
}
