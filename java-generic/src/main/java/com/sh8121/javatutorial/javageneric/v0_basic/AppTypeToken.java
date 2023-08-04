package com.sh8121.javatutorial.javageneric.v0_basic;

import com.sh8121.javatutorial.javageneric.model.v2.Electronics;
import com.sh8121.javatutorial.javageneric.model.v2.Radio;
import com.sh8121.javatutorial.javageneric.model.v2.Tv;
import java.util.HashMap;
import java.util.Map;

public class AppTypeToken {

    public static void main(String[] args) {
        ElectronicsStore store = new ElectronicsStore();
        Tv tv = new Tv("제조사1", "티비1");
        Radio radio = new Radio("제조사2", "라디오1");

        //Type Safety
        store.<Tv>save(tv, Tv.class);
        store.<Radio>save(radio, Radio.class);

        //Type Casting
        Tv findTv = store.<Tv>find(Tv.class);
        Radio findRadio = store.<Radio>find(Radio.class);

        //Type Inference
        // store.save(tv, Tv.class);
        // store.save(radio, Radio.class);

        // Tv findTv = store.find(Tv.class);
        // Radio findRadio = store.find(Radio.class);
    }

    static class ElectronicsStore {

        private final Map<Class<? extends Electronics>, Object> store = new HashMap<>();

        public <T extends Electronics> void save(T obj, Class<T> clazz) {
            store.put(clazz, obj);
        }

        public <T extends Electronics> T find(Class<T> clazz) {
            return clazz.cast(store.get(clazz));
        }
    }
}
