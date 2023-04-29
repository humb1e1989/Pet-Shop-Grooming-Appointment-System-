// package com.cpt202.appointment_system;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//     private String targetUrl;

//     public CustomAuthenticationSuccessHandler(String targetUrl) {
//         this.targetUrl = targetUrl;
//     }

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                         Authentication authentication) throws IOException, ServletException {
//         response.sendRedirect(request.getContextPath() + targetUrl);
//     }
// }
