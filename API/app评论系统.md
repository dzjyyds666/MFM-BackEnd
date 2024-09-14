# MFMapp系统


**简介**:MFMapp系统


**HOST**:http://127.0.0.1:8081


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs/app评论系统


[TOC]






# app评论管理


## 发布评论


**接口地址**:`/app/comment/addComment`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "commentContent": "",
  "foodId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentInfovo|CommentInfovo|body|true|CommentInfovo|CommentInfovo|
|&emsp;&emsp;commentContent|||false|string||
|&emsp;&emsp;foodId|||false|integer(int32)||


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


## 根据userId或foodId查询评论


**接口地址**:`/app/comment/getCommentByFoodId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|foodId||query|false|integer(int32)||
|userId||query|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCommentVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|CommentVo|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;commentContent||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;isDelete||string(byte)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;foodId||integer(int32)||
|&emsp;&emsp;isRecommend||string(byte)||
|&emsp;&emsp;userInfo||UserInfo|UserInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;password||string||
|&emsp;&emsp;&emsp;&emsp;avatarUrl||string||
|&emsp;&emsp;&emsp;&emsp;nickname||string||
|&emsp;&emsp;&emsp;&emsp;status||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;isDelete||string||
|&emsp;&emsp;&emsp;&emsp;cityId||integer||
|&emsp;&emsp;&emsp;&emsp;role||string||
|&emsp;&emsp;&emsp;&emsp;provinceId||integer||
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
			"commentContent": "",
			"createTime": "",
			"updateTime": "",
			"isDelete": "",
			"userId": 0,
			"foodId": 0,
			"isRecommend": "",
			"userInfo": {
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
			},
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