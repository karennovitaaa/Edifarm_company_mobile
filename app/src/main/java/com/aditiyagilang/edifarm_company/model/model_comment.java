package com.aditiyagilang.edifarm_company.model;

public class model_comment {
    private final String username_comment;
    private final String isi_comment;

    public model_comment(String username_comment, String isi_comment) {
        this.username_comment = username_comment;
        this.isi_comment = isi_comment;
    }


    public String getUsername_comment() {
        return username_comment;
    }

    public String getIsi_comment() {
        return isi_comment;
    }
}
