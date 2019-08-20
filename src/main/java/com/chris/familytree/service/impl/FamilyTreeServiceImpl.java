/**
 * 
 */
package com.chris.familytree.service.impl;

import java.util.List;

import org.chris.datastructures.FamilyTreeNode;
import org.chris.exceptions.MaximumParentsReachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.familytree.respositories.FamilyTreeRespository;
import com.chris.familytree.restobjects.FamilyTreeResponseObject;
import com.chris.familytree.service.FamilyTreeService;

/**
 * @author chris
 *
 */

@Service
public class FamilyTreeServiceImpl implements FamilyTreeService {
	
	private FamilyTreeRespository familyTreeRespository;

	/**
	 * @param familyTreeRespository
	 */
	@Autowired 
	public FamilyTreeServiceImpl(FamilyTreeRespository familyTreeRespository) {
		super();
		this.familyTreeRespository = familyTreeRespository;
	}

	@Override
	public void addFamilyMember(String name, int age, String parent, String partner) throws MaximumParentsReachedException {
		familyTreeRespository.createFamilyMember(name, 
				age, 
				parent, 
				partner);
	}

	@Override
	public FamilyTreeNode fetchFamilyMember(String name) {
		return familyTreeRespository.readFamilyMember(name);
	}

	@Override
	public List<FamilyTreeResponseObject> getFamilyTree(String sortOrder) {
		return familyTreeRespository.getFamilyTree(sortOrder);
	}

	@Override
	public void modifyFamilyMember(FamilyTreeNode familyMember) {
		familyTreeRespository.updateFamilyMember(familyMember);

	}

	@Override
	public void removeFamilyMember(String name) {
		familyTreeRespository.deleteFamilyMember(name);
	}
}
