package com.NTPWS.projekt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemeDTO {
    private String postLink;
    private String subreddit;
    private String title;
    private String url;
    private boolean nsfw;
    private boolean spoiler;
    private String author;
    private Long ups;
    private List<String> preview;
}
