package umc.study.apiPayload.code.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}