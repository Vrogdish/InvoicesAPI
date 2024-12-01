package com.Invoices.myApi.enums;

public enum Civility {

    MR("MR"),
    MRS("MRS");

    private String value;

    Civility(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
