package com.mybatis;

import org.mybatis.generator.api.ShellRunner;

import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * @author: lele
 * @date: 2022/11/30 22:16
 * @description:
 */
public class GenerateMain {

    public void generate() throws UnsupportedEncodingException {
        URL urlPath = this.getClass().getClassLoader().getResource("mybatis-generator.xml");
        String path = urlPath.toString();
        path = java.net.URLDecoder.decode(path, "UTF-8");
        String filePrefix = "file:";
        path = path.substring(filePrefix.length());
        String[] args = {"-configfile", path, "-overwrite"};
        System.out.println(path);
        ShellRunner.main(args);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        new GenerateMain().generate();
    }
}
