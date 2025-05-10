package com.ozan.service;

import com.ozan.dto.HostessDTO;
import java.util.List;

public interface HostessService {
    HostessDTO save(HostessDTO hostess);

    List<HostessDTO> listAllHostesses();

    HostessDTO findByUserTcId(String userTcId);

    HostessDTO updateHostess(HostessDTO hostessDTO);

    void delete(String userTcId);

    List<HostessDTO> listAvailableHostess();
}
