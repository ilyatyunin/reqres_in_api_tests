package models.getUsers;

import lombok.Data;

@Data
public class UserDataList {
    Integer id;
    String email;
    String first_name;
    String last_name;
    String avatar;
}
