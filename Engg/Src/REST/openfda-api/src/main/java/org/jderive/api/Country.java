package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * VO design pattern.
 *
 * Created by Durga on 6/20/2015.
 */

@Setter
@Getter
public class Country {

    private String id;
    private String name;
    private String code;

    /*private Country(Builder builder){
        this.id = builder.id;
        this.code = builder.code;
        this.name = builder.name;
    }

    public static Builder builder(){
        return new Builder();
    }

    private static class Builder {
        private String id;
        private String name;
        private String code;

        public final Country build() {
            return new Country(this);
        }

        public Builder withId(String input){
            this.id = input;
            return this;
        }

        public Builder withName(String input){
            this.name = input;
            return this;
        }

        public Builder withCode(String input){
            this.code = input;
            return this;
        }
    }*/
}
