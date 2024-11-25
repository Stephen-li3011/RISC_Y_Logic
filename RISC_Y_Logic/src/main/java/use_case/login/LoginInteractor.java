package use_case.login;

import data_access.InMemoryUserRepository;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface userRepository;
    private final LoginOutputBoundary loginOutputBoundary;

    public LoginInteractor(InMemoryUserRepository userRepository,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userRepository = userRepository;
        this.loginOutputBoundary = loginOutputBoundary;
    }

    @Override
    public void execute (LoginInputData loginInputData) {
        User user = userRepository.findByName(loginInputData.getUsername());
        if (user != null && user.getPassword()
                .equals(loginInputData.getPassword())) {
            loginOutputBoundary.prepareMessage(new LoginOutputData(
                    user.getUsername(), false));
        }
        else {
            loginOutputBoundary.prepareMessage(new LoginOutputData(null, true));
        }
    }
}
