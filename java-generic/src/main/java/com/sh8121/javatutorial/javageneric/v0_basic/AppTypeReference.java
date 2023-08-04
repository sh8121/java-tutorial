package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v2.Radio;
import com.sh8121.javatutorial.javageneric.model.v2.Tv;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class AppTypeReference {

    public static void main(String[] args) {
        TypeReference<Tv> tvTypeReference = new TypeReference<Tv>() {
        };
        TypeReference<Radio> radioTypeReference = new TypeReference<Radio>() {
        };
        TypeReference<List<Tv>> tvListTypeReference = new TypeReference<List<Tv>>() {
        };
        TypeReference<List<Radio>> radioListTypeReference = new TypeReference<List<Radio>>() {
        };
        System.out.println(tvTypeReference.getType()); //(Class 객체)
        System.out.println(radioTypeReference.getType()); //(Class 객체)
        System.out.println(tvListTypeReference.getType()); //(ParameterizedType 객체)
        System.out.println(radioListTypeReference.getType()); //(ParameterizedType 객체)

        TypeReference<Tv> tvTypeReference1 = new TypeReference<Tv>() {
        };
        TypeReference<Tv> tvTypeReference2 = new TypeReference<Tv>() {
        };
        System.out.println(tvTypeReference1.getType() == tvTypeReference2.getType());

        TypeReference<List<Tv>> tvListTypeReference1 = new TypeReference<List<Tv>>() {
        };
        TypeReference<List<Tv>> tvListTypeReference2 = new TypeReference<List<Tv>>() {
        };
        System.out.println(tvListTypeReference1.getType() == tvListTypeReference2.getType());
        System.out.println(tvListTypeReference1.getType().equals(tvListTypeReference2.getType()));
        System.out.println(tvListTypeReference1.getType().hashCode());
        System.out.println(tvListTypeReference2.getType().hashCode());
    }

    static abstract class TypeReference<T> {

        private Type type;

        public TypeReference() {
            this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }

        public Type getType() {
            return type;
        }
    }
}
