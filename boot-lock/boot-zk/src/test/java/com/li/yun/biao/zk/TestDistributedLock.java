package com.li.yun.biao.zk;

import com.li.yun.biao.zk.service.impl.SimpleDistributedLockMutex;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 10:11 上午
 * @description
 */
public class TestDistributedLock {

    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final ZkClientExt zkClientExt1 = new ZkClientExt("47.98.178.129:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex1 = new SimpleDistributedLockMutex(zkClientExt1, "/shier");

        final ZkClientExt zkClientExt2 = new ZkClientExt("47.98.178.129:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex2 = new SimpleDistributedLockMutex(zkClientExt2, "/shier");

        final SimpleDistributedLockMutex mutex3 = new SimpleDistributedLockMutex("/shier");

        try {
            mutex1.acquire();
            out.println(dateFormat.format(new Date()) + "     Client1 locked");
            Thread client2Thd = new Thread(() -> {
                try {
                    mutex2.acquire();
                    out.println(dateFormat.format(new Date()) + "     Client2 locked1");
                    mutex2.release();
                    out.println(dateFormat.format(new Date()) + "     Client2 released lock1");
                    mutex2.acquire();
                    out.println(dateFormat.format(new Date()) + "     Client2 locked2");
                    Thread.sleep(5000);
                    mutex2.release();
                    out.println(dateFormat.format(new Date()) + "     Client2 released lock2");
                    out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            client2Thd.start();
            new Thread(() -> {
                try {
                    mutex3.acquire();
                    out.println(dateFormat.format(new Date()) + "     Client3 locked1");
                    mutex3.release();
                    out.println(dateFormat.format(new Date()) + "     Client3 released lock1");
                    mutex3.acquire();
                    out.println(dateFormat.format(new Date()) + "     Client3 locked2");
                    mutex3.release();
                    out.println(dateFormat.format(new Date()) + "     Client3 released lock2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(5000);
            mutex1.release();
            out.println(dateFormat.format(new Date()) + "     Client1 released lock");
            out.println();
            client2Thd.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
