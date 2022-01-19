package ie.dsch.be.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepo;
    
    @Autowired
    private SecurityContext securityContext;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	     Optional<Users> user = usersRepo.findByUser(s);
    		if(user.isPresent())
        	{
    			securityContext.setCurrentUser(user.get().getValue());
        		  return new User(s, s,
        	                new ArrayList<>());
        	}
    		else {
        		throw new UsernameNotFoundException("User Not Found");
        	}
    	
    }
}
