package com.example.ei1057.appcliente;


class DataModel {
    private String guideId;
    private float RSSI;

    DataModel(String name, float RSSI) {
        this.guideId = name;
        this.RSSI = RSSI;
    }

    String getGuideID() {
        return guideId;
    }

    public float getRSSI() {
        return RSSI;
    }
}
