/**
 * 
 */
package com.chris.familytree.respositories;

import java.util.ArrayList;
import java.util.List;

import org.chris.datastructures.FamilyTree;
import org.chris.datastructures.FamilyTreeNode;
import org.chris.exceptions.MaximumParentsReachedException;
import org.springframework.stereotype.Repository;

import com.chris.familytree.restobjects.FamilyTreeResponseObject;

/**
 * @author chris
 *
 */
@Repository
public class FamilyTreeRespository {
	
	static FamilyTree familyTree = new FamilyTree();
	
	public void addFamilyMember(String name, int age, String parent, String partner) throws MaximumParentsReachedException {
		FamilyTreeNode familyMember = new FamilyTreeNode(name, age);
		familyTree.addFamilyMember(familyMember, parent, partner);
	}

	public List<FamilyTreeResponseObject> getFamilyTree(String sortOrder) {
		List<FamilyTreeResponseObject> familyTreeResponseObjects = new ArrayList<>();
		List<FamilyTreeNode> familyTreeNodes = familyTree.sort(sortOrder);
		for(FamilyTreeNode familyTreeNode: familyTreeNodes) {
			familyTreeResponseObjects.add(new FamilyTreeResponseObject(familyTreeNode.getName(),
					familyTreeNode.getAge()));
		}
		return familyTreeResponseObjects;
	}
}
