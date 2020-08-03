package com.example.app.controller.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtils {


    public static List formPageBord(int numberOfPages, int currentPage){
        int[] arr = new int[numberOfPages];
        List pageBoard = new ArrayList();

        int leftRange = currentPage - 3;
        int rightRange = currentPage + 3;
        int head = 1;
        int tail = arr.length;

        formHead(pageBoard, leftRange, head);
        formCenter(arr, pageBoard, leftRange, rightRange);
        formTail(pageBoard, rightRange, tail);

        return pageBoard;
    }

    private static void formCenter(int[] arr, List pages, int leftRange, int rightRange) {
        for (int i = 2; i < arr.length; i++) {
            if(i > leftRange && i < rightRange){
                pages.add(i);
            }
        }
    }

    private static void formTail(List pages, int rightRange, int tail) {
        if(tail > rightRange){
            pages.add(-1);
        }
        pages.add(tail);
    }

    private static void formHead(List pages, int leftRange, int head) {
        pages.add(head);
        if(head < leftRange){
            pages.add(-1);
        }
    }
}
