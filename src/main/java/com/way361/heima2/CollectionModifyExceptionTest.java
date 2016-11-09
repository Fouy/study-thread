package com.way361.heima2;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合测试
 * ArrayList 在迭代时不可以修改内容，否则会出现死循环等错误。
 * 此时可以使用CopyOnWriteArrayList ，则可以避免这种情况。
 * @author huge
 *
 */
public class CollectionModifyExceptionTest {
	
	public static void main(String[] args) {
		
		Collection<User> users = new CopyOnWriteArrayList<User>();

		// new ArrayList();
		users.add(new User("张三", 28));
		users.add(new User("李四", 25));
		users.add(new User("王五", 31));
		
		Iterator<User> itrUsers = users.iterator();
		while (itrUsers.hasNext()) {
			System.out.println("aaaa");
			User user = (User) itrUsers.next();
			if ("李四".equals(user.getName())) {
				users.remove(user);
				// itrUsers.remove();
			} else {
				System.out.println(user);
			}
		}
	}
}
