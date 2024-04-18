package ru.solomanin.testcinema;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args)  {

        List<Integer> list = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            list.add(i);
        }

        for (Integer seat : list) {
            System.out.print(seat + " ");
            if((seat+1)%5==0){
                System.out.println();
            }
        }
    }
}

