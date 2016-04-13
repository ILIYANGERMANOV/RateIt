package com.gcode.rateit.data;

public class UserInfo {
    public static final byte UNKNOWN = -1;
    public static final byte MALE = 1;
    public static final byte FEMALE = 2;

    private String mUsername;
    private String mPassword;
    private int mAge;
    private byte mGender;

    public UserInfo(String username, String password, int age, byte gender) {
        mUsername = username;
        mPassword = password;
        mAge = age;
        mGender = gender;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public byte getGender() {
        return mGender;
    }

    public void setGender(byte gender) {
        mGender = gender;
    }
}
