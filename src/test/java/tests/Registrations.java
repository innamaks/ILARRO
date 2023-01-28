package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class Registrations extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

            //System.out.println("do logout");
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Maksim").withLastName("Yurchenko").withEmail("maksm"+i+"@gmail.com").withPassword("MamaPapa2!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");

    }

    @Test
    public void registrationWrongEmail(){
        User user = new User().withName("Maksim").withLastName("Yurchenko").withEmail("maksmgmail.com").withPassword("MamaPapa2!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyJS();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
@Test
public void registrationWrongPassword(){

    User user = new User().withName("Maksim").withLastName("Yurchenko").withEmail("maksm@gmail.com").withPassword("Ma2!");
    app.getHelperUser().openRegistrationForm();
    app.getHelperUser().fillRegistrationForm(user);
    app.getHelperUser().checkPolicyXY();
    app.getHelperUser().submit();
    Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Password must contain"));
    Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

}



    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeDialogContainer();
    }




}
