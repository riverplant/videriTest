package com.river.videriTest.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Person {

     private final String name;
     private final int age;
     private final Map<String,Integer> friends;

     public Person(String name, int age, Map<String,Integer> friends) {
         this.name = name;
         this.age = age;
         this.friends = new HashMap(friends);
     }

     public String getName() { 
         return this.name;
     }

     public int getAge() {
         return this.age;
     }

     public Map<String,Integer> getFriends() {
         return Collections.unmodifiableMap(this.friends);
     }
}