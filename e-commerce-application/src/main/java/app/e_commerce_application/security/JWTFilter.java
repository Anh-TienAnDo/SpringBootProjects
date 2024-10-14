package app.e_commerce_application.security;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.auth0.jwt.exceptions.JWTVerificationException;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;


public class JWTFilter {

	private JWTUtil jwtUtil = new JWTUtil();

    public boolean doFilterUser(String token) {
        String[] role = {"ROLE_USER", "ROLE_STAFF", "ROLE_ADMIN"};  
        if (token != null && !token.isBlank()) {
            for (String r : role) {
                if(r.equals(jwtUtil.validateTokenAndRetrieveSubject(token))){
                    return true;
                }
            }
        }
        return false; 
    }

    public boolean doFilterStaff(String token) {
        String[] role = {"ROLE_STAFF", "ROLE_ADMIN"};    
        if (token != null && !token.isBlank()) {
            for (String r : role) {
                if(r.equals(jwtUtil.validateTokenAndRetrieveSubject(token))){
                    return true;
                }
            }
        }
        return false; 
    }

    public boolean doFilterAdmin(String token) {
        String role = "ROLE_ADMIN";  
        if (token != null && !token.isBlank()) {
            if(role.equals(jwtUtil.validateTokenAndRetrieveSubject(token))){
                return true;
            }
        }
        return false; 
    }


    // @Override
	// protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	// 		throws ServletException, IOException {

	// 	String authHeader = request.getHeader("Authorization");
    //     System.out.println("authHeader: " + authHeader);

	// 	// if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
	// 	// 	String jwt = authHeader.substring(7);
    //     if (authHeader != null && !authHeader.isBlank()) {
    //         String jwt = authHeader;
	// 		if (jwt == null || jwt.isBlank()) {
	// 			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invlaid JWT token in Bearer Header");
	// 		} else {
	// 			try {
	// 				String username = jwtUtil.validateTokenAndRetrieveSubject(jwt);
	// 			} catch (JWTVerificationException e) {
	// 				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
	// 			}
	// 		}
	// 	}
		
	// 	filterChain.doFilter(request, response);
	// }
    
}
