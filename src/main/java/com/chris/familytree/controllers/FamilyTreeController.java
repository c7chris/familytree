package com.chris.familytree.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chris.familytree.restobjects.FamilyTreeAddMemberRequest;
import com.chris.familytree.restobjects.FamilyTreeResponseObject;

@RequestMapping(value = "/family-tree", produces = MediaType.APPLICATION_JSON_VALUE)
public interface FamilyTreeController {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<String> addFamilyMember(FamilyTreeAddMemberRequest request);

	@GetMapping()
	@ResponseBody
	ResponseEntity<List<FamilyTreeResponseObject>> getFamilyTree(String sortOrder);
}
