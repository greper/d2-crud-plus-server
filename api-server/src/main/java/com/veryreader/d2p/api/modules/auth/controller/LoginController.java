package com.veryreader.d2p.api.modules.auth.controller;


import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/auth/")
@AllArgsConstructor
public class LoginController {

    /**
     * @return
     */
    @PostMapping(value = "/login")
    public Ret login(@RequestParam String username, @RequestParam String password) {
        Authentication request = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication result = authenticationManager.authenticate(request);
//        SecurityContextHolder.getContext().setAuthentication(result);
        return Ret.success("success",null);
    }

}
