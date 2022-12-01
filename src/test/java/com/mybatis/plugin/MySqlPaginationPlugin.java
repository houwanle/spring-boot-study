package com.mybatis.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: lele
 * @date: 2022/11/30 22:04
 * @description: MySQL 分页生成插件
 */
public class MySqlPaginationPlugin extends PluginAdapter {

    private static String mapper_package;
    private static String page_path;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("generator");
        mapper_package = resourceBundle.getString("mapper.package");
        page_path = resourceBundle.getString("page.path");
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // add field, getter, setter for limit clause
        addPage(topLevelClass, introspectedTable, "page");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //设置getMapper
//		addGetMapperClassNameMethodToBeans(topLevelClass, introspectedTable, "mapper");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement page = new XmlElement("if");
        page.addAttribute(new Attribute("test", "page != null"));
        page.addElement(new TextElement("limit #{page.begin} , #{page.end}"));
        element.addElement(page);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(page_path));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(page_path));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(page_path), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(page_path));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /***
     *自定义定制 生成mapper
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addGetMapperClassNameMethodToBeans(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setTransient(true);
        field.setType(new FullyQualifiedJavaType("java.lang.String"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        method.setName("get" + camel);
        method.addBodyLine("return \"" + mapper_package + "\"+this.getClass().getName().substring(this.getClass().getName().lastIndexOf(\".\"))+\"" + camel + "\";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
}
