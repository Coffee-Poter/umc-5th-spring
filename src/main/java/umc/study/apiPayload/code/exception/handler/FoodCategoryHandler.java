package umc.study.apiPayload.code.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
