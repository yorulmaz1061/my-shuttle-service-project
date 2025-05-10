package com.ozan.service.impl;

import com.ozan.dto.HostessDTO;
import com.ozan.entity.Hostess;
import com.ozan.enums.Status;
import com.ozan.enums.UserType;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.HostessRepository;
import com.ozan.service.HostessService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostessServiceImpl implements HostessService {
    private final HostessRepository hostessRepository;
    private final MapperUtil mapperUtil;

    public HostessServiceImpl(HostessRepository hostessRepository, MapperUtil mapperUtil) {
        this.hostessRepository = hostessRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public HostessDTO save(HostessDTO hostess) {
        hostess.setStatus(Status.ACTIVE);
        hostess.setUserType(UserType.HOSTESS);
        Hostess savedHostess = hostessRepository.save(mapperUtil.convert(hostess, new Hostess()));
        return mapperUtil.convert(savedHostess, new HostessDTO());

    }

    @Override
    public List<HostessDTO> listAllHostesses() {
       List<Hostess>  hostessList = hostessRepository.findAllByIsDeletedOrderByUserFirstName(false);
       return hostessList.stream().map(hostess -> mapperUtil.convert(hostess,new HostessDTO()))
               .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public HostessDTO findByUserTcId(String userTcId) {
        Hostess hostess = hostessRepository.findByUserTcId(userTcId);
        if(hostess == null) throw new IllegalArgumentException("Hostess not found.");
        return mapperUtil.convert(hostess,new HostessDTO());
    }

    @Override
    public HostessDTO updateHostess(HostessDTO hostessDTO) {
        Hostess hostess = hostessRepository.findByUserTcIdAndIsDeleted(hostessDTO.getUserTcId(), false);
        Hostess convertedHostess = mapperUtil.convert(hostessDTO,hostess);
        convertedHostess.setId(hostess.getId());
        convertedHostess.setStatus(Status.ACTIVE);
        hostessRepository.save(convertedHostess);
        return findByUserTcId(hostessDTO.getUserTcId());

    }

    @Override
    public void delete(String userTcId) {
        Hostess hostess = hostessRepository.findByUserTcIdAndIsDeleted(userTcId,false);
        hostess.setIsDeleted(true);
        hostess.setStatus(Status.PASSIVE);
        hostessRepository.save(hostess);

    }

    @Override
    public List <HostessDTO> listAvailableHostess() {
        List<Hostess> availableHostessList = hostessRepository.findAllByVehicleIsNullAndIsDeletedFalseAndStatusEquals(Status.ACTIVE);
        return availableHostessList.stream().map(hostess -> mapperUtil.convert(hostess,new HostessDTO()))
                .collect(Collectors.toList());

    }


}
