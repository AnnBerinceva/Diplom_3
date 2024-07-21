import java.util.Random;

public class UserData {
    private String email = "email" + new Random().nextInt(10000) + "@yandex.ru";
    private String password = "123456" + new Random().nextInt(10000);
    private String name = "user" + new Random().nextInt(10000);

    public String getRandomName() {
        return this.name;
    }

    public  String getRandomEmail() {
        return this.email;
    }

    public  String getRandomPassword() {
        return this.password;
    }
}