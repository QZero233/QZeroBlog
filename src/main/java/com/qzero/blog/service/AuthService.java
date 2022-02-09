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

}
