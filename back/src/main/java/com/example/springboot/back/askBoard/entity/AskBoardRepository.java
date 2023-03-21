package com.example.springboot.back.askBoard.entity;

// import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

public interface AskBoardRepository extends JpaRepository<AskBoard,String>{
    // List<AskBoard> findByContentNo(String contentNo);

    // Page<AskBoard> findyByContentNoContaining(String searchKeyword, Pageable pageable);
    
    // @Query(value = "SELECT * FROM TS _CONTENT c where c.contentNo= :contentNo" ,nativeQuery = true)
    // List<AskBoard> findsql(Pageable pageable,@Param(value= "contentNo") String contentNo);

    // @Query(value = "SELECT * FROM TS_CONTENT c where c.contentNo like %:searchKeyword% or c.contentNo like %:searchKeyword% ORDER BY REGIST_TIME DESC" ,nativeQuery = true)
    // Page<AskBoard> findTotal(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);

    // @Query(value ="SELECT * FROM TS_CONTENT c where c.contentNo like %:searchKeyword%" , nativeQuery = true)
    // Page<AskBoard> findCode(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);

    // @Query(value = "SELECT * FROM TS_CONTENT c where c.contentNo like %:searchKeyword% ORDER BY CONTENT_DATE DESC", nativeqyery = true)
    // Page<AskBoard> findName(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);

    // @Query(value = "SELECT * CHAR( max(to_number(d.contentNo))+1) FROM TS_CONTENT d ",nativeQuery =true)
    // String askBoardMax();


}
