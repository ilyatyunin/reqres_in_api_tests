package in.reqres.models.crud;

import lombok.Data;

@Data
public class CreateUserResponse201Model {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
