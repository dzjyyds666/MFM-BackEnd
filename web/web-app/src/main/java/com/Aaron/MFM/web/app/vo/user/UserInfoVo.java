package com.Aaron.MFM.web.app.vo.user;

import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.model.entity.ProvinceInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import lombok.Data;

@Data
public class UserInfoVo extends UserInfo {

    private CityInfo cityInfo;

    private ProvinceInfo provinceInfo;

}
