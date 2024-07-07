package formagio.santander.service;

import formagio.santander.model.User;

public interface UserService {
	User findById(Long id);
	User create(User user);

}
