<html>

<#include "../common/header.ftlh">

<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftlh">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}<br></td>
                                <td>${orderDTO.customerName}</td>
                                <td>${orderDTO.customerPhone}</td>
                                <td>${orderDTO.customerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.orderStatus}</td>
                                <td>${orderDTO.payStatus}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}">详情</a></td>
                                <#if orderDTO.getOrderStatus() == 0>
                                    <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a></td>
                                <#else>

                                </#if>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if orderDTOPage.getTotalPages() gte 12>
                                <#if index == 1>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                <#elseif index == orderDTOPage.getTotalPages()>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                <#elseif index lt currentPage - 5>

                                <#elseif index == currentPage - 5>
                                    <li class="disabled"><a href="#">...</a></li>
                                <#elseif index == currentPage + 5>
                                    <li class="disabled"><a href="#">...</a></li>
                                <#elseif index gt currentPage + 5>

                                <#else>
                                    <#if currentPage == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                    </#if>
                                </#if>
                            <#else>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                </#if>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var websocket = null;
    if ('websocket' in window) {
        websocket = new WebSocket('ws://127.0.0.1:8080/sell/webSocket');

    } else {
        alert('该浏览器不支持websocket');
    }

    // 监测websocket生命周期函数
    websocket.onopen = function (event) {
        console.log('【建立连接】');
    };

    websocket.onclose = function (event) {
        console.log('【建立连接】')
    };

    websocket.onmessage = function (event) {
        console.log('【收到消息】' + event.data)
        // 弹窗提醒，播放音乐
    };

    websocket.onerror = function (event) {
        alert('【连接错误】websocket通信发生错误');
    };

    window.onbeforeunload = function (event) {
        websocket.close();
    }
</script>
</body>
</html>