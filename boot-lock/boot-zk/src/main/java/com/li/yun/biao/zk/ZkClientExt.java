package com.li.yun.biao.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.data.Stat;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 10:10 上午
 * @description zk客户端拓展
 */
public class ZkClientExt extends ZkClient {

    public ZkClientExt(String zkServers, int sessionTimeout, int connectionTimeout, ZkSerializer zkSerializer) {
        super(zkServers, sessionTimeout, connectionTimeout, zkSerializer);
    }

    @Override
    public void watchForData(final String path) {
        retryUntilConnected(() -> {
            _connection.readData(path, new Stat(), true);
            return null;
        });
    }

}