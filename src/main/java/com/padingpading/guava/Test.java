package com.padingpading.guava;


import com.google.common.collect.*;

import java.util.*;

/**
 * @author libin
 * @description
 * @date 2021/9/30
 */
public class Test {

    public static void main(String[] args) {
        Set<String> set1 = Sets.newHashSet("a","b","c");
        Set<String> set2 = Sets.newHashSet("1","2","3","a");
        //取两个集合的并集
        System.out.println(Sets.union(set1,set2));
    }
}


