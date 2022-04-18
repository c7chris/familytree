package org.chris.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.chris.constants.FamilyTreeConstants;
import org.chris.exceptions.KidAlreadyHasMaximumParentsException;
import org.chris.exceptions.MaximumParentsReachedException;

/**
 * @author chris
 *
 */
public class FamilyTreeNode {
	
	/**
	 * Name of the family member
	 */
	private String name = null;
	
	/**
	 * Age of the family member
	 */
	private int age;
	
	/**
	 * Partner of the family member
	 */
	private String partner = null;
	
	/**
	 * Parents of the family member
	 */
	private Map<String, FamilyTreeNode> parents = null;
	
	/**
	 * Children of the family member
	 */
	private Map<String, FamilyTreeNode> kids = null;

	/**
	 * @param name
	 */
	public FamilyTreeNode(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param name
	 * @param age
	 */
	public FamilyTreeNode(String name, int age) {
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

	/**
	 * @return the parents
	 */
	public Map<String, FamilyTreeNode> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 * @throws MaximumParentsReachedException 
	 */
	public void setParents(Map<String, FamilyTreeNode> parents) throws MaximumParentsReachedException {
		if(!(parents.size() > 2)) { //Ensuring that a family member cannot have more than two parents
			this.parents = parents;
		} else {
			throw new MaximumParentsReachedException();
		}
	}

	/**
	 * @param parent
	 * @return 
	 * @throws MaximumParentsReachedException 
	 */
	public FamilyTreeNode addParent(FamilyTreeNode parent) throws MaximumParentsReachedException {
		this.addParentToKid(parent);
		if(null == parent.getKids() || !parent.getKids().containsKey(this.getName())) {
			parent.addKidToParent(this);
		}
		return this;
	}
	
	/**
	 * @param parent
	 * @return
	 * @throws MaximumParentsReachedException
	 */
	private void addParentToKid(FamilyTreeNode parent) throws MaximumParentsReachedException {
		if(null != this.parents && this.parents.size() == 2) {
			throw new MaximumParentsReachedException("Parents: "+this.parents.entrySet());
		}
		if(null != this.parents && this.parents.containsKey(FamilyTreeConstants.ROOT_NODE_NAME)) {
			this.parents.remove(FamilyTreeConstants.ROOT_NODE_NAME);
		}
		if(null == this.parents) {
			this.parents = new HashMap<String, FamilyTreeNode>();
		}
		this.parents.put(parent.getName(), parent);
	}
	
	/**
	 * @return the kids
	 */
	public Map<String, FamilyTreeNode> getKids() {
		return kids;
	}

	/**
	 * @param kids the kids to set
	 */
	public void setKids(Map<String, FamilyTreeNode> kids) {
		this.kids = kids;
	}

	/**
	 * @param kid
	 * @return 
	 * @throws KidAlreadyHasMaximumParentsException 
	 */
	public FamilyTreeNode addKid(FamilyTreeNode kid) throws KidAlreadyHasMaximumParentsException {
		if(null == kid.getParents() || !kid.getParents().containsKey(this.getName())) {
			try {
				kid.addParentToKid(this);
			} catch (MaximumParentsReachedException e) {
				throw new KidAlreadyHasMaximumParentsException(e.getMessage());
			}
		}
		if(null == this.kids) {
			this.kids = new HashMap<String, FamilyTreeNode>();
		}
		this.kids.put(kid.getName(), kid);
		return this;
	}

	/**
	 * @param kid
	 * @return 
	 */
	private void addKidToParent(FamilyTreeNode kid) {
		if(null == this.kids) {
			this.kids = new HashMap<String, FamilyTreeNode>();
		}
		this.kids.put(kid.getName(), kid);
	}

	/**
	 * @param partner the partner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FamilyTreeNode)) {
			return false;
		}
		FamilyTreeNode other = (FamilyTreeNode) obj;
		return Objects.equals(name, other.name);
	}
	
}
