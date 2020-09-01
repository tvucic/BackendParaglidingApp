package life.josip.vucic.paragliding.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import life.josip.vucic.paragliding.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto user);

  UserDto getUser(String email);

  UserDto getUserByUserId(String userId);

  UserDto updateUser(String id, UserDto user);

  void deleteUser(String id);

  List<UserDto> getUsers(int page, int limit);
}