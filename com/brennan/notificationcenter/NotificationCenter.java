
package com.brennan.notificationcenter;

import java.util.*;
import java.lang.reflect.*;

public class NotificationCenter {

    private final HashMap<String, LinkedList> observers;

    private static NotificationCenter instance = null;
    
    public static NotificationCenter sharedInstance() {
        if(instance == null) {
            instance = new NotificationCenter();
        }
        return instance;
    }
    
    public NotificationCenter() {
        this.observers = new HashMap<String, LinkedList>();
    }

    public void addObserver(Object listener, String notificationName, String method) {
		Map<String, Object> methodMap = new HashMap<String, Object>(); 
		methodMap.put(method, listener);

        LinkedList list = this.observers.get(notificationName);
        if(list == null) {
            list = new LinkedList();
            this.observers.put(notificationName, list);
        }			

        list.add(methodMap);
    }

    public void postNotification(String name, Object data) {
        LinkedList list = this.observers.get(name);
        if(list != null) {
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                HashMap<String, Object> listenerMap = (HashMap<String, Object>)iter.next();
				Set<Map.Entry<String, Object>> entrySet = listenerMap.entrySet();
				Map.Entry<String, Object> entry = entrySet.iterator().next(); 	
				Object listener = entry.getValue();
				String methodName = entry.getKey();
				try {
					Class[] args = new Class[1];
        			args[0] = Notification.class;
					Method method = listener.getClass().getMethod(methodName, args);
					method.invoke(listener, new Notification(name, data));
                } catch(Exception e) {
					System.out.println(e);
				}
            }
        }
    }

    public void removeObserver(Object listener) {
        Collection values = this.observers.values();
        Iterator iter = values.iterator();
        while(iter.hasNext()) {
            LinkedList list = (LinkedList) iter.next();
			Iterator listIterator = list.iterator();
			while(listIterator.hasNext()) {
				Map<String,Object> map = (Map<String, Object>)listIterator.next();
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				Map.Entry<String, Object> entry = entrySet.iterator().next(); 	
				if(listener.equals(entry.getValue())) {
					list.remove(map);
				}
			}
        }
    }
}
