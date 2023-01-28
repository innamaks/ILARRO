package manager;

import model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openFormLogin() {
        click(By.xpath("//a[text()=' Log in ']"));
        pause(500);
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        pause(500);
        type(By.id("password"), password);
        pause(500);

    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        pause(500);
        type(By.id("password"), user.getPassword());
        pause(500);
    }


    public void closeDialogContainer() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {

            click(By.xpath("//button[text()='Ok']"));
            pause(500);
        }
    }

    public boolean isLogged() {
        // return isElementPresent(By.xpath("//button[text()=' Logout ']"));
        return isElementPresent(By.xpath("//a[normalize-space()='Logout']"));
    }

    public void logout() {
        //click(By.xpath("//button[text()=' Logout ']"));

        click(By.xpath("//a[normalize-space()='Logout']"));
        pause(1000);

    }

    public String getErrorText() {

        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        // return isElementPresent(By.cssSelector("button[disabled]"));тру-активная фолс-неак.
        return !wd.findElement(By.cssSelector("button[disabled]")).isEnabled();//тру-не активная 2 метод
    }

    public String getMessage() {
        return wd.findElement(By.cssSelector("div.dialog-container>h2")).getText();
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[@class='navigation-link'][normalize-space()='Sign up']"));
        pause(1000);
    }

    public void fillRegistrationForm(User user) {
        pause(500);
        type(By.id("name"), user.getName());
        pause(500);
        type(By.id("lastName"), user.getLastName());
        pause(500);
        type(By.id("email"), user.getEmail());
        pause(500);
        type(By.id("password"), user.getPassword());
        pause(500);

    }

    public void checkPolicy() {
        // click(By.cssSelector("label[for='terms-of-use']"));
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            click(By.cssSelector(".checkbox-container"));
            pause(500);
        }
    }

    public void checkPolicyXY() {
        Dimension size = wd.manage().window().getSize();
        System.out.println("Window Height " + size.getHeight());//ширина
        System.out.println("Window Height " + size.getWidth());  //длина
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int xOfset = rect.getWidth() / 2;
        Actions actions = new Actions(wd);
        actions.moveToElement(label, -xOfset, 0).click().release().perform();
    }

    public void checkPolicyJS() {

        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').checked=true;");

    }


    public boolean isErrorMessageContains(String message) {
        return wd.findElement(By.cssSelector(".error")).getText().contains(message);
    }

    public void login(User user) {
        openFormLogin();
        fillLoginForm(user);
        submit();
        closeDialogContainer();
    }
}
