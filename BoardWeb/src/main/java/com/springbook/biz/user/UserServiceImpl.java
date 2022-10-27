package com.springbook.biz.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.springbook.biz.user.impl.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("userDAO")
	UserService userDao;

	public void setUserDao(UserService userDao) {
		this.userDao = userDao;
	}

	@Override
	public void insertUser(UserVO vo) {
		userDao.insertUser(vo);
		
	}

	@Override
	public void updateUser(UserVO vo) {
		userDao.updateUser(vo);
		
	}

	@Override
	public void deleteUser(UserVO vo) {
		userDao.deleteUser(vo);
		
	}

	@Override
	public UserVO getUser(UserVO vo) {
		return userDao.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		// TODO Auto-generated method stub
		return userDao.getUserList(vo);
	}

}
