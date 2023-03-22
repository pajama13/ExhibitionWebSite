package com.sist.exhibition.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.exhibition.entity.ExhibitionEntity;

@Repository
public interface ExhibitionDAO extends JpaRepository<ExhibitionEntity, Integer>{
	//4월 전시
	@Query(value="SELECT * FROM pro3_exhibition "
			    +"WHERE period LIKE CONCAT('%','2023-04','%') "
			    +"LIMIT 0,12 "
				,nativeQuery=true)
	public List<ExhibitionEntity> exhibitionAprilListData();
	
	//무료 전시
	@Query(value="SELECT * FROM pro3_exhibition "
		    +"WHERE price LIKE CONCAT('%','무료입장','%') "
		    +"AND period LIKE CONCAT('%','2023','%') LIMIT 0,12 "
			,nativeQuery=true)
	public List<ExhibitionEntity> exhibitionFreeListData();
	
	//특정 전시 1개
	public ExhibitionEntity findByGeno(@Param("geno") Integer geno);
	
	//모든 전시리스트
	@Query(value="SELECT * FROM pro3_exhibition "
			    +"WHERE period LIKE CONCAT('%','2023','%') LIMIT :start,12",nativeQuery=true)
	public List<ExhibitionEntity> exhibitionListData(@Param("start") Integer start);
	
	//모든 전시리스트 총페이지
	@Query(value="SELECT CEIL(count(*)/12.0) FROM pro3_exhibition "
			    +"WHERE period LIKE CONCAT('%','2023','%') ",nativeQuery=true)
	public int exhibitionTotalPage();
	
	//현재 진행중인 전시
	@Query(value="SELECT * FROM pro3_exhibition_view_now LIMIT :start,12",nativeQuery=true) //임시용
	public List<ExhibitionEntity> exhibitionNowList(@Param("start") Integer start);
	
	//현재 진행중인 전시 총페이지
	@Query(value="SELECT CEIL(count(*)/12.0) FROM pro3_exhibition_view_now",nativeQuery=true)
	public int exhibitionNowTotalPage();
	
	//현재 종료된 전시
	@Query(value="SELECT * FROM pro3_exhibition_view_end LIMIT :start,12",nativeQuery=true)
	public List<ExhibitionEntity> exhibitionEndList(@Param("start") Integer start);
	
	//현재 종료된 전시 총페이지
	@Query(value="SELECT CEIL(count(*)/12.0) FROM pro3_exhibition_view_end",nativeQuery=true)
	public int exhibitionEndTotalPage();
	
	//전시찾기
	@Query(value="SELECT * FROM pro3_exhibition "
				 +"WHERE hashtag LIKE CONCAT('%',:word,'%') "
				 +"AND period LIKE CONCAT('%','2023','%') LIMIT :start,12",nativeQuery=true)
	public List<ExhibitionEntity> exhibitionFindList(@Param("word") String word,@Param("start") Integer start);
	
	//전시찾기 총페이지
	@Query(value="SELECT CEIL(count(*)/12.0) FROM pro3_exhibition "
			 +"WHERE hashtag LIKE CONCAT('%',:word,'%') "
			 +"AND period LIKE CONCAT('%','2023','%') ",nativeQuery=true)
	public int exhibitionFindTotalPage(@Param("word") String word);
	
	//전시찾기 결과수
	@Query(value="SELECT count(*) FROM pro3_exhibition "
			 +"WHERE hashtag LIKE CONCAT('%',:word,'%') "
			 +"AND period LIKE CONCAT('%','2023','%') ",nativeQuery=true)
	public int exhibitionFindCount(@Param("word") String word);

}
