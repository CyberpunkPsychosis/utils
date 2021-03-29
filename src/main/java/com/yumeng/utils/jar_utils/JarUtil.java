package com.yumeng.utils.jar_utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

//修改aspose-wrods源码
public class JarUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            changeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeMethod() throws Exception {
        // 获取 Jar 包
        ClassPool.getDefault().insertClassPath(
                "C:\\Users\\user1\\Desktop\\aspose-words-18.6-jdk16.jar");
        // 获取 com.aspose.words.zzZLX 类
        CtClass c2 = ClassPool.getDefault()
                .getCtClass("com.aspose.words.zzZLX");
        // 查找并获取 private static void zzZ(Node var0, Node var1) 方法
        CtMethod[] ms = c2.getDeclaredMethods();
        for (CtMethod c : ms) {
            System.out.println(c.getName());
            CtClass[] ps = c.getParameterTypes();
            for (CtClass cx : ps) {
                System.out.println("\t" + cx.getName());
            }

            if (c.getName().equals("zzZ") && ps.length == 2
                    && ps[0].getName().equals("org.w3c.dom.Node")
                    && ps[1].getName().equals("org.w3c.dom.Node")) {
                System.out.println("find it!");
                // 在方法体最前面直接 return
                c.insertBefore("{return;}");
            }
        }
        c2.writeFile();
    }

}
