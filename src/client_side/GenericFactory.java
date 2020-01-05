package client_side;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GenericFactory<Product> {
	
	private interface Creator<Product>{
		public Product create();
	}
	
	Map<String,Creator> map;
	
	public GenericFactory(){
		map=new HashMap<>();
	}
	
	public void insertProduct(String key, Class<? extends Product> c) {
        map.put(key, new Creator<Product>(){
            @Override
            public Product create() {
                    try {
                            return c.getDeclaredConstructor().newInstance();
                    }
                    catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                    }
                return null;
            }
        });
	}
	
	public Product getNewProduct(String key){
        if (map.containsKey(key))
            return (Product)map.get(key).create();
		return null;
	}
}
