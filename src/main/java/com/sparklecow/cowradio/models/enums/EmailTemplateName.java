package com.sparklecow.cowradio.models.enums;

public enum EmailTemplateName {

    ACTIVATION_CODE("activation_code");

    private String templateName;

    EmailTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
}
