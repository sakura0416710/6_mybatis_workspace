package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
 //getSQLsession 만들기 = 이제 얘가 JDBC를 대신함
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		
		try {
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			
			InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory ssf = ssfb.build(stream);
			session = ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return session;
	}
	
	
	
	
}
