package com.test.model;

public enum JobTypeEnum {
    LEAD_JOB("readLeadFilesJob");

    public final String label;

    private JobTypeEnum(String label) {
        this.label = label;
    }
}