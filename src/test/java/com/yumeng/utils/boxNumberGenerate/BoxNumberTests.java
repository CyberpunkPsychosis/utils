package com.yumeng.utils.boxNumberGenerate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class BoxNumberTests {

    @Test
    void contextLoads(){
        List<Box> list = buildData();
        if (list.size() == 0){
            System.out.println("A");
            System.out.println(1);
            System.out.println(1);
        }else {
            list = list.stream().sorted(Comparator
                    .comparing(Box::getCodeNumber)
                    .thenComparing(Box::getCodeLetter)
                    .thenComparing(Box::getCodeDigit))
                    .collect(Collectors.toList());
            Box last = list.get(list.size()-1);
            if (last.getCodeNumber() < 10){
                System.out.println(last.getCodeLetter());
                System.out.println(last.getCodeNumber());
                System.out.println(last.getCodeDigit() + 1);
            }else {
                if (!last.getCodeLetter().equals("Z")){
                    int index = last.getCodeLetter().toCharArray()[0];
                    index = index + 1;
                    char change = (char)index;
                    System.out.println(change);
                    System.out.println(last.getCodeNumber());
                    System.out.println(1);
                }else {
                    System.out.println("A");
                    System.out.println(last.getCodeNumber() + 1);
                    System.out.println(1);
                }
            }
        }
    }

    public List<String> getLetters(){
        List<String> list = new ArrayList<>();
        for(int i = 1; i<=26; i++){
            list.add(String.valueOf((char)(64+i)));
        }
        return list;
    }

    public List<Box> buildData(){
        List<String> words = getLetters();
        List<Box> list = new ArrayList<>();
        for (String word: words) {
            for (int i = 9; i >= 0; i--) {
                Box demo = new Box();
                demo.setCodeLetter(word);
                demo.setCodeDigit(i + 1);
                demo.setCodeNumber(1);
                list.add(demo);
            }
        }
        Box demo = new Box();
        demo.setCodeLetter("A");
        demo.setCodeDigit(2);
        demo.setCodeNumber(2);
        list.add(demo);
        return list;
    }
}
