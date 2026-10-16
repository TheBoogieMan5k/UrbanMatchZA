package urbanmatch.config;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import urbanmatch.repository.UserRepository;
@Configuration
public class SecurityConfig {
 @Bean public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean public UserDetailsService userDetailsService(UserRepository users){return email -> {
  var u=users.findByEmail(email.toLowerCase()).orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));
  return org.springframework.security.core.userdetails.User.withUsername(u.email).password(u.passwordHash).roles(u.role.toUpperCase()).disabled(!"active".equals(u.status)).build();
 };}
 @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http.csrf(c->c.disable()) // Stateless HTTP Basic API only; no cookie authentication
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a
    .requestMatchers("/","/index.html","/favicon.ico","/css/**","/js/**","/images/**").permitAll()
    .requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
    .requestMatchers(HttpMethod.GET,"/api/jobs/open","/api/jobs/{id}","/api/directory","/api/reviews","/api/reviews/{userId}").permitAll()
    .requestMatchers("/api/admin/**","/api/users/count").hasRole("ADMIN")
    .anyRequest().authenticated())
   .httpBasic(org.springframework.security.config.Customizer.withDefaults())
   .formLogin(f->f.disable());
  return http.build();
 }
}
