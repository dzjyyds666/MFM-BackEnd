# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app文件系统


[TOC]






# app文件管理


## 上传文件


**接口地址**:`/app/file/upload`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": ""
}
```