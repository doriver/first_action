package com.sleep.insta.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public int insertLike(@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectCountLike(@Param("postId") int postId
			, @Param("userId") int userId);

	public int deleteLike(@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectCountLikeByPostId(@Param("postId") int postId);
}
