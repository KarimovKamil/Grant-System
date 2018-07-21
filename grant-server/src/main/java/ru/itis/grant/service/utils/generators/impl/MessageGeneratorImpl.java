package ru.itis.grant.service.utils.generators.impl;

import org.springframework.stereotype.Component;
import ru.itis.grant.service.utils.generators.interfaces.MessageGenerator;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private String siteUrl = "http://grant-service.herokuapp.com/active?key=";

    public String generateRegistrationMessage(String activationKey) {
        String text  = "Вы успешно зарегистрировались на сайте \"Грант\". " +
                "\n\nВам осталось лишь подтвердить вашу почту." +
                "<a href=\"" + siteUrl + activationKey +
                "\">Нажмите сюда</a>, чтобы подвердить регистрацию";
        return text;
    }
}
