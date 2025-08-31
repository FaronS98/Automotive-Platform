package com.reply.Automotive_Platform.config.properties;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "app.addresses")
public class AddressesProperties {

  @Pattern(regexp = "^\\d{1,3}(\\.\\d{1,3}){3}$", message = "Invalid IP format")
  private String ip;

  @Pattern(regexp = "^\\d{1,3}(\\.\\d{1,3}){3}$", message = "Invalid IP format")
  private String ip2;
}
