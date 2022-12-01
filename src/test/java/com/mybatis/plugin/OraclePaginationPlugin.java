package com.mybatis.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: lele
 * @date: 2022/11/30 22:08
 * @description: Oracle 分页生成插件
 */
public class OraclePaginationPlugin extends PluginAdapter {

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
        addGetMapperClassNameMethodToBeans(topLevelClass, introspectedTable, "mapper");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();

        // 产生分页语句前半部分
        XmlElement paginationPrefixElement = new XmlElement("sql");
        paginationPrefixElement.addAttribute(new Attribute("id", "OracleDialectPrefix"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "page != null"));
        pageStart.addElement(new TextElement("select * from ( select row_.*, rownum rownum_ from ( "));
        paginationPrefixElement.addElement(pageStart);
        parentElement.addElement(paginationPrefixElement);

        // 产生分页语句后半部分
        XmlElement paginationSuffixElement = new XmlElement("sql");
        paginationSuffixElement.addAttribute(new Attribute("id", "OracleDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "page != null"));
        pageEnd.addElement(new TextElement("<![CDATA[ ) row_ ) where rownum_ > #{page.begin} and rownum_ <= #{page.end} ]]>"));
        paginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(paginationSuffixElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement pageStart = new XmlElement("include"); //$NON-NLS-1$
        pageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        element.getElements().add(0, pageStart);

        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
        element.getElements().add(isNotNullElement);

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
     * 自定义定制 生成mapper
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addGetMapperClassNameMethodToBeans(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        method.setName("get" + camel);
        method.addBodyLine("return \"" + OraclePaginationPlugin.mapper_package + "\"+this.getClass().getName().substring(this.getClass().getName().lastIndexOf(\".\"))+\"" + camel + "\";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }


    /**
     * 生成字段字注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            // addJavadocTag(field, false);
            field.addJavaDocLine(" */");
        }
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
}
