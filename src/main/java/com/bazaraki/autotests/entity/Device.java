package com.bazaraki.autotests.entity;

import lombok.Data;

/**
 * @author Nikolay Streltsov on 17.05.2020
 */
@Data
public class Device {
    private String deviceName;
    private String platform;
    private String platformVersion;
    private String orientation;
    private String browser;
    private String systemPort;
    private String driver;
}
