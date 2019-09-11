import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;

import java.util.Iterator;
import java.util.Set;

public class Redis {

    @Test
    public void RedisTest() {

        Jedis jedis=new Jedis("localhost");
        Jedis shardedJedis=new Jedis("localhost");
//        System.out.println("成功");
//        System.out.println("服务正在运行: "+jedis.ping());
        shardedJedis.flushDB();
        jedis.flushDB();
//        //ShardedJedis shardedJedis=;
//        System.out.println("判断key999键是否存在："+jedis.exists("key999"));
//        System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001"));
//        System.out.println("判断key001是否存在："+jedis.exists("key001"));
//        System.out.println("新增key002,value002键值对："+jedis.set("key002", "value002"));
//        Set<String> keys=jedis.keys("*");
//        Iterator<String> iterator=keys.iterator();
//        while (iterator.hasNext()){
//            String key=iterator.next();
//            System.out.println(key);
//        }
//        System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
//        try{
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e){
//        }
//        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
//        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
//        // 移除某个key的生存时间
//        System.out.println("移除key001的生存时间："+jedis.persist("key001"));
//        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
//        // 查看key所储存的值的类型
//        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
//        System.out.println("一次性新增key201,key202,key203,key204及其对应值："+jedis.mset("key201","value201",
//                "key202","value202","key203","value203","key204","value204"));
//        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
//                jedis.mget("key201","key202","key203","key204"));
//        System.out.println("一次性删除key201,key202："+jedis.del(new String[]{"key201", "key202"}));
//        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
//                jedis.mget("key201","key202","key203","key204"));
//
        System.out.println("向sets集合中加入元素element001："+jedis.sadd("sets", "element001"));
        System.out.println("向sets集合中加入元素element002："+jedis.sadd("sets", "element002"));
        System.out.println("向sets集合中加入元素element003："+jedis.sadd("sets", "element003"));
        System.out.println("向sets集合中加入元素element004："+jedis.sadd("sets", "element004"));
        System.out.println("sets1中添加元素element002："+jedis.sadd("sets2", "element002"));
        System.out.println("sets1中添加元素element003："+jedis.sadd("sets2", "element003"));
        System.out.println("sets1中添加元素element004："+jedis.sadd("sets2", "element004"));
        System.out.println("set中的所有集合:"+jedis.smembers("sets"));
        System.out.println("删除set中的一个:"+jedis.srem("sets","element003"));
        System.out.println(jedis.smembers("sets"));
        System.out.println("查询set中是否有指定value:"+jedis.sismember("sets","element004"));
        System.out.println("sets1和sets2交集："+jedis.sinter("sets", "sets2"));
        System.out.println("sets1和sets2并集："+jedis.sunion("sets", "sets2"));
        System.out.println("sets1和sets2差集："+jedis.sdiff("sets", "sets2"));

    }
}
