package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertableUtil {

    public static <K, V extends Convertable<K>>Map convert(List<? extends Convertable<K>> list) {
        Map<K,V> result = new HashMap();
        return list.stream()
                .collect(Collectors.toMap(Convertable::getKey, Function.identity()));
    }
}
