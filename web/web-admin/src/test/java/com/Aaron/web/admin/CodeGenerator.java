package com.Aaron.web.admin;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/MFM?&characterEncoding=utf-8&userSSL=false";
        String username = "root";
        String password = "rootroot";
        String outputUrl = "/Users/zhijundu/code/IdeaProjects/MFM/model/src/main/java/";
        String mapperLocation = "/Users/zhijundu/code/IdeaProjects/MFM/web/web-admin/src/main/resources/mapper/";
//        String tables = "city_info,combo_food_relation,combo_info,comment_info,food_info,food_label,food_label_relation,food_status,food_type,label_status,label_status_relation,order_info,order_status,permission_info,province_info,sales_promotion,user_info,user_permission_relation";
        String tables = "order_food_relation";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Aaron") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputUrl); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.Aaron.MFM.model") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
