/**
 * 
 */
package com.ericsson.ipm.v1.config;

import org.springframework.stereotype.Component;

//import com.sivalabs.springapp.entities.User;
//import com.sivalabs.springapp.services.UserService;
//import com.sivalabs.springapp.web.config.SecurityUser;

/**
 * @author iqbal.hosain.khan@ericsson.com
 *
 */
//@Component
public class CustomUserDetailsService { /*implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userService.findUserByEmail(userName);
		if(user == null){
			throw new UsernameNotFoundException("UserName "+userName+" not found");
		}
		return new SecurityUser(user);
	}*/

}
