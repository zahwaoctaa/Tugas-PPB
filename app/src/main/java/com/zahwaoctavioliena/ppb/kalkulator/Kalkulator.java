package com.zahwaoctavioliena.ppb.kalkulator;

public class Kalkulator {
    public Kalkulator(String angka1, String operator, String angka2, String hasil) {
        this.angka1 = angka1;
        this.operator = operator;
        this.angka2 = angka2;
        this.hasil = hasil;
    }

    public String getAngka1() {
        return angka1;
    }

    public void setAngka1(String angka1) {
        this.angka1 = angka1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAngka2() {
        return angka2;
    }

    public void setAngka2(String angka2) {
        this.angka2 = angka2;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    private String angka1, operator, angka2, hasil;
}
