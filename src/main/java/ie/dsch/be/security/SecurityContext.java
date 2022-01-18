package ie.dsch.be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class to be assumed always containing the current logged user in the
 * request scope, that means when this class is INJECTED the currentUser()
 * method ALWAYS return the current logged user.
 */
@Component
public class SecurityContext {

	@Autowired
	private LoggedUser loggedUser;

	public LoggedUser currentUser() {
		return loggedUser;
	}

	
	public void setCurrentUser(Long id)
	{
		loggedUser.setId(id);
	}
	
}
