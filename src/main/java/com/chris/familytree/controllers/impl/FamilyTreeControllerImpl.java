package com.chris.familytree.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import org.chris.exceptions.MaximumParentsReachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.familytree.controllers.FamilyTreeController;
import com.chris.familytree.respositories.FamilyTreeRespository;
import com.chris.familytree.restobjects.FamilyTreeAddMemberRequest;
import com.chris.familytree.restobjects.FamilyTreeResponseObject;
import com.chris.familytree.service.FamilyTreeService;

@RestController
public class FamilyTreeControllerImpl implements FamilyTreeController {
	
	private FamilyTreeService service;
		
	@Autowired
	public FamilyTreeControllerImpl(FamilyTreeService service) {
		super();
		this.service = service;
	}

	@Override
	public ResponseEntity<String> addFamilyMember(@RequestBody FamilyTreeAddMemberRequest request) {
		try {
			service.addFamilyMember(request.getName(), 
					request.getAge(), 
					request.getParent(), 
					request.getPartner());
			return ResponseEntity.ok("Family member added");
		} catch (MaximumParentsReachedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Unable to add family member as they already have two parents.");
		}
		 catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Unable to add family member");
			}
	}

	@Override
	public ResponseEntity<List<FamilyTreeResponseObject>> getFamilyTree(@RequestParam String sortOrder) {
		try {
			return ResponseEntity.ok(service.getFamilyTree(sortOrder));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ArrayList<FamilyTreeResponseObject>());
		}
	}

}
