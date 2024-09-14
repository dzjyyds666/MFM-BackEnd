# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app促销系统


[TOC]






# app促销管理


## 抢购订单


**接口地址**:`/app/salePromotion/snapped`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|integer(int32)||


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


## 获取促销列表


**接口地址**:`/app/salePromotion/getSalePromotionList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListSalesPromotionVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|SalesPromotionVo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;promotionName||string||
|&emsp;&emsp;beginTime||string(date-time)||
|&emsp;&emsp;endTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;foodId||integer(int32)||
|&emsp;&emsp;number||integer(int32)||
|&emsp;&emsp;price||number||
|&emsp;&emsp;isShelves||integer(int32)||
|&emsp;&emsp;foodInfo||FoodInfo|FoodInfo|
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


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"promotionName": "",
			"beginTime": "",
			"endTime": "",
			"isDelete": "",
			"foodId": 0,
			"number": 0,
			"price": 0,
			"isShelves": 0,
			"foodInfo": {
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
		}
	]
}
```