package ru.itis.grant.validation.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.grant.dao.interfaces.ApplicationDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.security.exception.IncorrectDataException;
import ru.itis.grant.validation.dto.ApplicationDtoValidator;
import ru.itis.grant.validation.dto.EventDtoValidator;
import ru.itis.grant.validation.dto.PatternDtoValidator;
import ru.itis.grant.validation.dto.UserDtoValidator;

import java.util.Date;

@Component
public class Verification {

    @Autowired
    UserDao userDao;
    @Autowired
    ApplicationDao applicationDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    PatternDao patternDao;

    public void verifyTokenExistence(String token) {
        if (!userDao.userExistenceByToken(token)) {
            throw new IncorrectDataException("token", "Неверный токен");
        }
    }

    public void verifyEmailExistence(String email) {
        if (!userDao.userExistenceByEmail(email)) {
            throw new IncorrectDataException("email", "Неверный email");
        }
    }

    public void verifyEmailUnique(String email) {
        if (userDao.userExistenceByEmail(email)) {
            throw new IncorrectDataException("email", "Пользователь с таким email уже существует");
        }
    }

    public void verifyEmail(String email) {
        if (!UserDtoValidator.getInstance().verifyEmail(email)) {
            throw new IncorrectDataException("email", "Неверно введен email");
        }
    }

    public void verifyApplicationExistenceById(long id) {
        if (!applicationDao.applicationExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный id заявки");
        }
    }

    public void verifyUserApplicationExistenceById(String token, long id) {
        if (!applicationDao.userApplicationExistence(token, id)) {
            throw new IncorrectDataException("id", "Неверный id заявки");
        }
    }

    public void verifyPatternExistence(long id) {
        if (!patternDao.patternExistence(id)) {
            throw new IncorrectDataException("id", "Неверный id шаблона");
        }
    }

    public void verifyUserPatternApplicationExistence(String token, long patternId) {
        if (applicationDao.userPatternApplicationExistence(token, patternId)) {
            throw new IncorrectDataException("id", "Вы уже подали заявку на это мероприятие");
        }
    }

    public void verifyUserEventApplicationExistence(String token, long patternId) {
        if (applicationDao.userPatternApplicationExistence(token, patternId)) {
            throw new IncorrectDataException("id", "Вы уже подали заявку на это мероприятие");
        }
    }

    public void verifyEventExistenceById(long id) {
        if (!eventDao.eventExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный id конкурса");
        }
    }

    public void verifyEventPatternExistence(long eventId) {
        if (!eventDao.verifyEventPatternExistence(eventId)) {
            throw new IncorrectDataException("pattern",
                    "Конкурс не существует или у конкурса отсутствует шаблон для заявок");
        }
    }

    public void verifyUserApplicationExistence(String token, long applicationId) {
        if (!applicationDao.userApplicationExistence(token, applicationId)) {
            throw new IncorrectDataException("id", "Заявка с таким id не найдена");
        }
    }

    public void verifyExpertApplicationExistence(String token, long applicationId) {
        if (!applicationDao.expertApplicationExistence(token, applicationId)) {
            throw new IncorrectDataException("id", "Заявка с таким id не найдена");
        }
    }

    public void verifyExpertEventExistence(String token, long eventId) {
        if (!eventDao.expertEventExistence(token, eventId)) {
            throw new IncorrectDataException("id", "Конкурс с таким id не найден");
        }
    }

    public void verifyPatternTimeLimit(long patternId, Date date) {
        if (!patternDao.patternTimeCorrect(patternId, date)) {
            throw new IncorrectDataException("date",
                    "Шаблон с таким id не существует либо некорректное время подачи заявки");
        }
    }

    public void verifyUserDto(RequestUserDto userDto) {
        UserDtoValidator.getInstance().verify(userDto);
    }

    public void verifyApplicationDto(RequestApplicationDto applicationDto, Pattern pattern) {
        ApplicationDtoValidator.getInstance().verify(applicationDto, pattern);
    }

    public void verifyEventDto(RequestEventDto eventDto) {
        EventDtoValidator.getInstance().verify(eventDto);
    }

    public void verifyPatternDto(RequestPatternDto patternDto) {
        PatternDtoValidator.getInstance().verify(patternDto);
    }

    public void verifyOrganizerEventExistence(long eventId, String token) {
        if (!eventDao.organizerEventExistence(eventId, token)) {
            throw new IncorrectDataException("id", "Пользователь не является собственником события");
        }
    }

    public void verifyUserIdExistence(long id) {
        if (!userDao.userExistenceById(id)) {
            throw new IncorrectDataException("id", "Неверный идентификатор пользователя");
        }
    }

    public void verifyEventExpertExistence(long eventId, long expertId) {
        if (!eventDao.eventExpertExistence(eventId, expertId)) {
            throw new IncorrectDataException("id", "Эксперт не принадлежит событию");
        }
    }

    public void verifyEventExpertAddingCase(long eventId, long expertId) {
        if (eventDao.eventExpertExistence(eventId, expertId)) {
            throw new IncorrectDataException("id", "Такая связь уже есть");
        }
    }

    public void verifyPatternAddingCase(long eventId) {
        if (eventDao.verifyEventPatternExistence(eventId)) {
            throw new IncorrectDataException("id", "Шаблон для этого события уже существует");
        }
    }

    public void verifyExpertBanExistence(String token, long banId) {
        if (!userDao.expertBanExistence(token, banId)) {
            throw new IncorrectDataException("id", "Бан с таким id не найден");
        }
    }
}
