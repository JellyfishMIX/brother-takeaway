<html>
<head>
    <meta charset="utf-8">
    <title>卖家端查询订单详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <#--订单概要-->
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单Id</th>
                    <th>订单总金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.getOrderId()}</td>
                    <td>${orderDTO.getOrderAmount()}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <#--订单详情表数据-->
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品Id</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.getOrderDetailList() as orderDetail>
                    <tr>
                        <td>${orderDetail.getProductId()}</td>
                        <td>${orderDetail.getProductName()}</td>
                        <td>${orderDetail.getProductPrice()}</td>
                        <td>${orderDetail.getProductQuantity()}</td>
                        <td>${orderDetail.getProductPrice() * orderDetail.getProductQuantity()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

        <#--操作按钮-->
        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatus() == 0>
                <a href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
        </div>
    </div>
</div>
</body>
</html>