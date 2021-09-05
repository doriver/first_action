package com.sleep.insta.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sleep.insta.common.FileManagerService;
import com.sleep.insta.post.comment.bo.CommentBO;
import com.sleep.insta.post.comment.model.Comment;
import com.sleep.insta.post.dao.PostDAO;
import com.sleep.insta.post.model.Post;
import com.sleep.insta.post.model.PostWithComments;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		FileManagerService fileManager = new FileManagerService();
		
		String filePath = fileManager.saveFile(userId, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	public List<PostWithComments> getPostList(int userId) {
		List<Post> postList = postDAO.selectPostList();
		
		List<PostWithComments> postWithCommentsList = new ArrayList<>();
		
		for(Post post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostId(post.getId());
			
			boolean isLike = likeBO.existLike(post.getId(), userId);
			int likeCount = likeBO.countLike(post.getId());
			
			PostWithComments postWithComments = new PostWithComments();
			postWithComments.setPost(post);
			postWithComments.setCommentList(commentList);
			postWithComments.setLike(isLike);
			postWithComments.setLikeCount(likeCount);
			
			postWithCommentsList.add(postWithComments);
		}
		
		return postWithCommentsList;
	}
}
