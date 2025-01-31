package com.mrbean.billofmaterials.domain;

import org.springframework.stereotype.Component;

@Component
public class BomValidator {

    public void validateRatio(int ratio) {
        if (ratio < 0 || ratio > 100) {
            throw new IllegalArgumentException("BOM 비율은 0에서 100 사이여야 합니다.");
        }
    }
}
