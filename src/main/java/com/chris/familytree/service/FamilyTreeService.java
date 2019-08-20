/**
 * 
 */
package com.chris.familytree.service;

import java.util.List;

import org.chris.datastructures.FamilyTreeNode;
import org.chris.exceptions.MaximumParentsReachedException;

import com.chris.familytree.restobjects.FamilyTreeResponseObject;

/**
 * @author chris
 *
 */
public interface FamilyTreeService {
	
	void addFamilyMember(String name, int age, String parent, String partner) throws MaximumParentsReachedException;
	
	FamilyTreeNode fetchFamilyMember(String name);
	
	List<FamilyTreeResponseObject> getFamilyTree(String sortOrder);
	
	void modifyFamilyMember(FamilyTreeNode familyMember);
	
	void removeFamilyMember(String name);

}
