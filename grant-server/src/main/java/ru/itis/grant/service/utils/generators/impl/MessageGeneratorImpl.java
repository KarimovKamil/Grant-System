package ru.itis.grant.service.utils.generators.impl;

import org.springframework.stereotype.Component;
import ru.itis.grant.service.utils.generators.interfaces.MessageGenerator;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    //TODO: вынести
    private String siteUrl = "http://grant-service.herokuapp.com/user/activate?key=";

    public String generateRegistrationMessage(String activationKey) {
        String text  = "Вы успешно зарегистрировались на сайте \"Грант\". " +
                "\n\nВам осталось лишь подтвердить вашу почту. Перейдите по ссылке \n\n"+
                siteUrl + activationKey + ", \n\nчтобы подвердить регистрацию";
        return text;
    }
}
