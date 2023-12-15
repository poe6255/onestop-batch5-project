package com.example.securitywithdynamicrole;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTPGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;

@SpringBootApplication
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityWithDynamicRoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityWithDynamicRoleApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public TOTPGenerator totpGenerator(){
		byte[] secret = SecretGenerator.generate();

		return new TOTPGenerator.Builder(secret)
				.withHOTPGenerator(builder -> {
					builder.withPasswordLength(6);
					builder.withAlgorithm(HMACAlgorithm.SHA256); // SHA256 and SHA512 are also supported
				})
				.withPeriod(Duration.ofSeconds(60))
				.build();
	}
}
