package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class AppApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(AppApplication.class, args);
//    }

    public static void main(String[] args) {
        pagination(9, 2);

    }

    public static void pagination(int size, int currentPage){
        int[] arr = new int[size];
        List pages = new LinkedList<>();

        int leftRange = currentPage - 3;
        int rightRange = currentPage + 3;
        int head = 1;
        int tail = arr.length;

        pages.add(head);
        if(head < leftRange){
            pages.add(-1);
        }

        for (int i = 2; i < arr.length; i++) {
            if(i > leftRange && i < rightRange){
                pages.add(i);
            }
        }

        if(tail > rightRange){
            pages.add(-1);
        }
        pages.add(tail);


        System.out.println(pages);

    }

}
