package com.donet.donet.global.enums;

public enum Category {
    EDUCATION("education"),
    HOBBY("hobby"),
    MEDICAL("medical"),
    FOOD("food"),
    ETC("etc"),
    ;

    private final String label;
    private Category(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
