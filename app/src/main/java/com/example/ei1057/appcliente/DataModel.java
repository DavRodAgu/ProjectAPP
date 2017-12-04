package com.example.ei1057.appcliente;


class DataModel {
    private String guideId;
    private float RSSI;

    public DataModel(String name, float RSSI) {
        this.guideId = name;
        this.RSSI = RSSI;
    }
    public DataModel(){
        super();
    }

    public float getRSSI() {
        return RSSI;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public void setRSSI(float RSSI) {
        this.RSSI = RSSI;
    }
}
