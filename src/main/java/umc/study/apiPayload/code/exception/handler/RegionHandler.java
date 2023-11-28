package umc.study.apiPayload.code.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode){super(errorCode);}
}
