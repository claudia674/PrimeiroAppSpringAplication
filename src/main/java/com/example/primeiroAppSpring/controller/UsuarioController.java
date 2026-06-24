package com.example.primeiroAppSpring.controller;

import com.example.primeiroAppSpring.model.UsuarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {
    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina", "Cadastro");
        model.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque da Cozinha");

        return "cadastro";

    }

    @PostMapping("cadastro")
    public String processarCadastro(@ModelAttribute UsuarioForm form, Model model) {
        if (!form.getSenha().equals(form.getConfirmarsenha())) {
            model.addAttribute("erro", "Erro,as senhas não conferem");
            return "cadastro";
        }
        IO.println("Usúario criado com secesso!");
        IO.println(form.getNome() + "" + form.getEmail());

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String exibirLogin(Model model) {
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina", "login");
        model.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque da Cozinha");

        return "login";

    }

    @PostMapping("login")
    public String processarLogin(@ModelAttribute UsuarioForm form, Model model) {
        if (form.getEmail().endsWith("@df.senac.br")) {
            return "redirect:/";
        }
        model.addAttribute("erro", "E-mail ou senha invalida");

        return "login";
    }


    @GetMapping("/alterarSenha")
    public String exibirAlteraSenha(Model model) {
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina", "alterar senha");
        model.addAttribute("subTituloPagina", "Digita sua nova senha");

        return "alterarSenha";

    }

    @PostMapping("alterarSenha")
    public String processarAlterarSenha(@ModelAttribute UsuarioForm form, Model model) {
        if (!form.getSenha().equals(form.getConfirmarsenha())) {
            model.addAttribute("erro", "Erro, as senha não conferem!");
            return "alterarSenha";
        }
        model.addAttribute("sucesso", "Senha alterada com sucesso");

        return "redirect:/login";
    }



}
