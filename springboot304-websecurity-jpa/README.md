# spring-boot-security-3.0
1. Add user (by using postman)
http://localhost:8080/products/all
{
  "id": 103,
  "name": "Ajoy",
  "email": "ajoy@gmail.com",
  "password": "pwd1",
  "roles": "ROLE_USER, ROLE_ADMIN"
}

2. Go to browser 
a) http://localhost:8080/products/all
b) login by user name and password (admin user will see all products)
c) http://localhost:8080/products/1 (normal user will get one product)


#**********************************************************************************
#Note (1): If you are using spring boot 3.1.x version then please do the below code change

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/product-service/welcome", "/product-service/addNewUser").permitAll()
                                .requestMatchers("/product-service/**")
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults()).build();
    }


#Note (2): Old version WebSecurityConfigurerAdapter is depricated in Springboot 2.7
Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//User authentication
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("javainuse")
				.password("javainuse").roles("USER");
	}
	
       //Authorization
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
				.and().formLogin().loginPage("/login.jsp")
				.failureUrl("/login.jsp?error=1").loginProcessingUrl("/login")
				.permitAll().and().logout()
				.logoutSuccessUrl("/listEmployees.html");

	}

}
****************************************************************************************