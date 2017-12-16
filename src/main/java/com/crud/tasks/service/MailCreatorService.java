package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://radekkaniecki.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name",adminConfig.getCompanyName());
        context.setVariable("company_street", adminConfig.getStreet());
        context.setVariable("company_building_number", adminConfig.getBuildingNumber());
        context.setVariable("company_phone_number", adminConfig.getPhoneNumber());
        context.setVariable("goodbye", "Thank you for using our software.");
        context.setVariable("show_button", true);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
