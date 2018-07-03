package cn.mrcode.newstudy.hpbase.mysql.mymysql2;

import cn.mrcode.newstudy.hpbase.mysql.mymysql2.frontend.NIOAcceptor;
import org.testng.annotations.Test;

/**
 * ${desc}
 * @author zhuqiang
 * @version 1.0.1 2018/6/28 17:09
 * @date 2018/6/28 17:09
 * @since 1.0
 */
public class NIORactorTest {

    @Test
    public void testRun() throws Exception {
        NIORactor nioRactor = new NIORactor();
        nioRactor.start();
        // 数据库编码是 utf-8的
//        nioRactor.register("localhost", 3306, "root",
//                "123456", "mycat_dev_test_1",
//                "UTF-8");
        // 该示例 前半部分 建立和mysql服务器的连接，握手包解析，登录认证包的构造发送
        // 实现查询命令，与解析响应结果； 完全的分开写的。写完前部分再写后面部分
        // 所以可以单独运行前半部分

        // 后半部分：前段连接模拟mysql服务器，接收sql转发到后段连接执行完成后，再把结果直接转发到前段
        // 测试前段连接的时候；由于代码没有写完整，接收的前段连接中的验证包没有验证
        // 相关设置编码的命令也没有开发，所以更换成 使用mysql在命令行连接的时候
        // 使用的是28的索引 gbk_chinese_ci （在win10下mysql5.7中）
        // 编码不对的话将会造成数据乱码
        // 连接命令 mysql -uroot -p123456 -P8166
        nioRactor.register("localhost", 3306, "root",
                "123456", "mycat_dev_test_1",
                "GBK");
        new NIOAcceptor(8166, nioRactor).start();
        nioRactor.join();
    }
}