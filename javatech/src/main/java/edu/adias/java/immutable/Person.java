package edu.adias.java.immutable;

import java.util.Date;

public final class Person {
	 final private String name;
	 final private Date joiningDate;
	public Person(String name,Date joiningDate){
		this.name=name;
		this.joiningDate=joiningDate;
	}
	public String getName() {
		return name;
	}
	public Date getJoiningDate() {
		return (Date)joiningDate.clone();
	}
	@Override
	public String toString() {
		return "hashCode-->"+this.hashCode()+"\tPerson [name=" + name + ", joiningDate="+ joiningDate + "]";
	}
	
}
