package use_case.signup;

import entity.User;

public class SignupInteractor implements SignupInputBoundary{
    private final SignupDataAccessInterface userRepository;
    private final SignupOutputBoundary signupOutputBoundary;

    public SignupInteractor(SignupDataAccessInterface userRepository,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userRepository = userRepository;
        this.signupOutputBoundary = signupOutputBoundary;
    }

    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        if (userRepository.existByName(signupInputData.getUsername())) {
            signupOutputBoundary.prepareMessage(
                    new SignupOutputData(username, true));
            userRepository.save(new User(
                    username, signupInputData.getPassword()));
        }
        else {
            signupOutputBoundary.prepareMessage(
                    new SignupOutputData(username, false));
        }
    }
}
