package com.hankal.detrust.service.security;

import com.hankal.detrust.Util.TokenUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenUtilsTest {

    @Test
    public void createJwtToken() {
        String token = TokenUtils.createJwtToken("admin");
        int length = token.length();
        assertEquals(length,197);

        Claims claims  = TokenUtils.parseJWT(token);
        String userName = claims.getId();

        assertEquals(userName,"admin");
    }

    @Test
    public void createJwtToken1() {
    }

    @Test
    public void parseJWT() {
    }
}