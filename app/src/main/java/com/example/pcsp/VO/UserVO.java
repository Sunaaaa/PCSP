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
