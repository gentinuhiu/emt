package emt.lab.web.rest;

import emt.lab.dto.create.CreateUserDto;
import emt.lab.dto.create.LoginUserDto;
import emt.lab.dto.display.DisplayUserDto;
import emt.lab.model.domain.User;
import emt.lab.model.exception.InvalidArgumentsException;
import emt.lab.model.exception.InvalidUserCredentialsException;
import emt.lab.model.exception.PasswordsDoNotMatchException;
import emt.lab.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserRestController {

    private final UserApplicationService userApplicationService;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public UserRestController(UserApplicationService userApplicationService, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        this.userApplicationService = userApplicationService;
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        try {
//            DisplayUserDto displayUserDto = userApplicationService.login(
//                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
            DisplayUserDto displayUserDto = userApplicationService.login(loginUserDto)
                    .orElseThrow(InvalidUserCredentialsException::new);
//            request.getSession().setAttribute("user", displayUserDto.toUser());

            User authenticatedUser = displayUserDto.toUser();
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            return ResponseEntity.ok(displayUserDto);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
