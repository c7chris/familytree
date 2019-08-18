/**
 * 
 */
package org.chris.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.chris.constants.FamilyTreeConstants;
import org.chris.exceptions.MaximumParentsReachedException;

/**
 * @author chris
 *
 */
public class FamilyTree {
	
	private FamilyTreeNode root;
	private Map<String, FamilyTreeNode> partnerMap;
	
	/**
	 * @param root
	 */
	public FamilyTree() {
		super();
		this.root = new FamilyTreeNode(FamilyTreeConstants.ROOT_NODE_NAME, -1);
		partnerMap = new HashMap<String, FamilyTreeNode>();
	}
	public void addFamilyMember(FamilyTreeNode familyTreeNode) throws MaximumParentsReachedException {
		addFamilyMember(familyTreeNode, null, null);
	}
	
	public void addFamilyMember(FamilyTreeNode familyTreeNode, String parentName, String partnerName) throws MaximumParentsReachedException {
		if(FamilyTreeConstants.ROOT_NODE_NAME.equals(parentName)) {
			/*
			 * Set parent as ROOT.
			 */
			familyTreeNode.addParent(root);
		}
		else if(null != parentName) {
			FamilyTreeNode parentOfNewMember = findFamilyMember(parentName, root);
			/*
			 * Set the parent that we have found in the family tree.
			 */
			familyTreeNode.addParent(parentOfNewMember);
			if(null != parentOfNewMember.getPartner()) {
				/*
				 * Set the parent's partner as the other parent.
				 */
				familyTreeNode.addParent(partnerMap.get(parentOfNewMember.getPartner()));
			}
		}
		if(null != partnerName) {
			familyTreeNode.setPartner(partnerName);
			partnerMap.put(familyTreeNode.getName(), familyTreeNode);
		}
	}
	
	private FamilyTreeNode findFamilyMember(String name, FamilyTreeNode currentFamilyMember) {
		FamilyTreeNode familyMemberFromSubTree = null;
		/*
		 * Check if present in partner Map
		 */
		if(partnerMap.containsKey(name)) {
			return partnerMap.get(name);
		}
		/*
		 * First check partner for a match
		 */
		if(null!= currentFamilyMember.getPartner() 
				&& currentFamilyMember.getPartner().equals(name)) {
			return partnerMap.get(currentFamilyMember.getPartner());
		}
		/*
		 * Then check kids for a match
		 */
		else if(null != currentFamilyMember.getKids()) {
			/*
			 * Check the immediate kids
			 */
			if(currentFamilyMember.getKids().containsKey(name)) {
				return currentFamilyMember.getKids().get(name);	
			}
			else {
				/*
				 * Check other descendants, recursively.
				 */
				for(Entry<String, FamilyTreeNode> kid: currentFamilyMember.getKids().entrySet()) {
					familyMemberFromSubTree = findFamilyMember(name, kid.getValue());
					if(null != familyMemberFromSubTree) {
						/*
						 * Exit as soon as we've found the family member.
						 */
						break;
					}
				}
			}
		}
		return familyMemberFromSubTree;
	}
	
	public FamilyTreeNode findFamilyMember(String name) {
		return findFamilyMember(name, root);
	}
	
	public List<FamilyTreeNode> sort(String sortOrder) {
		List<FamilyTreeNode> familySortedAgeWise = new ArrayList<>();
		Map<String, FamilyTreeNode> partnerMap = new HashMap<String, FamilyTreeNode>();
		familySortedAgeWise.add(root);
		sort(familySortedAgeWise, partnerMap, root, sortOrder);
		/*
		 * Remove the root node from the sorted list.
		 */
		if(FamilyTreeConstants.SORT_ASCENDING.equals(sortOrder)) {
			familySortedAgeWise.remove(0);
		}
		else if(FamilyTreeConstants.SORT_DESCENDING.equals(sortOrder)) {
			familySortedAgeWise.remove(familySortedAgeWise.size() - 1);
		}
		//System.out.println(" Depth of tree: "+determineDepthOfFamilyTree(root, 0));
		return familySortedAgeWise;
	}
	
	private void sort(List<FamilyTreeNode> sortedList, Map<String, FamilyTreeNode> partnerInsertedMap, FamilyTreeNode currentFamilyMember, String sortOrder) {
		if(partnerInsertedMap.containsKey(currentFamilyMember.getName())) {
			/*
			 * Since the partner and its kids were already added to the sortList, no need to do anything further.
			 */
			return;
		}
		/*
		 * Check partner, if any.
		 */
		if(null!= currentFamilyMember.getPartner()) {
			FamilyTreeNode partner = partnerMap.get(currentFamilyMember.getPartner());
			if(!partnerInsertedMap.containsKey(partner.getName())) {
				insertIntoList(sortedList, partner, sortOrder);
				partnerInsertedMap.put(partner.getName(), partner);
			}
		}
		/*
		 * Then check kids, if any.
		 */
		if(null != currentFamilyMember.getKids()) {
			Map<String, FamilyTreeNode> kids = currentFamilyMember.getKids();
			for(Entry<String, FamilyTreeNode> kid: kids.entrySet()) {
				/*
				 * Add the immediate kids
				 */
				if(!partnerInsertedMap.containsKey(kid.getValue().getName())) {
					insertIntoList(sortedList, kid.getValue(), sortOrder);
				}
			}
			for(Entry<String, FamilyTreeNode> kid: kids.entrySet()) {
				/*
				 * Add the descendants recursively.
				 */
				sort(sortedList, partnerInsertedMap, kid.getValue(), sortOrder);
			}
		}
	}
	
	private void insertIntoList(List<FamilyTreeNode> list, FamilyTreeNode currentFamilyMember, String sortOrder) {
		boolean isInserted = false;
		for(int position = 0; position < list.size() && !isInserted; position++) {
			/*
			 * Add the currentFamilyMember to the correct position in the list.
			 */
			if(FamilyTreeConstants.SORT_ASCENDING.equals(sortOrder) 
					&& list.get(position).getAge() > currentFamilyMember.getAge()) {
				list.add(position, currentFamilyMember);
				isInserted = true;
			}
			else if(FamilyTreeConstants.SORT_DESCENDING.equals(sortOrder) 
					&& list.get(position).getAge() < currentFamilyMember.getAge()) {
				list.add(position, currentFamilyMember);
				isInserted = true;
			}			
		}
		if(!isInserted) {
			list.add(currentFamilyMember);
		}
	}

	private int determineDepthOfFamilyTree(FamilyTreeNode currentNode, int depth) {
		int maxDepthOfKids = 0;
		if(null != currentNode.getKids()) {
			depth++;
			for(Entry<String, FamilyTreeNode> kid: currentNode.getKids().entrySet()) {
				maxDepthOfKids = determineDepthOfFamilyTree(kid.getValue(), depth);
				System.out.println(" currentKidDepth = "+maxDepthOfKids +", depth = "+depth);
			}
		}
		return depth;
	}
	

	public void printChildAndAncestors(FamilyTreeNode currentNode) {
		System.out.println("Child: "+currentNode.getName() + ", age: "+ currentNode.getAge());
		printAncestors(currentNode);
	}
	
	private void printAncestors(FamilyTreeNode currentNode) {
		
		if(null != currentNode.getParents() 
				&& !currentNode.getParents().isEmpty()
				&& !currentNode.getParents().containsKey(FamilyTreeConstants.ROOT_NODE_NAME)) {
			for(Entry<String, FamilyTreeNode> parent: currentNode.getParents().entrySet()) {
				System.out.println("Family member: "+parent.getValue().getName() + ", age: "+ parent.getValue().getAge());
			}
			for(Entry<String, FamilyTreeNode> parent: currentNode.getParents().entrySet()) {
				printAncestors(parent.getValue());
			}
		}
	}
}
