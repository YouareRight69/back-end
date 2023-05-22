package fa.youareright.service;

import org.springframework.stereotype.Service;

import fa.youareright.model.User;

@Service
public interface UserService {
	
	String getLastUserId();
	
	String getNextId(String inputId);
	
	void save(User user);

}
