<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

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
</body>
</html>