// package com.cpt202.appointment_system.Models;

// // 导入和包名根据您的项目结构进行调整
// // ...

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     @Autowired
//     private UserDetailsServiceImpl userDetailsService;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeRequests()
//                 .antMatchers("/register").permitAll()
//                 .anyRequest().authenticated()
//             .and()
//             .formLogin()
//                 .loginPage("/")
//                 .loginProcessingUrl("/log")
//                 .defaultSuccessURL("/home", true)
//                 .permitAll()
//             .and()
//             .logout()
//                 .permitAll();
//     }
// }
