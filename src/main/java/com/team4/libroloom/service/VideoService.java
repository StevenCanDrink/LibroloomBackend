package com.team4.libroloom.service;

import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.VideoResDto;
import com.team4.libroloom.mapper.VideoMapper;
import com.team4.libroloom.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {

    private final VideoRepository videoRepository;

    @Transactional
    public VideoResDto saveVideo(Video video){
        videoRepository.save(video);
        return VideoMapper.mapToVideoDto(video);
    }

    public List<VideoResDto>findVideoByMember(Long idMember){
        List<Video> video = videoRepository.findAllByMember(idMember);

        return video.stream().map(VideoMapper::mapToVideoDto)
                .collect(Collectors.toList());
    }

    public Optional<Video> findOne(Long idVideo){
        return videoRepository.findOne(idVideo);
    }
}
