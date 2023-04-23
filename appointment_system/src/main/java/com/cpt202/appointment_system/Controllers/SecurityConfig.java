// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userService)
//             .passwordEncoder(new BCryptPasswordEncoder());
//     } // using the BCryptPasswordEncoder to encode the pswd

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeRequests()
//                 .antMatchers("/register").permitAll() // allow all the user to access to the register and login page
//                 .antMatchers("/login").permitAll()
//                 .anyRequest().authenticated()
//             .and()
//             .formLogin()
//                 .loginPage("/login")
//                 .permitAll()
//                 .defaultSuccessURL("/home", true)
//             .and()
//             .logout()
//                 .permitAll();
//     }
// }
