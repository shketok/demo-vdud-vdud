package ru.vdudvdud.testdata.models.essences;

import lombok.Data;

@Data
public class MiniCartProduct {

    private String name;
    private String currency;
    private String imgLink;
    private int count;
    private int cost;
}
