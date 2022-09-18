package com.fitproject.cravefit.common.JwtConfig;

import com.fitproject.cravefit.infrastructure.dao.UserAccountDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get jwt
        //Bearer
        //validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username=null;
        String jwtToken=null;

        //null and format
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken=requestTokenHeader.substring(7);

            try{

                username = this.jwtUtil.getUsernameFromToken(jwtToken);


            }catch (Exception e)
            {
                e.printStackTrace();
            }

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                String password=userAccountDetailsDao.getByMobileNumber(username).getPassword();

                UserDetails userDetails=new User(username,password,new ArrayList<>());
                //UserDetails userDetails=new User(username,);
                //UserDetails userDetails = this.userAccountDetailsDao.findBy(U);
                //security


                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            }else
            {
                System.out.println("Token is not validated..");
            }

        }

        filterChain.doFilter(request,response);

    }
}
