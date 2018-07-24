package ru.itis.grant.service.utils.generators.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.itis.grant.service.utils.generators.interfaces.MessageGenerator;

@Component
@PropertySource("/application.properties")
public class MessageGeneratorImpl implements MessageGenerator {

    @Value("${url}")
    private String siteUrl;
    private String path = "/user/activate?key=";

    public String generateRegistrationMessage(String activationKey) {
        return "Вы успешно зарегистрировались на сайте \"Грант\". " +
                "<br><br>Вам осталось лишь подтвердить вашу почту. <br><br>" +
                "<a href=\""+ siteUrl + path + activationKey + "\">Нажмите сюда</a>, " +
                "чтобы подвердить регистрацию";
    }
}
