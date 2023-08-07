package in.reqres.models.readUsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import in.reqres.models.userData.SupportDataObject;
import in.reqres.models.userData.UserDataList;

import java.util.List;

@Data
public class ReadUsersResponse200Model {
    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    private Integer total;

    @JsonProperty("total_pages")
    private Integer totalPages;

    private List<UserDataList> data;

    private SupportDataObject support;
}

