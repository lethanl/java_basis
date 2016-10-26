JDK内置Logger(java.util.logging),JDK1.4引入的

1. JDK内置Logger的类结构

参考图片jdk内置logger类图关系.jpg。

2. JDK内置Logger支持的Level

JDK内置 Logger提供了如下七种Logger级别，从高到低依次是：
SEVERE->WARNING->INFO->CONFIG->FINE->FINER->FINESET。

另外，可以使用OFF关闭日志记录，使用 ALL 启用所有消息的日志记录。

3. JDK内置Logger支持的Formatter

JDK Logger支持2种Formatter，包括SimpleFormatter 和 XMLFormatter。其中，
SimpleFormatter以文本的形式记录日志信息；XMLFormatter 以XML格式的形式记录日志信息。

4. JDK内置Logger支持的Handler

Handler，实现将日志写入指定目的地，JDK Logger主要支持MemoryHandler和StreamHandler两个大类Handler，另外ConsoleHanler,
FileHandler以及SocketHandler都是继承自StreamHandler，分别添加了一些自己的功能，分别将日志写入控制台、文件、Socket端口。

ConsoleHandler只是将OutputStream设置为System.err，其他实现和StreamHandler类似。

而SocketHandler将OutputStream绑定到对应的端口号中，其他也和StreamHandler类似。另外它还增加了两个配置：
java.util.logging.SocketHandler.port和java.util.logging.SocketHandler.host分别对应端口号和主机。

FileHandler支持指定文件名模板（java.util.logging.FileHandler.pattern），文件最大支持大小（java.util.logging.FileHandler.limit，字节为单位，0为没有限制），
循环日志文件数（java.util.logging.FileHandler.count）、对已存在的日志文件是否往后添加（java.util.logging.FileHandler.append）。

FileHandler支持的文件模板参数有：
/     目录分隔符
%t    系统临时目录
%h    系统当前用户目录
%g    生成的以区别循环日志文件名
%u    一个唯一的数字以处理冲突问题
%%    一个%

SocketHanlder的例子，参考SocketHandlerTest类。

再来一个MemoryHanlder的例子，参考MemoryHandlerTest类。

5. JDK内置Logger 默认配置文件
6. 如何使用JDK内置logger

使用JDK内置Logger可以分成三个步骤来完成：
first. 创建Logger
second. 创建Handler,为handler指定Formmater, 然后将Handler添加到logger中去。
last. 设定Level级别
参考MyLoggerUtil和JDKLoggerExample类。
