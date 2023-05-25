package fa.youareright.service;

import org.springframework.stereotype.Service;

import fa.youareright.dto.UpdateInfoDTO;
import fa.youareright.model.User;

@Service
public interface UserService {
	
	String getLastUserId();
	
	String getNextId(String inputId);
	
	void save(User user);
	
	User findByAccountId(Integer accountId);
	
	void updateInfo(UpdateInfoDTO info);

}
