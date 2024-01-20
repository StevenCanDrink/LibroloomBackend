package com.team4.libroloom.mapper;

import com.team4.libroloom.domain.Video;
import com.team4.libroloom.dto.VideoResDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class VideoMapper {
    public static VideoResDto mapToVideoDto(Video video){
        return new VideoResDto(
                video.getId(),
                video.getName(),
                video.getUrl(),
                video.getImage(),
                video.getUploadedDate(),
                video.getLength(),
                Optional.ofNullable(video.getMember())
                        .map(MemberMapper::mapToMemberDTO)
                        .orElse(null)
        );
    }

    public static Video mapToVideo(VideoResDto videoResDto){
        Video video = new Video();
        video.setId(videoResDto.getId());
        video.setName(videoResDto.getName());
        video.setMember(MemberMapper.mapToMember(videoResDto.getMemberResDto()));
        video.setUrl(videoResDto.getUrl());
        video.setLength(videoResDto.getLength());
        video.setImage(videoResDto.getImage());
        return video;
    }
}
