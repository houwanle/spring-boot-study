package com.mybatis.generator.api;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author: lele
 * @date: 2022/11/30 21:56
 * @description:
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }
    /**
     * 生成字段字注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
            field.addJavaDocLine("/** "+ introspectedColumn.getRemarks() +" */");
            // addJavadocTag(field, false);
        }
    }

    @Override
    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn){

    }

    @Override
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn){

    }

    @Override
    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }
}
