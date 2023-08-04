package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v2.Radio;
import com.sh8121.javatutorial.javageneric.model.v2.Tv;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AppParameterizedType {

    public static void main(String[] args) {
        List<Tv> tvList = new TvList();
        ParameterizedType parameterizedType = (ParameterizedType) tvList.getClass().getGenericSuperclass();
        System.out.println(parameterizedType);
        System.out.println(parameterizedType.getActualTypeArguments()[0]);

        List<Radio> radioList = new ArrayList<Radio>() {
        };
        parameterizedType = (ParameterizedType) radioList.getClass().getGenericSuperclass();
        System.out.println(parameterizedType);
        System.out.println(parameterizedType.getActualTypeArguments()[0]);

        List<List<Radio>> radioNestedList = new ArrayList<List<Radio>>() {
        };
        parameterizedType = (ParameterizedType) radioNestedList.getClass().getGenericSuperclass();
        System.out.println(parameterizedType);
        ParameterizedType nestedParameterizedType = (ParameterizedType) parameterizedType.getActualTypeArguments()[0];
        System.out.println(nestedParameterizedType);
    }

    static class TvList extends ArrayList<Tv> {

    }
}
