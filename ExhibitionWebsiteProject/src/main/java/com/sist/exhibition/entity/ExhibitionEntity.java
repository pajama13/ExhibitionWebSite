package com.sist.exhibition.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/*

   CREATE TABLE pro3_exhibition(
 	GENO int auto_increment, 
 	POSTER varchar(260), 
 	TITLE varchar(150), 
 	TITLE2 varchar(150), 
 	KIND varchar(60), 
 	PERIOD varchar(100), 
 	LOC varchar(60), 
 	LOC2 varchar(100), 
 	AREA varchar(200), 
 	AREA2 varchar(200), 
 	ITEM text, 
 	HOST varchar(450), 
 	URL varchar(200), 
 	PRICE text, 
 	TIME varchar(400), 
 	HASHTAG text, 
 	CONTENT text, 
 	HIT int DEFAULT 0, 
 	LIKE_COUNT int DEFAULT 0, 
 	GOOD int,
     primary key(geno)
   );

*/
@Entity(name="pro3_exhibition")
@Getter
@Setter
public class ExhibitionEntity {
	@Id
	private int geno;
	
	private int hit;
	private String poster,title,title2,kind,period,loc,loc2,area,area2,item,
					host,url,price,time,hashtag,content;
}
