# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app用户系统


[TOC]






# app用户信息管理


## 更新用户个人信息


**接口地址**:`/app/user/updateInfo`


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


## 获取用户个人信息


**接口地址**:`/app/user/getUserInfo`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultUserInfoVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||UserInfoVo|UserInfoVo|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;phone||string||
|&emsp;&emsp;password||string||
|&emsp;&emsp;avatarUrl||string||
|&emsp;&emsp;nickname||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;cityId||integer(int32)||
|&emsp;&emsp;role||string||
|&emsp;&emsp;provinceId||integer(int32)||
|&emsp;&emsp;cityInfo||CityInfo|CityInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;cityName||string||
|&emsp;&emsp;&emsp;&emsp;provinceId||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||
|&emsp;&emsp;provinceInfo||ProvinceInfo|ProvinceInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;provinceName||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
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
		"provinceId": 0,
		"cityInfo": {
			"id": 0,
			"cityName": "",
			"provinceId": 0,
			"createTime": "",
			"isDelete": ""
		},
		"provinceInfo": {
			"id": 0,
			"provinceName": "",
			"createTime": "",
			"isDelete": ""
		}
	}
}
```


# app用户地址管理


## 获取省份列表


**接口地址**:`/app/user/getProvinceList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListProvinceInfo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|ProvinceInfo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;provinceName||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"provinceName": "",
			"createTime": "",
			"isDelete": ""
		}
	]
}
```


## 获取城市列表


**接口地址**:`/app/user/getCityList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provinceId||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCityInfo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|CityInfo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;cityName||string||
|&emsp;&emsp;provinceId||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"cityName": "",
			"provinceId": 0,
			"createTime": "",
			"isDelete": ""
		}
	]
}
```