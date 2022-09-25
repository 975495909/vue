package com.example.networkdatatgh

import com.baidubce.http.ApiExplorerClient
import com.baidubce.http.AppSigner
import com.baidubce.http.HttpMethodName
import com.baidubce.model.ApiExplorerRequest
import com.baidubce.model.ApiExplorerResponse
import java.lang.Exception

// 实时降雨量 示例代码
object net {
    @JvmStatic
    fun main(args: Array<String>) {
        val path = "http://gwgp-h4bqkmub7dg.n.bdcloudapi.com/rain"
        val request = ApiExplorerRequest(HttpMethodName.GET, path)
        request.setCredentials(
            "1416264fd7b94eea9e12643a181e9d14",
            "01d7901f59f244dfb3a8d3b37c82fef8"
        )

        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/json; charset=utf-8")
        val client = ApiExplorerClient(AppSigner())
        try {
            val response: ApiExplorerResponse = client.sendRequest(request)
            // 返回结果格式为Json字符串
            System.out.println(response.getResult())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
