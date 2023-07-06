package models.getUsers;

import lombok.Data;

import java.util.List;

@Data
public class GetUsersResponse200Model {
    Integer page;
    Integer per_page;
    Integer total;
    Integer total_pages;
    List<UserDataList> data;
    SupportDataObject support;
}

