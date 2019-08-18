/**
 * 
 */
package com.chris.familytree.restobjects;

/**
 * @author chris
 *
 */
public class FamilyTreeAddMemberRequest {
	private String name; 
	private int age;
	private String parent; 
	private String partner;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
}
