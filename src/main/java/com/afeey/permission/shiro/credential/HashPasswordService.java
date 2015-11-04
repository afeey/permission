package com.afeey.permission.shiro.credential;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Hash密码加密服务
 * 
 * @author 王跃飞
 *
 */
public class HashPasswordService {

	private HashedCredentialsMatcher credentialsMatcher;

	/**
	 * 返回加密密码
	 * 
	 * @param password 密码
	 * @return 返回加密密码
	 */
	public String hashPassword(String password) {

		SimpleHash hash = new SimpleHash(
				credentialsMatcher.getHashAlgorithmName(), password, password,
				credentialsMatcher.getHashIterations());

		return credentialsMatcher.isStoredCredentialsHexEncoded() ? hash
				.toHex() : hash.toBase64();
	}

	public HashedCredentialsMatcher getCredentialsMatcher() {
		return credentialsMatcher;
	}

	public void setCredentialsMatcher(
			HashedCredentialsMatcher credentialsMatcher) {
		this.credentialsMatcher = credentialsMatcher;
	}
}
