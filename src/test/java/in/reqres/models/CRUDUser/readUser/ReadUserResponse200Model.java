package in.reqres.models.CRUDUser.readUser;

import lombok.Data;
import in.reqres.models.userData.SupportDataObject;
import in.reqres.models.userData.UserDataList;

@Data
public class ReadUserResponse200Model {
    private UserDataList data;
    private SupportDataObject support;
}
