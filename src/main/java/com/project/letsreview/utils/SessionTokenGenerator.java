package com.project.letsreview.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public final class SessionTokenGenerator {
	private SecureRandom random = new SecureRandom();

	public String nextSessionToken(){
		return new BigInteger(130, random).toString(32);
	}
}
