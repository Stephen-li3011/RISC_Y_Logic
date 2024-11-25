package interface_adapter.signup;

import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;

public class SignupController {
    private final SignupInteractor signupInteractor;

    public SignupController(SignupInteractor signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    public void signup(String username, String password) {
        SignupInputData signupInputData =
                new SignupInputData(username, password);
        signupInteractor.execute(signupInputData);
    }
}
