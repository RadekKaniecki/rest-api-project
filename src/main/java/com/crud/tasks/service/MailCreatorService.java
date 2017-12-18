package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
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

    @Autowired
    private TaskRepository taskRepository;

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

    public String buildTrelloCardEmailWithCardSize(String message) {
        Context context = new Context();
        context.setVariable("message", getCardSize());
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name",adminConfig.getCompanyName());
        context.setVariable("company_street", adminConfig.getStreet());
        context.setVariable("company_building_number", adminConfig.getBuildingNumber());
        context.setVariable("company_phone_number", adminConfig.getPhoneNumber());
        context.setVariable("goodbye", "Thank you for using our software.");
        context.setVariable("show_button", false);
        return templateEngine.process("mail/trello-card-size", context);
    }

    private String getCardSize() {
        long size = taskRepository.count();
        String message;
        if(size == 1) {
            return message = "Currently in database you got: " + size + " task.";
        }else {
            return message = "Currently in database you got: " + size + " tasks.";
        }
    }
}
