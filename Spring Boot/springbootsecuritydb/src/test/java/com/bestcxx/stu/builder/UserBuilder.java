package com.bestcxx.stu.builder;

/**
 * 只能通过构造器方法对实体进行值的修改
 * @author Administrator
 *
 */
public class UserBuilder {
	
	private final int number;
	private final int name;
	private final  int age;
	
	public static class Builder{
		 
		private final int number;
		
		//设置默认值
		private int name=0;
		private int age=0;
		
		public Builder(int number){
			this.number=number;
		}

		public Builder name(int val){
			name=val;return this;
		}
		
		public Builder age(int val){
			age=val;return this;
		}
		
		public UserBuilder build(){
			return new UserBuilder(this);
		}
		
	}
	
	private UserBuilder(Builder builder){
		number=builder.number;
		name=builder.name;
		age=builder.age;
	}
	
	public static void main(String[] args) {
		UserBuilder u=new UserBuilder.Builder(1).name(10).age(20).build();
		System.out.println(u.number);
		System.out.println(u.age);
		System.out.println(u.name);
		
		
	}

	

}
