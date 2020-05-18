package com.bazaraki.autotests.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Nikolay Streltsov on 17.05.2020
 */
@Data
@AllArgsConstructor
public class Offer {
    private int index;
    private Date timeLike;
    private double price;
    private int photoCount;
}
