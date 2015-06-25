package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pradeep on 6/25/2015.
 */
@Setter
@Getter
public class Gender {

    public Gender(int id,String name){
        this.id=id;
        this.name=name;
    }

    private int id;

    private String name;
}
