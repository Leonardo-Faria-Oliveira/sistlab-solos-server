package com.example.sistlabsolos.models;

public class Headers {
    

    private byte[] header1;

    private byte[] header2;

    private byte[] header3;

    public Headers(byte[] header1, byte[] header2, byte[] header3) {
        this.header1 = header1;
        this.header2 = header2;
        this.header3 = header3;
    }

    public byte[] getHeader1() {
        return header1;
    }

    public void setHeader1(byte[] header1) {
        this.header1 = header1;
    }

    public byte[] getHeader2() {
        return header2;
    }

    public void setHeader2(byte[] header2) {
        this.header2 = header2;
    }

    public byte[] getHeader3() {
        return header3;
    }

    public void setHeader3(byte[] header3) {
        this.header3 = header3;
    }

}
