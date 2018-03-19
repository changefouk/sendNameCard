package com.mbox.administrator.namecardexchange.feature.cards.models;

/**
 * Created by Mangkorn Supparphai on 12/8/2017 AD.
 */

public class MemberMdl {

    private String cardID;
    private String memberID;
    private String guidAuthenID;
    private String deviceID;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String IDCard;
    private String sex;
    private String email;
    private String phoneNumber;
    private String address;
    private String OS;
    private String picProfile;

    public MemberMdl() {
    }

    public MemberMdl(String cardID, String memberID, String guidAuthenID, String deviceID, String title, String firstName, String middleName, String lastName, String birthDate, String IDCard, String sex, String email, String phoneNumber, String address, String OS, String picProfile) {
        this.cardID = cardID;
        this.memberID = memberID;
        this.guidAuthenID = guidAuthenID;
        this.deviceID = deviceID;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.IDCard = IDCard;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.OS = OS;
        this.picProfile = picProfile;
    }


    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getGuidAuthenID() {
        return guidAuthenID;
    }

    public void setGuidAuthenID(String guidAuthenID) {
        this.guidAuthenID = guidAuthenID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(String picProfile) {
        this.picProfile = picProfile;
    }
}
