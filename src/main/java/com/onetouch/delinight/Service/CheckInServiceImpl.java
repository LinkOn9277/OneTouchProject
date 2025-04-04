package com.onetouch.delinight.Service;

import com.onetouch.delinight.DTO.CheckInDTO;
import com.onetouch.delinight.DTO.HotelDTO;
import com.onetouch.delinight.DTO.RoomDTO;
import com.onetouch.delinight.DTO.UsersDTO;
import com.onetouch.delinight.Entity.CheckInEntity;
import com.onetouch.delinight.Entity.RoomEntity;
import com.onetouch.delinight.Entity.UsersEntity;
import com.onetouch.delinight.Repository.CheckInRepository;
import com.onetouch.delinight.Repository.RoomRepository;
import com.onetouch.delinight.Repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService{

    private final CheckInRepository checkInRepository;
    private final RoomRepository roomRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;


    @Override
    public void create(CheckInDTO checkInDTO, String email) {

        CheckInEntity checkInEntity =
                modelMapper.map(checkInDTO, CheckInEntity.class)
                        .setUsersEntity(modelMapper.map(checkInDTO.getUsersDTO(), UsersEntity.class));

        RoomEntity roomEntity =
            roomRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        UsersEntity usersEntity =
            usersRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        usersEntity =
            usersRepository.selectEmail(email);



        checkInEntity.builder()
                .checkinDate(checkInEntity.getCheckinDate())
                .checkoutDate(checkInEntity.getCheckoutDate())
                .checkInStatus(checkInEntity.getCheckInStatus())
                .price(checkInEntity.getPrice())
                .guestEntity(checkInEntity.getGuestEntity())
                .usersEntity(usersEntity)
                .roomEntity(roomEntity)
                .build();

        checkInRepository.save(checkInEntity);

    }

    @Override
    public void create(CheckInDTO checkInDTO) {



        CheckInEntity checkInEntity =
                modelMapper.map(checkInDTO, CheckInEntity.class)
                        .setUsersEntity(modelMapper.map(checkInDTO.getUsersDTO(), UsersEntity.class));


        RoomEntity roomEntity =
                roomRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        UsersEntity usersEntity =
                usersRepository.findById(1L).orElseThrow(EntityNotFoundException::new);




        checkInEntity.builder()
                .checkinDate(checkInEntity.getCheckinDate())
                .checkoutDate(checkInEntity.getCheckoutDate())
                .checkInStatus(checkInEntity.getCheckInStatus())
                .price(checkInEntity.getPrice())
                .guestEntity(checkInEntity.getGuestEntity())
                .usersEntity(usersEntity)
                .roomEntity(roomEntity)
                .build();

        checkInRepository.save(checkInEntity);

    }

    @Override
    public List<CheckInDTO> list() {
        List<CheckInEntity> checkInEntityList =
            checkInRepository.findAll();

        List<CheckInDTO> checkInDTOList =
                checkInEntityList.stream().map(
                        checkInEntity -> modelMapper.map(checkInEntity, CheckInDTO.class)
                                .setRoomDTO(modelMapper.map(checkInEntity.getRoomEntity(), RoomDTO.class))
                ).collect(Collectors.toList());

        return checkInDTOList;
    }
}
