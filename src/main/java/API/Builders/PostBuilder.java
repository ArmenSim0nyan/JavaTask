package API.Builders;

import com.github.javafaker.Faker;

import java.io.Serializable;

public class PostBuilder implements Serializable {
    private final int userId;
    private final String title;
    private final String body;

    public PostBuilder(int userId) {
        Faker faker = new Faker();
        this.userId = userId;
        this.title = faker.lorem().word();
        this.body = faker.lorem().sentence();
    }
    public PostBuilder(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
