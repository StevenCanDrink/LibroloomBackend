package com.team4.libroloom.service;

import com.team4.libroloom.domain.Gender;
import com.team4.libroloom.domain.Member;
import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.MemberResDto;
import com.team4.libroloom.dto.VideoResDto;
import com.team4.libroloom.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
class VideoServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void saveVideo_savesAndReturnsVideoDTO() {
        Video video = new Video(); // Set up video fields
        video.setName("test");

        VideoResDto result = videoService.saveVideo(video);

        // Assert expected DTO content based on persisted video
        assertNotNull(result.getId()); // Ensure ID is assigned
        assertEquals(video.getName(), result.getName());
        // ... (assert other fields)
    }

    @Test
    void saveVideo_handlesInvalidVideoData() {
        Video invalidVideo = new Video();
        invalidVideo.setName(null); // Set invalid title

        assertThrows(IllegalArgumentException.class, () -> videoService.saveVideo(invalidVideo));
    }

    @Test
    void findVideoByMember_returnsVideoDTOsForMember() {
        Member member = new Member();
        member.setEmail("kimhoo@gmail.com");
        member.setPassword("123333");
        member.setGender(Gender.MALE);
        member.setUsername("kimhoo");

        MemberResDto memberResDto = memberService.join(member);

        Video video1 = new Video();
        video1.setMember(member);
        Video video2 = new Video();
        video2.setMember(member);

        videoService.saveVideo(video1);
        videoService.saveVideo(video2);

        List<VideoResDto> result = videoService.findVideoByMember(memberResDto.getId());

        assertEquals(2, result.size());
        // Assert expected DTO content based on persisted videos
        assertEquals(video1.getName(), result.get(0).getName());
        // ... (assert other fields)
    }

    @Test
    void findVideoByMember_handlesMemberWithNoVideos() {
        Long idMemberWithNoVideos = 2L;

        List<VideoResDto> result = videoService.findVideoByMember(idMemberWithNoVideos);

        assertTrue(result.isEmpty());
    }

}