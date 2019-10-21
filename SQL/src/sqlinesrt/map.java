package sqlinesrt;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class map {

    @Test
    public void createMap(){
        Map<String,Object> map = new ConcurrentHashMap();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.put("5","5");
        for( Map.Entry<String,Object> entry:map.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
        System.out.println("-----------------------------------");
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            System.out.println(entry.getKey()+"\t"+entry.getValue());
            iterator.remove();
            iterator.notifyAll();
        }
        System.out.println("-----------------------------------");
        for( Map.Entry<String,Object> entry:map.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
