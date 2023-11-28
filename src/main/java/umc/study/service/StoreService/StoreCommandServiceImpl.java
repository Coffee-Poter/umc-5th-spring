package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.exception.handler.RegionHandler;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store joinStore(StoreRequestDto.JoinDto request) {

        Store newStore = StoreConverter.toStore(request);
        //Region처리 그 지역에 추가하는 API를 만드는거니까
        //지역이 존재하지 않으면 오류 처리
        Region storeRegion = newStore.getRegion();

        //존재하지 않을 때 사용
        if (!regionRepository.existsById(storeRegion.getId())){
            throw new RegionHandler(ErrorStatus.REGION_NOT_FOUND);
        }

        //존재하면
        return storeRepository.save(newStore);
    }
}
