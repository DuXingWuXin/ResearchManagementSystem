# 概述

## 需求
![需求](/image/需求.png)

## 用例图
![用例](/image/useCase.png)

## 架构
![架构](/image/架构.png)

## ER图
![ER图](/image/ERD.png)

## 环境要求
Java：java17 

JavaFX：JavaFX17（需要添加javafx的SDK到项目lib） 

MySQL：MySQL8.0（项目中已包含jdbc）

## 项目文件说明
+ /src/lib  
jdbc连接MySQL的jar包

+ /src/main/sql.sql  
创建数据库的sql语句    

+ /src/main/java/model/jdbcConfiguration.java  
连接数据库的相关参数

## 小问题
1. achievement 的 种类 不支持修改，若要修改种类，需要删除再重新添加
2. 相关单位编辑时必须填写 ID 和 相关项目ID
3. 没实现 创建新用户 功能
4. 没实现 查看个人信息 功能
5. 编辑相关信息后，表格中并不实时更新，需要再次查询才更新
6. 创建数据库的sql语句中，有些表的 primary key 设置了自增，可能对增删改查有影响。sql语句仅为创建数据表，并不包含数据，添加登录用户的数据需要在数据库中添加
