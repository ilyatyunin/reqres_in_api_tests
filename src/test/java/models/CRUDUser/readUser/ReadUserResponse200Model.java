package models.CRUDUser.readUser;

import lombok.Data;
import models.userData.SupportDataObject;
import models.userData.UserDataList;

@Data
public class ReadUserResponse200Model {
    UserDataList data;
    SupportDataObject support;
}
