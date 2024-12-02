package interface_adapter.mainmenu;

import view.login_and_signup.LoginAndSignupView;

public class MainMenuBackButtonController implements ButtonController {
    public void click() {
        new LoginAndSignupView();
    }
}
