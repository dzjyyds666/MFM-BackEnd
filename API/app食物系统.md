# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app食物系统


[TOC]






# app食物管理


## 根据foodKey获取食物信息


**接口地址**:`/app/food/getFoodInfoByKey`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|foodKey||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListFoodInfoVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|FoodInfoVo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;foodName||string||
|&emsp;&emsp;picUrl||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;price||number||
|&emsp;&emsp;isRecommend||string(byte)||
|&emsp;&emsp;statusId||integer(int32)||
|&emsp;&emsp;description||string||
|&emsp;&emsp;foodKey||string||
|&emsp;&emsp;foodStatus||FoodStatus|FoodStatus|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;statusName||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||
|&emsp;&emsp;foodLabelList||array|FoodLabel|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;labelName||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"foodName": "",
			"picUrl": "",
			"createTime": "",
			"updateTime": "",
			"isDelete": "",
			"price": 0,
			"isRecommend": "",
			"statusId": 0,
			"description": "",
			"foodKey": "",
			"foodStatus": {
				"id": 0,
				"statusName": "",
				"createTime": "",
				"updateTime": "",
				"isDelete": ""
			},
			"foodLabelList": [
				{
					"id": 0,
					"labelName": "",
					"createTime": "",
					"updateTime": "",
					"isDelete": ""
				}
			]
		}
	]
}
```


## 根据foodId获取食物信息


**接口地址**:`/app/food/getFoodInfoById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|foodId||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultFoodInfoVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||FoodInfoVo|FoodInfoVo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;foodName||string||
|&emsp;&emsp;picUrl||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;price||number||
|&emsp;&emsp;isRecommend||string(byte)||
|&emsp;&emsp;statusId||integer(int32)||
|&emsp;&emsp;description||string||
|&emsp;&emsp;foodKey||string||
|&emsp;&emsp;foodStatus||FoodStatus|FoodStatus|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;statusName||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||
|&emsp;&emsp;foodLabelList||array|FoodLabel|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;labelName||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"foodName": "",
		"picUrl": "",
		"createTime": "",
		"updateTime": "",
		"isDelete": "",
		"price": 0,
		"isRecommend": "",
		"statusId": 0,
		"description": "",
		"foodKey": "",
		"foodStatus": {
			"id": 0,
			"statusName": "",
			"createTime": "",
			"updateTime": "",
			"isDelete": ""
		},
		"foodLabelList": [
			{
				"id": 0,
				"labelName": "",
				"createTime": "",
				"updateTime": "",
				"isDelete": ""
			}
		]
	}
}
```