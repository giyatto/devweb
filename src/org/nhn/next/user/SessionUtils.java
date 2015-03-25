package org.nhn.next.user;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	
	public static boolean isEmpty(HttpSession session, String key){
		Object object = session.getAttribute(LoginServlet.SESSION_USER_ID);
		
		if(object==null){
			return true;
		}
		return false;
	}
	
	public static String getStringValue(HttpSession session, String key){
		if(!isEmpty(session, key)){
			return null;
		}
		return (String) session.getAttribute(key);
	}
	
}
