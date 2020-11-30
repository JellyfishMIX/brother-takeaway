create table `tb_product_info` (
    `product_id` varchar(32) not null ,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8, 2) not null comment '单价',
    `product_stock` int not null comment '库存',
    `product_description` varchar(64) comment '描述',
    `product_icon` varchar(512) comment '商品小图',
    `category_type` int not null comment '类目编号',
    `product_status` tinyint(3) not null default '0' comment '商品状态，0上架，1下架',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`product_id`)
) comment '商品表';

create table `tb_product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '类目名字',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`),
    unique key `unique_category_type` (category_type)
) comment '商品类目表';

create table `tb_order_master` (
    `order_id` varchar(32) not null ,
    `customer_name` varchar(32) not null comment '顾客名字',
    `customer_phone` varchar(32) not null comment '顾客电话',
    `customer_address` varchar(128) not null comment '顾客地址',
    `customer_openid` varchar(64) not null comment '顾客openid',
    `order_amount` decimal(8, 2) not null comment '订单总金额',
    `order_status` tinyint(3) not null default '0' comment '支付状态，默认0新增订单',
    `pay_status` tinyint(3) not null default '0' comment '支付状态，默认0未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`),
    key `index_customer_openid` (`customer_openid`)
) comment '订单表';

create table `tb_order_detail` (
    `detail_id` varchar(32) not null ,
    `order_id` varchar(32) not null ,
    `product_id` varchar(32) not null ,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8, 2) not null comment '商品价格',
    `product_quantity` int not null comment '商品数量',
    `product_icon` varchar(512) comment '商品小图',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`detail_id`),
    key `index_order_id` (`order_id`)
) comment '订单详情表';

create table `user_info` (
    `id` varchar(32) not null,
    `username` varchar(32) default '',
    `password` varchar(32) default '',
    `openid` varchar(64) default '' comment '微信openid',
    `role` int not null comment '1买家2卖家',
    `creation_time` timestamp not null default current_timestamp comment '创建时间，自动写入',
    `modified_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间，自动写入',
    primary key (`id`)
) comment '用户表';