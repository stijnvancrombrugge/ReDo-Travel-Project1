package com.realdolmen.project1.domain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 10/09/2015.
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

    private int version;
    private String userModified;
}
