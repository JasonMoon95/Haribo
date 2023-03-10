package com.MyFirstApp.Haribo.Service.posts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.MyFirstApp.Haribo.Service.posts.PostsService;
import com.MyFirstApp.Haribo.domain.posts.Posts;
import com.MyFirstApp.Haribo.domain.posts.PostsRepository;
import com.MyFirstApp.Haribo.web.dto.PostsSaveRequestDto;
import com.MyFirstApp.Haribo.web.dto.PostsUpdateRequestDto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private PostsRepository postsRepository;

    PostsService postsService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postsService = new PostsService(postsRepository);
    }

//    @Test
//    public void save() {
//        // given
//        PostsService postsService = new PostsService(postsRepository);
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build();
//        when(postsRepository.save(requestDto.toEntity())).
//                thenReturn(Posts.builder().title("abc").author("seungje").content("abcdefg!").build());
//
//        // when
//        Long id = postsService.save(requestDto);
//
//        // then
//        assertThat(id).isEqualTo(1L);
//    }

    @Test
    public void update() {
        // given
        PostsUpdateRequestDto requestDto2 = PostsUpdateRequestDto.builder()
                .title("titleTest2")
                .content("contentTest2")
                .build();

        when(postsRepository.findById(1L)).
                thenReturn(Optional.of(Posts.builder().title("abc1").author("seungje1").content("abcdefg1!").build()));
        when(postsRepository.findById(2L)).
                thenReturn(Optional.of(Posts.builder().title("abc2").author("seungje2").content("abcdefg2!").build()));
        when(postsRepository.findById(3L)).
                thenReturn(Optional.of(Posts.builder().title("abc3").author("seungje3").content("abcdefg3!").build()));

        // when
        Long id = postsService.update(1L, requestDto2);
        Long id2 = postsService.update(2L, requestDto2);
        Long id3 = postsService.update(3L, requestDto2);

        // then
        assertThat(id).isEqualTo(1L);
        assertThat(id2).isEqualTo(2L);
        assertThat(id3).isEqualTo(3L);
    }

//    @Test
//    public void findById() {
//        // given
//        when(postsRepository.findById(1L)).thenReturn(Optional.of(Posts.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build()));
//
//        // when
//        PostsResponseDto responseDto = postsService.findById(1L);
//
//        // then
//        assertThat(responseDto.getTitle()).isEqualTo("title");
//        assertThat(responseDto.getContent()).isEqualTo("content");
//        assertThat(responseDto.getAuthor()).isEqualTo("author");
//    }

}