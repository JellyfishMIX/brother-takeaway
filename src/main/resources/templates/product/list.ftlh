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
                            <th>商品Id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}<br></td>
                                <td>${productInfo. productName}</td>
                                <td><img height="100" width="100" src="${productInfo.productIcon}" alt="图片加载遇到了问题"></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <#if productInfo.productStatus == 0>
                                    <td><a href="/sell/seller/product/up?productId=${productInfo.productId}">下架</a></td>
                                <#else>
                                    <td><a href="/sell/seller/product/down?productId=${productInfo.productId}">上架</a></td>
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
                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if productInfoPage.getTotalPages() gte 12>
                                <#if index == 1>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                <#elseif index == productInfoPage.getTotalPages()>
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
                        <#if currentPage gte productInfoPage.getTotalPages()>
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