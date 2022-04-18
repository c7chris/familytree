/**
 * 
 */
package com.chris.familytree.restobjects;

/**
 * @author chris
 *
 */
public class FamilyTreeResponseObject {

	private String name;
	
	private int age;


	/**
	 * @param name
	 * @param age
	 */
	public FamilyTreeResponseObject(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
