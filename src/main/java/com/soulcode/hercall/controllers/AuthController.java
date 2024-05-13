package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.AuthDto;
import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.enumerator.TipoUsuario;
import com.soulcode.hercall.services.UsuarioService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    TokenService tokenService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastra-usuario")
    public String register(@ModelAttribute UsuarioDto dto) {
        ApiResponse<UsuarioDto> usuarioCadastrado = this.usuarioService.save(dto);

        return "redirect:/login";
    }

    @PostMapping("/login-usuario")
    public String login(@ModelAttribute AuthDto dto) {
        ApiResponse<UsuarioDto> usuarioLogado = this.usuarioService.login(dto);

        if (usuarioLogado.getData() == null) {
            return "redirect:/";
        }

        if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            return "redirect:/tela-admin";
        } else if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.TECNICO)) {
            return "redirect:/tela-tecnico";
        } else if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.FUNCIONARIO)) {
            return "redirect:/tela-usuario";
        } else {
            return "redirect:/";
        }
    }

}
