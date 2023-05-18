package com.aditiyagilang.edifarm_company.model.documentation;

import com.google.gson.annotations.SerializedName;

public class DocumentationData {

    @SerializedName("pdf_file")
    private String pdfFile;

    @SerializedName("report_id")
    private int reportId;

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}