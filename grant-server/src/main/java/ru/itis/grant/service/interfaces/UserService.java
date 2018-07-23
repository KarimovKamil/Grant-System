package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.response.*;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.request.UserUpdateDto;

import java.util.List;

public interface UserService {

    TokenDto login(AuthDto authDto);

    RegistrationResponse register(RequestUserDto authDto);

    TokenDto activate(String activationKey);

    ResponseUserDto userInfo(String token);

    ResponseUserDto updateUserInfo(String token, UserUpdateDto userUpdateDto);

    List<ResponseEventDto> getEvents();

    List<ResponseEventDto> getEvents(int from, int count);

    List<ResponseEventDto> getActiveEvents();

    List<ResponseEventDto> getActiveEvents(int from, int count);

    List<ResponseEventDto> getActiveEventsWithPattern();

    List<ResponseEventDto> getActiveEventsWithPattern(int from, int count);

    ResponseEventDto getEvent(long eventId);

    ResponsePatternDto getEventPattern(long eventId);

    ResponseApplicationDto createApplication(String token, RequestApplicationDto requestApplicationDto);

    List<ResponseApplicationDto> getUserApplications(String token);

    List<ResponseApplicationDto> getUserApplications(String token, int from, int count);

    ResponseApplicationDto getApplication(String token, long applicationId);

    ResponseApplicationDto getApplicationByEvent(String token, long eventId);

    ResponseApplicationDto updateApplication(long id, String token, RequestApplicationDto requestApplicationDto);

    boolean deleteApplication(String token, long applicationId);
}
