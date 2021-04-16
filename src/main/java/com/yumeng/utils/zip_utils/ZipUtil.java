package com.yumeng.utils.zip_utils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩成ZIP 单文件压缩
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常 存在问题当源文件找不到的时候仍然压缩，
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void toZip(String srcDir, String destDir, boolean KeepDirStructure) throws RuntimeException {
        long start = System.currentTimeMillis();
        try {
            compress(srcDir, destDir, KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    /**
     * 压缩成ZIP 多文件压缩
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常 提前判断文件是否存在
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure)
            throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();//关闭当前zip条目读取下一条
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }

    /**
     * @param src              被压缩的文件源路径
     * @param newSrc           压缩后的新路径+名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    public static void compress(String src, String newSrc, boolean KeepDirStructure) throws Exception {
        FileOutputStream fos3 = new FileOutputStream(new File(newSrc));
        File file = new File(src);
        ZipOutputStream zos = new ZipOutputStream(fos3);
        compress(file, zos, file.getName(), KeepDirStructure);
        zos.close();
    }

    public static void main(String[] args) throws Exception {
        /** 测试压缩方法1 */
        FileOutputStream fos1 = new FileOutputStream(new
                File("C:\\Users\\user1\\Desktop\\测试.zip"));
        toZip(
                "C:\\Users\\user1\\Desktop\\新建文件夹",
                fos1, true);


        /** 测试压缩方法2 */

//        List<File> fileList = new ArrayList<File>();
//        fileList.add(new File("F:/笔记/截图/Html/合并单元格.png"));
//        fileList.add(new File("F:/photo/鸡哈线/质量管理/过程质量控制/钢筋工程/fds/3D120-2018-11-14-001X号塔+桩号(承台)+钢筋工程.jpeg"));
//        FileOutputStream fos2 = new FileOutputStream(new
//                File("F:/mytest02.zip"));
//        toZip(fileList, fos2);


        /** 测试递归压缩 必须关闭zip流 */
        /*FileOutputStream fos3 = new FileOutputStream(new File("F:/mytest03.zip"));
        File file = new File("F:/photo/鸡哈线");
        ZipOutputStream zos = new ZipOutputStream(fos3);
        ZipUtils.compress(file, zos, "鸡哈线", true);
        zos.close();//这里必须切记关闭
        */
    }
}
