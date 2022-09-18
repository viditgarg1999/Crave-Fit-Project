package com.leggo.fit.services;

import com.leggo.fit.common.configuration.JwtConfig.*;
import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.login.RefreshTokenRequestModel;
import com.leggo.fit.dto.login.UserLoginRequestModel;
import com.leggo.fit.dto.login.UserLoginResponseModel;
import com.leggo.fit.infrastructure.dao.UserAccountDetailsDao;
import com.leggo.fit.infrastructure.dao.UserRequestedOtpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserLoginCommandHandler {
    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RefreshTokenUtil refreshTokenUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OtpVerificationCommandHandler otpVerification;

    @Autowired
    private UserRequestedOtpDao userRequestedOtpDao;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter;


    public ResponseEntity<?> handle(UserLoginRequestModel userLoginRequestModel) {
        try {
            var user = userAccountDetailsDao.getByMobileNumber(userLoginRequestModel.getMobileNumber());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile number does not exist");
            }

            if (!bCryptPasswordEncoder.matches(userLoginRequestModel.getPassword(), user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password.");
            }
            UserDetails userDetails = new User(user.getMobileNumber(), user.getPassword(), new ArrayList<>());
            String jwtToken = jwtUtil.generateToken(userDetails);
            String refreshToken = refreshTokenUtil.generateToken(userDetails);
            if (jwtToken.length() == 0 || refreshToken.length() == 0) {
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Server issue please try again");
            }
            return ResponseEntity.ok(new UserLoginResponseModel(jwtToken, refreshToken));
        } catch (ServerErrorException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Server Error, please try again", ex);
        }
    }

    public ResponseEntity<?> handleLoginViaOtp(MobileNumberRequestModel requestedMobileNumber) {

        try {
            if (userAccountDetailsDao.existsByMobileNumber(requestedMobileNumber.getMobileNumber()) == false) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Mobile number does not exist! Please login first");
            } else {
                return otpVerification.generateOTP(requestedMobileNumber.getMobileNumber());
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> validateOtpLogin(String mobileNumber) {

        try {
            var user = userAccountDetailsDao.getByMobileNumber(mobileNumber);

            UserDetails userDetails = new User(user.getMobileNumber(), user.getPassword(), new ArrayList<>());
            String jwtToken = jwtUtil.generateToken(userDetails);
            String refreshToken = refreshTokenUtil.generateToken(userDetails);
            if (jwtToken.length() == 0 || refreshToken.length() == 0) {
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Server issue please try again");
            }
            return ResponseEntity.ok(new UserLoginResponseModel(jwtToken, refreshToken));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> validateRefreshToken(RefreshTokenRequestModel refreshTokenRequestModel)
    {
        try
        {

           var expirationDate=refreshTokenUtil.getExpirationDateFromToken(refreshTokenRequestModel.getRefreshToken());
           if(expirationDate.before(new Date()))
           {
               return ResponseEntity
                       .status(HttpStatus.FORBIDDEN)
                       .body("Refresh token expired, please login again!");
           }

           String username=refreshTokenUtil.getUsernameFromToken(refreshTokenRequestModel.getRefreshToken());

            if(username==null)
            {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Please try again");
            }

            String password = userAccountDetailsDao.getByMobileNumber(username).getPassword();

            UserDetails userDetails=new User(username,password,new ArrayList<>());
            String jwtToken = jwtUtil.generateToken(userDetails);
            if (jwtToken.length() == 0) {
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Server issue please try again");
            }
            return ResponseEntity.ok(new UserLoginResponseModel(jwtToken, refreshTokenRequestModel.getRefreshToken()));

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }
}
