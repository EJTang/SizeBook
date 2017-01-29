package ejtang.sizebook;

import java.util.Date;

/**
 * Created by ejtang on 2017-01-28.
 */

public class person {
    private String name;
    private Date date;
    private Float neck, bust,
            chest, waist,
            hip, inseam;
    private String comment;

    public person(String name) {
        this.name = name;
    }
}

