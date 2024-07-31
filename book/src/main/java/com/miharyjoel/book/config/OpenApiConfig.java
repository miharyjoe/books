package com.miharyjoel.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(
      name = "Mihary Joel",
      email = "miharyjoe@gmail.com",
      url = "https://miharyjoelportfolio.vercel.app/"
    ),
    description = "OpenApi documentation for spring security",
    title = "openApi Specification - Mihary",
    version = "1.0",
    license = @License(
      name = "Licence name",
      url = "https://some-url.com"
    ),
    termsOfService = "Term of service"
  ),
  servers = {
    @Server(
      description = "local ENV",
      url = "http://localhost:8088/api/v1"
    ),
    @Server(
      description = "PROD ENV",
      url = "https://realurl.com"
    )
  },
  security = {
    @SecurityRequirement(
      name = "bearerAuth"
    )
  }
)
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT auth description",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
