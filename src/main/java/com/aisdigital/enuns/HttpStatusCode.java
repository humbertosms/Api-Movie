package com.aisdigital.enuns;

public enum HttpStatusCode {
    OK(200),BAD_REQUEST(400),ERROR(500);
    private int cod;
    HttpStatusCode(int i) {
        cod = i;
    }

    public int getCod() {
        return cod;
    }
}
