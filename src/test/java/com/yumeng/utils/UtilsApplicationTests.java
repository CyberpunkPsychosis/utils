package com.yumeng.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UtilsApplicationTests {

    @Test
    void contextLoads() {
        List<Object> array = new ArrayList<>();
        array.add(5);
        array.add(2);
        List<Integer> a = Arrays.asList(7, -1);
        array.add(a);
        array.add(3);
        List<Object> b = new ArrayList<>();
        b.add(6);
        List<Integer> c = Arrays.asList(-13, 8);
        b.add(c);
        b.add(4);
        array.add(b);
        System.out.println(productSum(array));
    }

    public static List<Integer> branchSums(BinaryTree root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        findBranchSums(root, root.value, arrayList);
        return arrayList;
    }

    public static void findBranchSums(BinaryTree root, Integer sum, ArrayList<Integer> arrayList){
        if (root.left != null) {
            findBranchSums(root.left, root.left.value + sum, arrayList);
        }
        if (root.right != null) {
            findBranchSums(root.right, root.right.value + sum, arrayList);
        }
        if (root.right == null && root.left == null){
            arrayList.add(sum);
        }
    }

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static int productSum(List<Object> array) {
        List<Integer> total = new ArrayList<>();
        productSum(array, 1, total);
        return total.stream().mapToInt(x -> x).sum();
    }

//    [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
    public static void productSum(List<Object> array, int depth, List<Integer> total) {
        int sum = 0;
        for (Object o : array) {
            if (o instanceof List) {
                List<Object> list = (List<Object>)o;
                productSum(list, depth + 1, total);
            } else {
                Integer a = (Integer) o;
                sum += a;
            }
        }
        if (depth == 1) {
            total.add(depth * sum);
        } else {
            int i = depth;
            while (i > 1) {
                sum *= i;
                i--;
            }
            total.add(sum);
        }
    }
}
