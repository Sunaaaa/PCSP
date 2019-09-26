package com.example.pcsp.VO;

import android.os.Parcel;
import android.os.Parcelable;

public class UserVO implements Parcelable {
    int userNum;
    String userName;
    String userId;
    String userPw;
    String userPhoneNum;
    String userAddress;
    int areaNum;

    public UserVO(int userNum, String userName, String userId, String userPw, String userPhoneNum, String userAddress, int areaNum) {
        this.userNum = userNum;
        this.userName = userName;
        this.userId = userId;
        this.userPw = userPw;
        this.userPhoneNum = userPhoneNum;
        this.userAddress = userAddress;
        this.areaNum = areaNum;
    }

    protected UserVO(Parcel in) {
        userNum = in.readInt();
        userName = in.readString();
        userId = in.readString();
        userPw = in.readString();
        userPhoneNum = in.readString();
        userAddress = in.readString();
        areaNum = in.readInt();
    }

    public static final Creator<UserVO> CREATOR = new Creator<UserVO>() {
        @Override
        public UserVO createFromParcel(Parcel in) {
            return new UserVO(in);
        }

        @Override
        public UserVO[] newArray(int size) {
            return new UserVO[size];
        }
    };

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setAreaNum(int areaNum) {
        this.areaNum = areaNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public int getAreaNum() {
        return areaNum;
    }

    public UserVO setUserVO(String[] userdata){
        UserVO mUserVO = new UserVO(Integer.parseInt(userdata[0]), userdata[1], userdata[2], userdata[3], userdata[4], userdata[5], Integer.parseInt(userdata[6]));
        return mUserVO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userNum);
        parcel.writeString(userName);
        parcel.writeString(userId);
        parcel.writeString(userPw);
        parcel.writeString(userPhoneNum);
        parcel.writeString(userAddress);
        parcel.writeInt(areaNum);
    }
}
