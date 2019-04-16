package cn.com.food.measure.bean;

import java.io.Serializable;

/**
 * Created by wangm on 2018/11/22.
 */

public class BusinessBean implements Serializable {

    private int Id;
    private String Jjh;
    private String Truckno;
    private String Gross_Datetime;
    private String Tare_Datetime;
    private String Goods;
    private double Gross;
    private double Tare;
    private double Net;
    private String Sender;
    private String Receiver;
    private String Transport;
    private String Unit;
    private String Note1;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getJjh() {
        return Jjh;
    }

    public void setJjh(String jjh) {
        Jjh = jjh;
    }

    public String getTruckno() {
        return Truckno;
    }

    public void setTruckno(String truckno) {
        Truckno = truckno;
    }

    public String getGross_Datetime() {
        return Gross_Datetime;
    }

    public void setGross_Datetime(String gross_Datetime) {
        Gross_Datetime = gross_Datetime;
    }

    public String getTare_Datetime() {
        return Tare_Datetime;
    }

    public void setTare_Datetime(String tare_Datetime) {
        Tare_Datetime = tare_Datetime;
    }

    public String getGoods() {
        return Goods;
    }

    public void setGoods(String goods) {
        Goods = goods;
    }

    public double getGross() {
        return Gross;
    }

    public void setGross(double gross) {
        Gross = gross;
    }

    public double getTare() {
        return Tare;
    }

    public void setTare(double tare) {
        Tare = tare;
    }

    public double getNet() {
        return Net;
    }

    public void setNet(double net) {
        Net = net;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getTransport() {
        return Transport;
    }

    public void setTransport(String transport) {
        Transport = transport;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getNote1() {
        return Note1;
    }

    public void setNote1(String note1) {
        Note1 = note1;
    }
}
