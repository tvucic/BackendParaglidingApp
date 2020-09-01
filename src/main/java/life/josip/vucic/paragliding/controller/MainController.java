package life.josip.vucic.paragliding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import life.josip.vucic.paragliding.exceptions.UserServiceException;
import life.josip.vucic.paragliding.service.UserService;
import life.josip.vucic.paragliding.shared.dto.UserDto;
import life.josip.vucic.paragliding.ui.model.request.UserDetailsRequestModel;
import life.josip.vucic.paragliding.ui.model.response.ErrorMessages;
import life.josip.vucic.paragliding.ui.model.response.OperationStatusModel;
import life.josip.vucic.paragliding.ui.model.response.RequestOperationName;
import life.josip.vucic.paragliding.ui.model.response.RequestOperationStatus;
import life.josip.vucic.paragliding.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class MainController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();
    UserDto userDto = userService.getUserByUserId(id);
    BeanUtils.copyProperties(userDto, returnValue);
    return returnValue;
  }

  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
    UserRest returnedValue = new UserRest();

    if (userDetails.getFirstName().isEmpty())
      throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userDetails, userDto);
    UserDto createdUser = userService.createUser(userDto);

    BeanUtils.copyProperties(createdUser, returnedValue);

    return returnedValue;
  }

  @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE })
  public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
    UserRest returnedValue = new UserRest();

    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userDetails, userDto);
    UserDto updatedUser = userService.updateUser(id, userDto);

    BeanUtils.copyProperties(updatedUser, returnedValue);
    return returnedValue;
  }

  @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

  public OperationStatusModel deleteUser(@PathVariable String id) {

    OperationStatusModel returnValue = new OperationStatusModel();
    returnValue.setOperationName(RequestOperationName.DELETE.name());

    userService.deleteUser(id);
    returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
    return returnValue;

  }

  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {
    List<UserRest> returnValue = new ArrayList<>();

    List<UserDto> users = userService.getUsers(page, limit);

    for (UserDto userDto : users) {
      UserRest userModel = new UserRest();
      BeanUtils.copyProperties(userDto, userModel);
      returnValue.add(userModel);
    }

    return returnValue;
  }
}