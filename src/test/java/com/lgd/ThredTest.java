package com.lgd;

import java.util.*;

public class ThredTest extends Thread{
    @Override
    public void run() {
        System.out.println("Thread----线程");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Test{

    public static void main(String[] args) {

//        System.out.println("Main----线程");
//        ThredTest thredTest = new ThredTest();
//        thredTest.run();
//
//        System.out.println("Main----线程");

//        Executors.new


        int[] a = {1, 2, 3, 1,2,3};
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i <a.length ; i++) {
            if(!set1.contains(a[i])){
                set1.add(a[i]);
            }else{
                set2.add(a[i]);
            }
        }
        for (Integer integer : set2) {
            System.out.println(integer);
        }

    }
}