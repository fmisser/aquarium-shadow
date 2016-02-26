package com.fmisser.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2016/2/25.
 *
 */

@Entity
@Table(name = "terminals")
public class TerminalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    public TerminalModel() {
        //do nothing
    }

    public TerminalModel(long id) {
        this.id = id;
    }

    public TerminalModel(String name) {
        this.name = name;
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
}
