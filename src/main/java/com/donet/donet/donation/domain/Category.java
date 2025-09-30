package com.donet.donet.donation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;

    public static Category of(String name) {
        return new Category(getIdFromName(name), name);
    }

    private static Long getIdFromName(String name) {
        if(name.equals("EDUCATION")) return 1L;
        if(name.equals("HOBBY")) return 2L;
        if(name.equals("MEDICAL")) return 3L;
        if(name.equals("FOOD")) return 4L;
        if(name.equals("ETC")) return 5L;
        return 0L;
        //TODO: exception 발생
    }
}
