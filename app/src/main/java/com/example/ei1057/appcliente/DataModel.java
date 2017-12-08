package com.example.ei1057.appcliente;


class DataModel {
    private String guideId;
    private String SSID;
    //private float RSSI;

    //DataModel(String name, float RSSI) {
    public DataModel(String name, String SSID) {
        this.guideId = name;
        this.SSID = SSID;
        //this.RSSI = RSSI;
    }

    public DataModel(){
        super();
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }
    /*
    public float getRSSI() {
        return RSSI;
    }

    public void setRSSI(float RSSI) {
        this.RSSI = RSSI;
    }*/
}
