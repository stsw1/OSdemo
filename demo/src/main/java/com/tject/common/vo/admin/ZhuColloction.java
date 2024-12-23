package com.tject.common.vo.admin;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ZhuColloction {
    private String ids;
    private String addressGroup;

    public List<Integer> getIdList() {
        return Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
