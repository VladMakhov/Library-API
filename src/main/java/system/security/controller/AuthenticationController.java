package system.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.security.model.reqresp.AuthenticationRequest;
import system.security.model.reqresp.AuthenticationResponse;
import system.security.model.reqresp.RegisterRequest;
import system.security.service.AuthenticationService;


@RestController
@RequestMapping("/library/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }
}
