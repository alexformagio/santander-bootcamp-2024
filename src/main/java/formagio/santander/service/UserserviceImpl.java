package formagio.santander.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import formagio.santander.model.User;
import formagio.santander.repository.UserRepository;

@Service
public class UserserviceImpl implements UserService {
	
	private final UserRepository userRepository;

	public UserserviceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User user) {
		if(user.getId() != null && userRepository.existsById(user.getId())) {
			throw new IllegalArgumentException("Esse Usuário já existe!");
		}
		return userRepository.save(user);
	}

}
