package com.jinanging.thebuild.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinanging.thebuild.common.MD5HashingEncoder;
import com.jinanging.thebuild.user.domain.User;
import com.jinanging.thebuild.user.repository.UserRepository;



@Service
public class UserService {
	
	//final : 해당 변수에 값이 저장된 후 수정 불가(상수)
	private final UserRepository userRepository;
	

	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	public List<User> getUsersByIds(List<Long> userIds){
        return userRepository.selectUsersByIds(userIds);
    }
	
	public User getUser(
			String loginId
			, String password) {
		
		String hashingPassword = MD5HashingEncoder.encode(password);
		
		
		return userRepository.selectUser(loginId, hashingPassword);
		
	}
	
	
	
	//사용자 추가 기능 
	public boolean addUser(
			 String loginId
			, String password
			,  String nickName
			, String email
			, String introduce) {
		
		String hashingPassword = MD5HashingEncoder.encode(password);
		
		
		int count = userRepository.insertUser(loginId, hashingPassword, nickName, email, introduce);
		
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		}
		else {
			return true;
		}
	}

}