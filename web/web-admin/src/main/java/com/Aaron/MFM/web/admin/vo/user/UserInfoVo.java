package com.Aaron.MFM.web.admin.vo.user;

import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.model.entity.ProvinceInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户个人信息")
public class UserInfoVo extends UserInfo{

    @Schema(description = "城市信息")
    private CityInfo cityInfo;

    @Schema(description = "省份信息")
    private ProvinceInfo provinceInfo;

}
