package com.fmisser.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2016/2/25.
 * 设备信息
 */

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long type;

    @NotNull
    private long status;

    @NotNull
    private String number;

    private String intro;

    public Device() {
        //do nothing
    }

    public Device(String name, long type, long status, String number) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.number = number;
    }

    public Device(String name, long type, long status, String number, String describe) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.number = number;
        this.intro = describe;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
