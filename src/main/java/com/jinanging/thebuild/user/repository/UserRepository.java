package com.jinanging.thebuild.user.repository;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinanging.thebuild.user.domain.User;

@Mapper
public interface UserRepository {
	
	  List<User> selectUsersByIds(@Param("userIds") List<Long> userIds);
	
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	
	
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("nickName") String nickName
			, @Param("email") String email
			, @Param("introduce") String introduce);
	
	
	public int selectCountByLoginId(@Param("loginId") String loginId);
	

}
