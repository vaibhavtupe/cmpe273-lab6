package edu.sjsu.cmpe.cache.client;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
public class Client {

    public static void main(String[] args) throws Exception {
    	
    	// Consistent Hashing functionality
    	
    	CacheServiceInterface cache1 = new DistributedCacheService("http://localhost:3000");
 	    CacheServiceInterface cache2 = new DistributedCacheService("http://localhost:3001");
 	    CacheServiceInterface cache3 = new DistributedCacheService("http://localhost:3002");
 	    //add it in bucket called array
 	    CacheServiceInterface[] buckets = {cache1, cache2, cache3};
 	    
 	    //take data
 	    Map<Long, String> dataMap =  new HashMap<Long, String>();
 	    dataMap.put((long)1, "a");
 	    dataMap.put((long)2, "b");
 	    dataMap.put((long)3, "c");
 	    dataMap.put((long)4, "d");
 	    dataMap.put((long)5, "e");
 	    dataMap.put((long)6, "f");
 	    dataMap.put((long)7, "g");
 	    dataMap.put((long)8, "h");
 	    dataMap.put((long)9, "i");
 	    dataMap.put((long)10, "j");
 	 

 	    //put 
 	    for(long i=1; i<=dataMap.size(); i++){
 	    	int bucketNumber = ConsistentHash.getConsistentHash("md5", i, buckets.length);
 	    	buckets[bucketNumber].put(i, dataMap.get(i));
 	    	System.out.println("put("+ i +"=>"+ dataMap.get(i) +") @ http://localhost:300" + bucketNumber);
 	    	 
 	    }
 	    
 	    
 	    //get 
 	    for(long i=1; i<=dataMap.size(); i++){
 	    	int bucketNumber = ConsistentHash.getConsistentHash("md5", i, buckets.length);
 	    	String value = buckets[bucketNumber].get(i);
 	    	System.out.println("get("+ i +") => "+ value + " @ http://localhost:300" + bucketNumber);
 	    }
    	
 	    
 	    
 	    // Enable below code to make RendezvousHash functionality
    	/*
    	final Funnel<CharSequence> strFunnel = Funnels.stringFunnel();
        HashFunction hf = Hashing.md5();
        System.out.println("Starting Cache Client...");
        String node1 = "http://localhost:3000";
        String node2 = "http://localhost:3001";
        String node3 = "http://localhost:3002";
        CacheServiceInterface cache1 = new DistributedCacheService(node1);
        CacheServiceInterface cache2 = new DistributedCacheService(node2);
        CacheServiceInterface cache3 = new DistributedCacheService(node3);
        Map<Integer, String> objects = new HashMap<Integer, String>();
        objects.put(1, "a");
        objects.put(2, "b");
        objects.put(3, "c");
        objects.put(4, "d");
        objects.put(5, "e");
        objects.put(6, "f");
        objects.put(7, "g");
        objects.put(8, "h");
        objects.put(9, "i");
        objects.put(10, "j");
        List<String> al = new ArrayList<String>(); 
        al.add(node1);
        al.add(node2);
        al.add(node3);
        

        RendezvousHash hashAl = new RendezvousHash(hf, strFunnel, strFunnel, al);
        
        String obj1 =(String)hashAl.get("1");
        String obj2 =(String)hashAl.get("2");
        String obj3 =(String)hashAl.get("3");
        String obj4 =(String)hashAl.get("4");
        String obj5 =(String)hashAl.get("5");
        String obj6 =(String)hashAl.get("6");
        String obj7 =(String)hashAl.get("7");
        String obj8 =(String)hashAl.get("8");
        String obj9 =(String)hashAl.get("9");
        String obj10 =(String)hashAl.get("10");


        ArrayList<String> objects = new ArrayList<String>();
        objects.add(0, "");
        objects.add(1, obj1);
        objects.add(2, obj2);
        objects.add(3, obj3);
        objects.add(4, obj4);
        objects.add(5, obj5);
        objects.add(6, obj6);
        objects.add(7, obj7);
        objects.add(8, obj8);
        objects.add(9, obj9);
        objects.add(10, obj10);

        for(int i=1;i<=10;i++) {
        	System.out.println(objects.get(i)+" : "+i+" -> "+objects.get(i));
        }
        String node="";
        for(int i = 1;i<objNodes.size();i++) {

            node = objNodes.get(i);
            if(node.equals(node1)) cache1.put(i, objects.get(i));
            else if(node.equals(node2)) cache2.put(i, objects.get(i));
            else if(node.equals(node3)) cache3.put(i, objects.get(i));
        }
        
        
        */
        

        
        System.out.println("Existing Cache Client...");
    }



}
