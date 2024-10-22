package API.Builders;

public class UserBuilder {
    public int randomUserId(int max) {
        return (int) Math.round(Math.random() * max);
    }
}
