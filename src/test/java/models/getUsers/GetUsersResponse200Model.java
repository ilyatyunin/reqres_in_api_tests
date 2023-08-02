package models.getUsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.userData.SupportDataObject;
import models.userData.UserDataList;

import java.util.List;

@Data
public class GetUsersResponse200Model {
    Integer page;

    @JsonProperty("per_page")
    Integer perPage;

    Integer total;

    @JsonProperty("total_pages")
    Integer totalPages;

    List<UserDataList> data;

    SupportDataObject support;
}

