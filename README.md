# brother-takeaway
A SpringBoot project for learning, learned from lly835.

## Environmental Parameters

### JDK

jdk@11.0.4

### Dependency

junit@5.5.2

weixin-java-mp@2.9.0

best-pay-sdk@1.3.0

freemarker@2.3.28

### Database

mysql@8.0.17

redis@3.2.8

### Server

VirtualBox@6.1--CentOS@7.3

## 亮点

1.

me/jmix/brothertakeaway/utils/EnumUtil.java

templates/order/list.ftlh

使用自定义枚举工具，解决订单状态由整型数字转变为说明文字的需求

2.

me/jmix/brothertakeaway/service/RedisLock

引入Redis分布式锁
