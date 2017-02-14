package ar.com.uade.pfi.service;

import ar.com.uade.pfi.domain.User;

public class UserService {

    public User getDefaultUser() {
    	User user = new User();
        user.setFirstName("JsonFromREST");
        user.setLastName("DoeFromREST");
        return user;
    }
}
