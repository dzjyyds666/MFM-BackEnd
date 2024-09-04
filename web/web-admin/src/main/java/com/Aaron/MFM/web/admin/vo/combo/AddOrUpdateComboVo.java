package com.Aaron.MFM.web.admin.vo.combo;

import com.Aaron.MFM.model.entity.ComboInfo;
import lombok.Data;

import java.util.List;

@Data
public class AddOrUpdateComboVo extends ComboInfo {
    private List<Integer> foodIdList;
}
