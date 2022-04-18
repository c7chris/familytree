package com.chris.familytree;

import java.util.List;

import org.chris.constants.FamilyTreeConstants;
import org.chris.datastructures.FamilyTree;
import org.chris.datastructures.FamilyTreeNode;
import org.chris.exceptions.MaximumParentsReachedException;

public class MyFamilyTree {
	
	static FamilyTree familyTree = new FamilyTree();
	static FamilyTreeNode grandKid5;

	public static void main(String a[]) throws MaximumParentsReachedException {
		testAddingMembers();
		testSortAscending();
		testSortDescending();
		testPrintChildAndAncestors();		
	}
	
	private static void testAddingMembers() throws MaximumParentsReachedException {
		/*
		 * Create great-grand parents
		 */
		FamilyTreeNode greatGrandParent1 = new FamilyTreeNode("Great grand parent1", 125);
		FamilyTreeNode greatGrandParent2 = new FamilyTreeNode("Great grand parent2", 122);
		FamilyTreeNode greatGrandParent3 = new FamilyTreeNode("Great grand parent3", 124);
		FamilyTreeNode greatGrandParent4 = new FamilyTreeNode("Great grand parent4", 130);
		familyTree.addFamilyMember(greatGrandParent1, FamilyTreeConstants.ROOT_NODE_NAME, "Great grand parent2");
		familyTree.addFamilyMember(greatGrandParent2, null, "Great grand parent1");
		familyTree.addFamilyMember(greatGrandParent3, FamilyTreeConstants.ROOT_NODE_NAME, "Great grand parent4");
		familyTree.addFamilyMember(greatGrandParent4, null, "Great grand parent3");
		/*
		 * Create grand parents
		 */
		FamilyTreeNode grandParent1 = new FamilyTreeNode("Grand parent1", 98);
		FamilyTreeNode grandParent2 = new FamilyTreeNode("Grand parent2", 101);
		familyTree.addFamilyMember(grandParent1, "Great grand parent1", "Grand parent2");
		familyTree.addFamilyMember(grandParent2, "Great grand parent3", "Grand parent1");
		
		/*
		 * Create parents
		 */			
		FamilyTreeNode parent1 = new FamilyTreeNode("parent1", 77);
		FamilyTreeNode parent2 = new FamilyTreeNode("parent2", 71);
		familyTree.addFamilyMember(parent1, null, "parent2");
		familyTree.addFamilyMember(parent2, "Grand parent1", "parent1");
		
		
		FamilyTreeNode parent3 = new FamilyTreeNode("parent3", 67);
		FamilyTreeNode parent4 = new FamilyTreeNode("parent4", 65);
		familyTree.addFamilyMember(parent3, "Grand parent1", "parent4");
		familyTree.addFamilyMember(parent4, null, "parent3");

		FamilyTreeNode parent5 = new FamilyTreeNode("parent5", 56);
		FamilyTreeNode parent6 = new FamilyTreeNode("parent6", 50);
		familyTree.addFamilyMember(parent5, "Grand parent1", "parent6");
		familyTree.addFamilyMember(parent6, null, "parent5");

		
		/*
		 * Create kids
		 */
		FamilyTreeNode kid1 = new FamilyTreeNode("kid1", 41);
		FamilyTreeNode kid2 = new FamilyTreeNode("kid2", 45);
		FamilyTreeNode kid3 = new FamilyTreeNode("kid3", 32);
		FamilyTreeNode kid4 = new FamilyTreeNode("kid4", 29);
		FamilyTreeNode kid5 = new FamilyTreeNode("kid5", 38);
		FamilyTreeNode kid6 = new FamilyTreeNode("kid6", 40);
		familyTree.addFamilyMember(kid1, null, "kid2");
		familyTree.addFamilyMember(kid2, "parent1", "kid1");
		familyTree.addFamilyMember(kid3, null, "kid4");
		familyTree.addFamilyMember(kid4, "parent3", "kid3");
		familyTree.addFamilyMember(kid5, "parent3","kid6");
		familyTree.addFamilyMember(kid6, null, "kid5");

		/*
		 * Create grand kids
		 */
		FamilyTreeNode grandKid1 = new FamilyTreeNode("grand kid1", 14);
		FamilyTreeNode grandKid2 = new FamilyTreeNode("grand kid2", 5);
		FamilyTreeNode grandKid3 = new FamilyTreeNode("grand kid3", 10);
		FamilyTreeNode grandKid4 = new FamilyTreeNode("grand kid4", 6);
		grandKid5 = new FamilyTreeNode("grand kid5", 2);
		familyTree.addFamilyMember(grandKid1, "kid1", null);
		familyTree.addFamilyMember(grandKid2, "kid1", null);
		familyTree.addFamilyMember(grandKid3, "kid3", null);
		familyTree.addFamilyMember(grandKid4, "kid3", null);
		familyTree.addFamilyMember(grandKid5, "kid3", null);
	}
	
	private static void testSortAscending() {
		System.out.println("\n***********************************************");
		System.out.println("********** START: Sort Ascending***************");
		List<FamilyTreeNode> sortedMembers = familyTree.sort(FamilyTreeConstants.SORT_ASCENDING);
		for (FamilyTreeNode familyTreeNode : sortedMembers) {
			System.out.println(familyTreeNode.getName() + " : " + familyTreeNode.getAge());
		}
		System.out.println("********** END: Sort Ascending***************");
		System.out.println("*********************************************\n");
	}
	

	private static void testSortDescending() {
		System.out.println("\n************************************************");
		System.out.println("********** START: Sort Descending***************");
		List<FamilyTreeNode> sortedMembers = familyTree.sort(FamilyTreeConstants.SORT_DESCENDING);
		for (FamilyTreeNode familyTreeNode : sortedMembers) {
			System.out.println(familyTreeNode.getName() + " : " + familyTreeNode.getAge());
		}
		System.out.println("********** END: Sort Descending***************");
		System.out.println("**********************************************\n");
	}

	private static void testPrintChildAndAncestors() {
		System.out.println("\n**************************************************");
		System.out.println("********** START: Print child and ancestors*******");
		familyTree.printChildAndAncestors(grandKid5);
		System.out.println("********** END: Print child and ancestors**********");
		System.out.println("***************************************************\n");
	}
	
}
