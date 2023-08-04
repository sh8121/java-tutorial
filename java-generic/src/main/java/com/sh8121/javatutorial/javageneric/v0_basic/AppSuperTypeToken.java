package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v2.Tv;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSuperTypeToken {

    public static void main(String[] args) {
        ElectronicsStore store = new ElectronicsStore();

        Tv tv = new Tv("제조사1", "티비1");
        List<Tv> tvList = new ArrayList<>();
        tvList.add(new Tv("제조사2", "티비2"));

        store.save(tv, new TypeReference<Tv>() {
        });
        store.save(tvList, new TypeReference<List<Tv>>() {
        });
        Tv findTv = store.find(new TypeReference<Tv>() {
        });
        List<Tv> findTvList = store.find(new TypeReference<List<Tv>>() {
        });
        System.out.println(tv == findTv);
        System.out.println(tvList == findTvList);
    }

    static class ElectronicsStore {

        private final Map<Type, Object> store = new HashMap<>();

        public <T> void save(T obj, TypeReference<T> typeReference) {
            store.put(typeReference.getType(), obj);
        }

        public <T> T find(TypeReference<T> typeReference) {
            return (T) store.get(typeReference.getType());
        }
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
