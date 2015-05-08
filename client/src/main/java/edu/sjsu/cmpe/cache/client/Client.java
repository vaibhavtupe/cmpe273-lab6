package edu.sjsu.cmpe.cache.client;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws NoSuchAlgorithmException  {
    	
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
 	    
        
    }

}
