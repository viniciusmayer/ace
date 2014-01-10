package com.eleonorvinicius.ace.data;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.entity.User;
import com.eleonorvinicius.ace.exception.ACEException;

public class UserData extends Data<User> {

	private static UserData userData;

	private UserData() {
		User user = new User("admin", "123qwe");
		this.getObjects().put(user.getId(), user);
	}

	public static UserData getInstance() {
		if (userData == null) {
			userData = new UserData();
		}
		return userData;
	}

	@Override
	public void validate(User entidade) throws ACEException {
		for (Long l : this.getObjects().keySet()) {
			User u = this.getObjects().get(l);
			if (u.getEmail().equals(entidade.getEmail())) {
				throw new ACEException(R.string.emailAlreadyExists, "email already exists");
			}
		}
	}
}
