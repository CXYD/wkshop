package org.mybatis.generator.custom.javamapper;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;

import java.util.List;

/**
 * Created by dylan on 15-12-16.
 */
public class GenericJavaMapperGenerator extends JavaMapperGenerator {
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> types = super.getCompilationUnits();
        for(CompilationUnit type : types) {
            if(type instanceof Interface) {
                FullyQualifiedJavaType superInterface = type.getSuperInterfaceTypes().iterator().next();
                superInterface.addTypeArgument(getBaseRecordType());
                superInterface.addTypeArgument(getExampleType());
            }
        }
        return types;
    }
    protected FullyQualifiedJavaType getBaseRecordType() {
        FullyQualifiedJavaType listType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            listType = new FullyQualifiedJavaType(introspectedTable
                    .getRecordWithBLOBsType());
        } else {
            // the blob fields must be rolled up into the base class
            listType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        }
        return listType;
    }

    protected FullyQualifiedJavaType getExampleType() {
        return new FullyQualifiedJavaType(introspectedTable.getExampleType());
    }
}
