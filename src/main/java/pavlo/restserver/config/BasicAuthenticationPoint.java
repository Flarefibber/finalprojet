package pavlo.restserver.config;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BasicAuthenticationPoint extends BasicAuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authEx) throws IOException, ServletException{

        response.addHeader("WWW-Authenticate", "Basic realm= " +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter writer = response.getWriter();
        writer.println(authEx.getMessage());

    }

    public void afterPropertiesSet() throws Exception{
        setRealmName("Pavlo");
        super.afterPropertiesSet();
    }
}
