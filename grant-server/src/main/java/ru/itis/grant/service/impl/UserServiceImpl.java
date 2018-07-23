package ru.itis.grant.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.*;
import ru.itis.grant.dto.response.*;
import ru.itis.grant.model.Message;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.request.UserUpdateDto;
import ru.itis.grant.model.*;
import ru.itis.grant.security.exception.IncorrectDataException;
import ru.itis.grant.service.interfaces.UserService;
import ru.itis.grant.service.utils.generators.interfaces.HashGenerator;
import ru.itis.grant.service.utils.generators.interfaces.MessageGenerator;
import ru.itis.grant.service.utils.generators.interfaces.TokenGenerator;
import ru.itis.grant.validation.verification.Verification;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Verification verification;
    @Autowired
    ConversionResultFactory conversionFactory;
    @Autowired
    ConversionListResultFactory conversionListFactory;
    @Autowired
    HashGenerator hashGenerator;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    MessageGenerator messageGenerator;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    PatternDao patternDao;
    @Autowired
    ApplicationDao applicationDao;
    @Autowired
    ElementValueDao elementValueDao;
    @Autowired
    ActivationKeyDao activationKeyDao;

    @Override
    public TokenDto login(AuthDto authDto) {
        verification.verifyEmailExistence(authDto.getEmail());
        User userFromDB = userDao.getUserByEmail(authDto.getEmail());
        if (userFromDB.getRole().intern() == "GUEST") {
            throw new IncorrectDataException("email", "Вы не подтвердили почту");
        }
        if (hashGenerator.match(authDto.getPassword(), userFromDB.getHashPassword())) {
            String token = tokenGenerator.generateToken();
            userFromDB.setToken(token);
            userDao.updateUser(userFromDB);
            return TokenDto.builder()
                    .token(token)
                    .build();
        } else {
            throw new IncorrectDataException("email and password", "Неверный email или пароль");
        }
    }

    @Override
    public RegistrationResponse register(RequestUserDto userDto) {
        verification.verifyEmailUnique(userDto.getEmail());
        verification.verifyUserDto(userDto);
        User user = conversionFactory.requestUserDtoToUser(
                hashGenerator.encode(userDto.getPassword()),
                userDto);
        user.setRole("GUEST");
        userDao.addUser(user);
        String key = sendRegistrationEmail(user);
        return RegistrationResponse.builder()
                .message("Подтвердите регистрацию " + key)
                .build();
    }

    @Override
    public TokenDto activate(String activationKey) {
        //TODO: удалять других неактивных пользователей с такой же почтой
        verification.verifyActivationKeyExistence(activationKey);
        ActivationKey activationKeyFromDB = activationKeyDao.getActivationKey(activationKey);
        String token = tokenGenerator.generateToken();
        User user = activationKeyFromDB.getUser();
        user.setToken(token);
        userDao.updateUser(user);
        activationKeyDao.deleteUserActivationKeys(user.getId());
        return TokenDto.builder()
                .token(token)
                .build();
    }

    @Override
    public ResponseUserDto userInfo(String token) {
        verification.verifyTokenExistence(token);
        User user = userDao.getUserByToken(token);
        ResponseUserDto responseUserDto = conversionFactory.userToResponseUserDto(user);
        return responseUserDto;
    }

    @Override
    public ResponseUserDto updateUserInfo(String token, UserUpdateDto userUpdateDto) {
        verification.verifyTokenExistence(token);
        verification.verifyEmail(userUpdateDto.getEmail());
        User user = userDao.getUserByToken(token);
        user.setFirstName(userUpdateDto.getFirstName());
        user.setSecondName(userUpdateDto.getSecondName());
        user.setMiddleName(userUpdateDto.getMiddleName());
        if (user.getEmail().intern() != userUpdateDto.getEmail().intern()) {
            verification.verifyEmailUnique(userUpdateDto.getEmail());
            user.setEmail(userUpdateDto.getEmail());
        }
        userDao.updateUser(user);
        ResponseUserDto responseUserDto = conversionFactory.userToResponseUserDto(user);
        return responseUserDto;
    }

    @Override
    public List<ResponseEventDto> getEvents() {
        List<Event> events = eventDao.getEvents();
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getEvents(int from, int count) {
        List<Event> events = eventDao.getEvents(from, count);
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEvents() {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()));
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEvents(int from, int count) {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()), from, count);
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEventsWithPattern() {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()));
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEventsWithPattern(int from, int count) {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()), from, count);
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public ResponseEventDto getEvent(long eventId) {
        verification.verifyEventExistenceById(eventId);
        Event event = eventDao.getEvent(eventId);
        ResponseEventDto responseEventDto = conversionFactory.eventToResponseEventDto(event);
        return responseEventDto;
    }

    @Override
    public ResponsePatternDto getEventPattern(long eventId) {
        verification.verifyEventPatternExistence(eventId);
        Pattern pattern = patternDao.getEventPattern(eventId);
        ResponsePatternDto responsePatternDto = conversionFactory.patternToResponsePatternDto(pattern);
        return responsePatternDto;
    }

    @Override
    public ResponseApplicationDto createApplication(String token, RequestApplicationDto requestApplicationDto) {
        Date currentDate = new Date(System.currentTimeMillis());
        verification.verifyTokenExistence(token);
        verification.verifyPatternExistence(requestApplicationDto.getPatternId());
        verification.verifyUserPatternApplicationExistence(token, requestApplicationDto.getPatternId());
        verification.verifyPatternTimeLimit(requestApplicationDto.getPatternId(), currentDate);
        Pattern pattern = patternDao.getPattern(requestApplicationDto.getPatternId());
        verification.verifyApplicationDto(requestApplicationDto, pattern);
        Application application = conversionFactory.requestApplicationDtoToApplication(requestApplicationDto);
        User user = userDao.getUserByToken(token);
        application.setApplicationDate(currentDate);
        application.setStatus("ACTIVE");
        application.setUser(user);
        application.setPattern(pattern);
        applicationDao.addApplication(application);
        for (ElementValue elementValue : application.getValueList()) {
            elementValue.setApplication(application);
            elementValueDao.addElementValue(elementValue);
        }
        Application applicationFromDB = applicationDao.getApplicationById(application.getId());
        ResponseApplicationDto responseApplicationDto = conversionFactory.applicationToResponseApplicationDto(applicationFromDB);
        return responseApplicationDto;
    }

    @Override
    public List<ResponseApplicationDto> getUserApplications(String token) {
        verification.verifyTokenExistence(token);
        List<Application> applications = applicationDao.getUserApplications(token);
        List<ResponseApplicationDto> applicationDtoList = conversionListFactory.applicationsToResponseApplicationDtos(applications);
        return applicationDtoList;
    }

    @Override
    public List<ResponseApplicationDto> getUserApplications(String token, int from, int count) {
        verification.verifyTokenExistence(token);
        List<Application> applications = applicationDao.getUserApplications(token, from, count);
        List<ResponseApplicationDto> applicationDtoList = conversionListFactory.applicationsToResponseApplicationDtos(applications);
        return applicationDtoList;
    }

    @Override
    public ResponseApplicationDto getApplication(String token, long applicationId) {
        verification.verifyUserApplicationExistence(token, applicationId);
        Application application = applicationDao.getApplicationById(applicationId);
        ResponseApplicationDto responseApplicationDto = conversionFactory.applicationToResponseApplicationDto(application);
        return responseApplicationDto;
    }

    @Override
    public ResponseApplicationDto getApplicationByEvent(String token, long eventId) {
        verification.verifyUserEventApplicationExistence(token, eventId);
        Application application = applicationDao.getApplicationByEventId(token, eventId);
        return conversionFactory.applicationToResponseApplicationDto(application);
    }

    @Override
    public ResponseApplicationDto updateApplication(long id, String token, RequestApplicationDto requestApplicationDto) {
        Date currentDate = new Date(System.currentTimeMillis());
        verification.verifyUserApplicationExistenceById(token, id);
        Application application = applicationDao.getApplicationById(id);
        verification.verifyPatternTimeLimit(application.getPattern().getId(), currentDate);
        verification.verifyApplicationDto(requestApplicationDto, application.getPattern());
        application.setApplicationDate(currentDate);
        application.setStatus("ACTIVE");
        application.setValueList(conversionListFactory.requestElementValueDtosToElementValues(requestApplicationDto.getValues()));
        applicationDao.updateApplication(application);
        ResponseApplicationDto responseApplicationDto = conversionFactory.applicationToResponseApplicationDto(application);
        return responseApplicationDto;
    }

    @Override
    public boolean deleteApplication(String token, long applicationId) {
        verification.verifyUserApplicationExistenceById(token, applicationId);
        applicationDao.deleteApplication(applicationDao.getApplicationById(applicationId));
        return true;
    }

    private String sendRegistrationEmail(User user) {
        String key = UUID.randomUUID().toString();
        Message message = Message.builder()
                .subject("Регистрация")
                .text(messageGenerator.generateRegistrationMessage(key))
                .email(user.getEmail())
                .build();
        ActivationKey activationKey = ActivationKey.builder()
                .sendDate(new Date())
                .key(key)
                .user(user)
                .build();
        activationKeyDao.addActivationKey(activationKey);
        rabbitTemplate.convertAndSend("grant-exchange", "messages", message);
        return key;
    }
}
