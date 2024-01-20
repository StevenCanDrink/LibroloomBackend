package com.team4.libroloom.controller;

import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.VideoResDto;
import com.team4.libroloom.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/")
    public VideoResDto saveVideo(@RequestBody Video video){
        return videoService.saveVideo(video);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VideoResDto>> findVideoAllByIdMember(@PathVariable("id") Long id) {
        List<VideoResDto> videos = videoService.findVideoByMember(id);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}
