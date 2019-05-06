package com.github.dmotta.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("group1")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String group1() {
        return "Admin message";
    }

    @GetMapping("group2")
    @ResponseBody
    @PreAuthorize("hasRole('READER')")
    public String group2() {
        return "Reader message";
    }

    @GetMapping("/")
    public String index(Model model, OAuth2AuthenticationToken authentication) {
        final OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        return "index";
    }
}