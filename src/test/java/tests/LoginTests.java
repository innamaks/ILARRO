package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

            System.out.println("do logout");
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("maksim@gmail.com", "AlisMaksim26!");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeDialogContainer();
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("maksim@gmail.com").withPassword("AlisMaksim26!");
        // user.setEmail("maksim@gmail.com");
        // user.setPassword("AlisMaksim26!");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeDialogContainer();
    }

    @Test

    public void loginWrongEmail() {
        User user = new User().withEmail("maksimgmail.com").withPassword("AlisMaksim26!");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());//если кнопка ялла не октивная
    }

    @Test
    public void loginWrongPassword() {
        User user = new User().withEmail("maksim@gmail.com").withPassword("Al");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @Test(enabled = false)
    public void loginUnregisterUser() {

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeDialogContainer();
    }


}
