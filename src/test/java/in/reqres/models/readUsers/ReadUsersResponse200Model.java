package in.reqres.models.readUsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import in.reqres.models.userData.SupportDataObject;
import in.reqres.models.userData.UserDataList;

import java.util.List;

@Data
public class ReadUsersResponse200Model {
    Integer page;

    @JsonProperty("per_page")
    Integer perPage;

    Integer total;

    @JsonProperty("total_pages")
    Integer totalPages;

    List<UserDataList> data;

    SupportDataObject support;
}

