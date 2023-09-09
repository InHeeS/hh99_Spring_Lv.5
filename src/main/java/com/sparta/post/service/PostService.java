package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.dto.PostResponseListDto;
import com.sparta.post.entity.Message;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.entity.UserRoleEnum;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.jwt.SecurityUtil;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    //멤버 변수 선언
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> createPost(PostRequestDto requestDto, String tokenValue) {
        User principal = SecurityUtil.getPrincipal().get();
        String username = principal.getUsername();

        //RequestDto -> Entity
        Post post = new Post(requestDto,username);

        //DB 저장
        Post savePost = postRepository.save(post);

        //Entity -> ResponseDto
        return new ResponseEntity<>(new PostResponseDto(savePost),null, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public PostResponseListDto getPosts(){
        // comment : post  -> N : 1
        // commentList -> postId 기준으로 불러온다.
        List<Post> postList = postRepository.findAllByOrderByCreatedAt();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post : postList){
            PostResponseDto postRes = new PostResponseDto(post);
            postResponseDtoList.add(postRes);
        }
        return new PostResponseListDto(postResponseDtoList);
        //return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        // id 로 조회
        Post post = findPost(id);
        // 새로운 Dto 로 수정할 부분 최소화
        return new PostResponseDto(post);
    }
    @Transactional //변경 감지(Dirty Checking), 부모메서드인 updatePost
    public ResponseEntity<?> updatePost(Long id, PostRequestDto requestDto, String tokenValue){
        User principal = SecurityUtil.getPrincipal().get();

        // 해당 post DB에 존재하는지 확인 수정필요
        Post post = findPost(id);
        String username = principal.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("토큰이 이상합니다.")
        );

        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            System.out.println("운영자가 로그인하였습니다.");
        }else if(!username.equals(post.getUsername())){
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }

        // post 내용 수정
        post.update(requestDto);

        return new ResponseEntity<>(postRepository.findById(id)
                ,null, HttpStatus.OK);
    }

    public ResponseEntity<Message> deletePost(Long id, String tokenValue){

        Message msg = new Message(200, "게시글 삭제 성공");

        User principal = SecurityUtil.getPrincipal().get();

        // 해당 post DB에 존재하는지 확인
        Post post = findPost(id);

        // 해당 사용자(username)가 작성한 게시글인지 확인
        // setSubject(username)
        String username = principal.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("토큰이 이상합니다.")
        );
        System.out.println("HEHEHEHEHEHEHEHEH");
        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            System.out.println("운영자가 로그인하였습니다.");
        }else if(!username.equals(post.getUsername())){
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }

        //post 삭제
        postRepository.delete(post);

        return new ResponseEntity<>(msg, null, HttpStatus.OK);
    }

    private Post findPost(Long id){
        //findById -> Optional type -> Null Check
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
