package com.qzero.blog.service;

import com.qzero.blog.data.Token;
import com.qzero.blog.data.User;
import com.qzero.blog.data.repository.TokenRepository;
import com.qzero.blog.data.repository.UserRepository;
import com.qzero.blog.exception.ErrorCodeList;
import com.qzero.blog.exception.ResponsiveException;
import com.qzero.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Login using username and password given by parameter
     * @param loginInfoUser provide username and password
     * @return The token if succeeded
     * @throws ResponsiveException If login failed
     */
    public Token login(User loginInfoUser,long expiredTime) throws ResponsiveException {
        if(!userRepository.existsByUsernameAndPasswordHash(loginInfoUser.getUsername(),loginInfoUser.getPasswordHash())){
            //Wrong username and password
            throw new ResponsiveException(ErrorCodeList.WRONG_LOGIN_INFO,"Wrong username and password");
        }

        User user=userRepository.getByUsernameAndPasswordHash(loginInfoUser.getUsername(),loginInfoUser.getPasswordHash());
        if(!user.isActivated()){
            //Account is not activated
            throw new ResponsiveException(ErrorCodeList.ACCOUNT_NOT_ACTIVATED,"Account is not activated");
        }

        //Create token
        String tokenId= UUIDUtils.getRandomUUID();
        Token token=new Token(tokenId,user.getUsername(),expiredTime);
        tokenRepository.save(token);

        return token;
    }

    /**
     * Logout using tokenId given by parameter
     * @param token provide tokenId
     */
    public void logout(Token token) throws ResponsiveException {
        if(!tokenRepository.existsById(token.getTokenId()))
            throw new ResponsiveException(ErrorCodeList.INVALID_TOKEN,"Invalid token");

        tokenRepository.deleteById(token.getTokenId());
    }

    /**
     * Check if the token is a valid one
     * @param tokenId The token's id
     * @throws ResponsiveException If it's not, exception thrown
     */
    public void checkTokenValidation(String tokenId) throws ResponsiveException {
        if (!tokenRepository.existsById(tokenId)) {
            throw new ResponsiveException(ErrorCodeList.INVALID_TOKEN, "Token does not exist");
        }

        Token token = tokenRepository.getById(tokenId);

        //Check if expired
        if (token.getExpiredTime() > 0 && token.getExpiredTime() < System.currentTimeMillis()) {
            //Delete expired token
            tokenRepository.delete(token);
            throw new ResponsiveException(ErrorCodeList.INVALID_TOKEN, "Token is expired");
        }

        //Check pass
        return;
    }

    /**
     * Check token's validation and get it
     * @param tokenId
     * @return
     * @throws ResponsiveException if token is not a valid one
     */
    public Token checkAndGetToken(String tokenId) throws ResponsiveException{
        checkTokenValidation(tokenId);

        Token token=tokenRepository.getById(tokenId);
        return token;
    }

}
