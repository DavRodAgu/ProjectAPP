package com.example.ei1057.appcliente;



class Session {
    private String name;
    private String password;

    public Session(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
