/*********************************************************************
 * 클래스명 : MenuRepository
 * 기능 :
 * 작성자 :
 * 작성일 : 2025-03-30
 * 수정 : 2025-04-01  이지호
 *********************************************************************/
package com.onetouch.delinight.Repository;

import com.onetouch.delinight.Entity.MembersEntity;
import com.onetouch.delinight.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    public List<MenuEntity> findByStoreEntity_HotelEntity_Id(Long hotelId);

//    public List<MenuEntity> findByMembersEntity_Email(String email);

    @Query("select m from MembersEntity m where m.email = :email")
    public MenuEntity selectEmail(String email);

    @Query("select m from MenuEntity m ,StoreEntity s where m.storeEntity.id = s.id")
    public MenuEntity select2(Long id);





}
