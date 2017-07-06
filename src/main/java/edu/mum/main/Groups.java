package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Authority;
import edu.mum.domain.Group;
import edu.mum.domain.UserCredentials;
import edu.mum.service.GroupService;
import edu.mum.service.UserCredentialsService;

@Component
public class Groups {

	@Autowired
	GroupService groupService;

	@Autowired
	UserCredentialsService userCredentialsService;

	public void addGroups() {

		// Create ADMIN Group
		Authority authority = new Authority();
		authority.setAuthority("ROLE_ADMIN");

		Group groupAdmin = new Group();
		groupAdmin.setGroup_name("USER");
		groupAdmin.getAuthority().add(authority);

		// Create SUPERVISOR Group
		authority = new Authority();
		authority.setAuthority("ROLE_SUPERVISOR");

		Group groupSuper = new Group();
		groupSuper.setGroup_name("SUPERVISOR");
		groupSuper.getAuthority().add(authority);

		// Add LIST to both groups
//		Authority authority = new Authority();
		authority = new Authority();
		authority.setAuthority("LIST");
		groupAdmin.getAuthority().add(authority);
		groupSuper.getAuthority().add(authority);

		// Add READ to both Groups
		authority = new Authority();
		authority.setAuthority("READ");
		groupAdmin.getAuthority().add(authority);
		groupSuper.getAuthority().add(authority);

		// Add Update to Super only
		authority = new Authority();
		authority.setAuthority("UPDATE");
		groupSuper.getAuthority().add(authority);

		// Add users to groups
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUserName("Sean");
		userCredentials.setPassword("Sean");
		userCredentials.setAuthority(groupAdmin.getAuthority());
		userCredentials.setEnabled(true);

		groupAdmin.getUserCredentials().add(userCredentials);

		userCredentials = new UserCredentials();
		userCredentials.setUserName("Paul");
		userCredentials.setPassword("Paul");
		userCredentials.setAuthority(groupSuper.getAuthority());
		userCredentials.setEnabled(true);

		groupSuper.getUserCredentials().add(userCredentials);

		// Save groups
		groupService.save(groupAdmin);
		groupService.update(groupSuper);

	}
}
