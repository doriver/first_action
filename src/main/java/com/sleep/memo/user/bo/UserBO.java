package com.sleep.memo.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sleep.memo.common.EncryptUtils;
import com.sleep.memo.user.dao.UserDAO;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);	
		
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUP] 암호화 실패!!!!!!!!!!!!!!");
			return 0;
		}
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
}
