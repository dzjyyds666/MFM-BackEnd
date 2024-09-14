# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app登录系统


[TOC]






# app登录管理


## 登录


**接口地址**:`/app/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "phone": "",
  "password": "",
  "captchaKey": "",
  "captchaCode": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|loginInfoVo|后台管理系统登录信息|body|true|LoginInfoVo|LoginInfoVo|
|&emsp;&emsp;phone|电话号码||false|string||
|&emsp;&emsp;password|密码||false|string||
|&emsp;&emsp;captchaKey|验证码key||false|string||
|&emsp;&emsp;captchaCode|验证码code||false|string||


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


## 注册


**接口地址**:`/app/login/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "phone": "",
  "password": "",
  "avatarUrl": "",
  "nickname": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "isDelete": "",
  "cityId": 0,
  "role": "",
  "provinceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userInfo|UserInfo|body|true|UserInfo|UserInfo|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;avatarUrl|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;isDelete|||false|string(byte)||
|&emsp;&emsp;cityId|||false|integer(int32)||
|&emsp;&emsp;role|||false|string||
|&emsp;&emsp;provinceId|||false|integer(int32)||


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


## 退出登录


**接口地址**:`/app/login/logout`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


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


## 获取验证码


**接口地址**:`/app/login/getcaptcha`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultCaptchaVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||CaptchaVo|CaptchaVo|
|&emsp;&emsp;image|验证码图片信息|string||
|&emsp;&emsp;key|验证码key|string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"image": "",
		"key": ""
	}
}
```