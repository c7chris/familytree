/**
 * 
 */
package com.chris.familytree.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.chris.constants.FamilyTreeConstants;
import org.chris.datastructures.FamilyTreeNode;
import org.chris.exceptions.MaximumParentsReachedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.chris.familytree.respositories.FamilyTreeRespository;
import com.chris.familytree.restobjects.FamilyTreeResponseObject;

/**
 * @author chris
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FamilyTreeServiceImplTest {
	
	@Mock
	FamilyTreeRespository familyTreeRespository;
	
	@InjectMocks
	FamilyTreeServiceImpl familyTreeServiceImpl;
	
	
	FamilyTreeNode greatGrandParent1;
	
	FamilyTreeNode greatGrandParent2;
	
	List<FamilyTreeResponseObject> familyTreeResponseObjects;
	
	@Before
	public void setUp() {
		/*
		 * Initialise some family members for the tests.
		 */
		greatGrandParent1 = new FamilyTreeNode("Great grand parent1", 125);
		greatGrandParent2 = new FamilyTreeNode("Great grand parent2", 122);
		familyTreeResponseObjects = new ArrayList<FamilyTreeResponseObject>();
		familyTreeResponseObjects.add(new FamilyTreeResponseObject(greatGrandParent1.getName(),
				greatGrandParent1.getAge()));
		familyTreeResponseObjects.add(new FamilyTreeResponseObject(greatGrandParent2.getName(),
				greatGrandParent2.getAge()));
		
		/*
		 * Set up the mock repository
		 */
		try {
			Mockito.doNothing().when(familyTreeRespository).createFamilyMember(
					anyString(), 
					anyInt(), 
					anyString(), 
					anyString());
		} catch (MaximumParentsReachedException e) {
			/*
			 * Ignore this for testing
			 */
			e.printStackTrace();
		}
		Mockito.doNothing().when(familyTreeRespository).updateFamilyMember(any(FamilyTreeNode.class));
		Mockito.doNothing().when(familyTreeRespository).deleteFamilyMember(anyString());
		Mockito.when(familyTreeRespository.readFamilyMember(anyString())).thenReturn(greatGrandParent1);
		Mockito.when(familyTreeRespository.getFamilyTree(anyString())).thenReturn(familyTreeResponseObjects);
	}

	@Test
	public void shouldCreateFamilyMember() throws MaximumParentsReachedException {
		familyTreeServiceImpl.addFamilyMember("test", 0, "test", "test");
		Mockito.verify(familyTreeRespository, times(1)).createFamilyMember(
				anyString(), 
				anyInt(), 
				anyString(), 
				anyString());
	}

	@Test
	public void shouldGetFamilyTree() {
		List<FamilyTreeResponseObject> familyTreeResponseObjectsTest = 
				familyTreeServiceImpl.getFamilyTree(FamilyTreeConstants.SORT_ASCENDING);
		assertEquals(familyTreeResponseObjects, familyTreeResponseObjectsTest);
	}
	
	@Test
	public void shouldFetchFamilyMember() {
		FamilyTreeNode familyMember = familyTreeServiceImpl.fetchFamilyMember("test");
		assertEquals(greatGrandParent1, familyMember);
	}
	
	@Test
	public void shouldModifyFamilyMember() {
		familyTreeServiceImpl.modifyFamilyMember(greatGrandParent1);
		Mockito.verify(familyTreeRespository, times(1)).updateFamilyMember(any(FamilyTreeNode.class));		
	}

	@Test
	public void shouldRemoveFamilyMember() {
		familyTreeServiceImpl.removeFamilyMember("test");
		Mockito.verify(familyTreeRespository, times(1)).deleteFamilyMember(anyString());		
	}

}
