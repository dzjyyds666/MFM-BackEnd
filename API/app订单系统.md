# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app订单系统


[TOC]






# app订单管理


## 更新订单


**接口地址**:`/app/order/updateOrder`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "stausId": 0,
  "orderNumber": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderStatusVo|OrderStatusVo|body|true|OrderStatusVo|OrderStatusVo|
|&emsp;&emsp;stausId|||false|integer(int32)||
|&emsp;&emsp;orderNumber|||false|string||


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


## 创建订单


**接口地址**:`/app/order/createOrder`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "orderNumber": "",
  "stausId": 0,
  "total": 0,
  "userId": 0,
  "foodIdList": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderVo|OrderVo|body|true|OrderVo|OrderVo|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;orderNumber|||false|string||
|&emsp;&emsp;stausId|||false|integer(int32)||
|&emsp;&emsp;total|||false|number||
|&emsp;&emsp;userId|||false|integer(int64)||
|&emsp;&emsp;foodIdList|||false|array|integer(int32)|


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


## 根据订单id获取订单信息


**接口地址**:`/app/order/GetOrderById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListOrderInfoVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|OrderInfoVo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;orderNumber||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;stausId||integer(int32)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;total||number||
|&emsp;&emsp;foodInfoList||array|FoodInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;foodName||string||
|&emsp;&emsp;&emsp;&emsp;picUrl||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;isRecommend||string||
|&emsp;&emsp;&emsp;&emsp;statusId||integer||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;foodKey||string||
|&emsp;&emsp;orderFoodRelationList||array|OrderFoodRelation|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;orderId||integer||
|&emsp;&emsp;&emsp;&emsp;foodId||integer||
|&emsp;&emsp;&emsp;&emsp;number||integer||
|&emsp;&emsp;&emsp;&emsp;isDelete||boolean||
|&emsp;&emsp;orderStatus||OrderStatus|OrderStatus|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;statusName||string||
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
			"orderNumber": "",
			"createTime": "",
			"stausId": 0,
			"isDelete": "",
			"userId": 0,
			"total": 0,
			"foodInfoList": [
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
					"foodKey": ""
				}
			],
			"orderFoodRelationList": [
				{
					"id": 0,
					"orderId": 0,
					"foodId": 0,
					"number": 0,
					"isDelete": true
				}
			],
			"orderStatus": {
				"id": 0,
				"statusName": "",
				"createTime": "",
				"updateTime": "",
				"isDelete": ""
			}
		}
	]
}
```