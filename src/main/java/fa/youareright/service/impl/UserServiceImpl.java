package fa.youareright.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.youareright.dto.UpdateInfoDTO;
import fa.youareright.model.User;
import fa.youareright.repository.UserRepository;
import fa.youareright.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String getLastUserId() {
		return userRepository.getLastUserId();
	}

	@Override
	public String getNextId(String inputId) {
		String prefix = inputId.substring(0, 3); // Extract the prefix "ACC"
		int number = Integer.parseInt(inputId.substring(3)); // Extract the number part as an integer
		int nextNumber = number + 1; // Increment the number by 1

		// Format the nextNumber to have leading zeros
		String formattedNumber = String.format("%03d", nextNumber);

		return prefix + formattedNumber; // Concatenate the prefix and formatted number
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByAccountId(Integer accountId) {
		return userRepository.findByAccountId(accountId).get();
	}

	@Override
	public void updateInfo(UpdateInfoDTO info) {
		userRepository.updateUser(info.getAddress(), info.getAvatar(), info.getDob(), info.getFullName(),
				info.getPhoneNumber(), info.getUserId());
	}

}
