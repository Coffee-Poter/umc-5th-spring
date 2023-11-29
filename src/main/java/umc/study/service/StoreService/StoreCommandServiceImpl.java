package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.exception.handler.RegionHandler;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.*;
import umc.study.web.dto.StoreRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Store joinStore(StoreRequestDto.JoinDto request) {
        Store newStore = StoreConverter.toStore(request);
        if(!regionRepository.existsById(newStore.getRegion().getId())){
            throw new RegionHandler(ErrorStatus.REGION_NOT_FOUND);
        }

        return storeRepository.save(newStore);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDto.ReviewDto request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }

}
